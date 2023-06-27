import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.openqa.selenium.chrome.ChromeDriver
import java.io.IOException

class Scraper {

    private val url = "https://www.vbn.de/service/linienfahrplaene"

    private val addedLines: MutableList<String> = mutableListOf()

    private val trainUrl = "/uploads/_processed_/3/c/csm_haf_prod_reg_5def92b6ab.png"
    private val tramUrl = "/uploads/_processed_/b/3/csm_haf_prod_tram_507ffce0b0.png"
    private val busUrl = "/uploads/_processed_/5/b/csm_haf_prod_bus_2482a6cd66.png"
    private val burgerBusUrl = "/uploads/tx_rscwliniendownloader/BuergerBus_icon_rund.svg"
    private val taxiUrl = "doesn't exist"
    private val astUrl = "/uploads/tx_rscwliniendownloader/haf_prod_taxi.svg"
    private val nightUrl = "/uploads/tx_rscwliniendownloader/Nacht_blau.svg"

    fun scrape(onProgress: (String) -> Unit): List<Line> {
        val linesList: MutableList<Line> = mutableListOf()

        val driver = ChromeDriver()
        driver.get(url)

        val doc = Jsoup.parse(driver.pageSource)

//        val doc = Jsoup.connect(url).get()

//        println(driver.pageSource)

        val list = doc.getElementById("line-list")

        list?.let { l -> l.getElementsByClass("card mb-2 border-0").forEachIndexed { index, child ->

            onProgress("progress – table index: $index")
            val id = "DataTables_Table_$index"
            onProgress("check-in, id: '$id'")

            child.select("table[id='$id']")[0].let { table ->
                linesList.addAll(table.parseTable(doc, onProgress))
            }
        }}

        driver.quit()

        return linesList
    }

    private fun Element.parseTable(doc: Document, onProgress: (String) -> Unit): List<Line> {
        val list: MutableList<Line> = mutableListOf()

        onProgress("getting started with parsing the table!")

        val rows = this.select("tr[role=row]")
        for (i in 1 until rows.size) {
            val tds = rows[i].getElementsByTag("td")
            val name = tds.first()?.text() ?: throw IOException("didn't find that, so, error")

            if (name in addedLines) {
                onProgress("progress – skipped $name")
                continue
            }

            val areas = doc.findAreasForLine(name).toTypedArray()

            addedLines.add(name)

            onProgress("progress – row index: $i, line: $name")
            list.add(Line(
                name = name,
                route = tds[1].text(),
                areas = areas,
                vehicleType = rows[i].getElementsByClass("lisstIcon")[0].getVehicleType(),
            ))
        }

        return list
    }

    private fun Document.findAreasForLine(name: String): List<String> {
        val areas = arrayListOf<String>()

        this.select("td[class='sorting_1']").filter { e -> e.text() == name }.forEach { e ->
            val area = (e.parents().select("div[class='card-header border-0 collapse-header']").first()?.text()
                ?: throw IOException("error during finding areas!"))
                .prepareAreaString()

            if (area !in areas) {
                areas.add(area)
            }
        }

        return areas
    }

    private fun Element.getVehicleType(): String = when (this.attr("src")) {
        trainUrl -> "TRAIN"
        tramUrl -> "TRAM"
        busUrl -> "BUS"
        burgerBusUrl -> "BUERGERBUS"
        taxiUrl -> "TAXI"
        astUrl -> "AST"
        nightUrl -> "NACHT"
        else -> "error"
    }

    // Trim whitespace and shorten "Stadt" to "ST_" and "Landkreis" to "LK_"
    private fun String.prepareAreaString() =
        this.trim().replace("Stadt ", "ST_").replace("Landkreis ", "LK_").uppercase()
}