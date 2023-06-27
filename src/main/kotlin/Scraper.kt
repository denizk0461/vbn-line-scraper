import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.openqa.selenium.chrome.ChromeDriver
import java.io.IOException

class Scraper {

    private val url = "https://www.vbn.de/service/linienfahrplaene"

    private val addedLines: MutableList<String> = mutableListOf()


    /**
     * Scrapes the VBN website for lines.
     *
     * @param onProgress    reports back progress
     * @return              list of lines
     */
    fun scrape(onProgress: (String) -> Unit): List<Line> {

        val linesList: MutableList<Line> = mutableListOf()

        /*
         * Using WebDriver to fetch the website before parsing its contents. This is necessary, as some elements can
         * otherwise not be found. I suspect this is due to the website not fully loading unless a web browser instance
         * tries to load its contents.
         */
        val driver = ChromeDriver()
        driver.get(url)
        val doc = Jsoup.parse(driver.pageSource)

        // Retrieve the list that holds all lines
        val list = doc.getElementById("line-list")

        // Iterate through individual tables
        list?.let { l -> l.getElementsByClass("card mb-2 border-0").forEachIndexed { index, child ->

            onProgress("progress – table index: $index")

            val id = "DataTables_Table_$index"

            child.select("table[id='$id']")[0].let { table ->
                linesList.addAll(table.parseTable(doc, onProgress))
            }
        }}

        driver.quit()

        return linesList
    }

    /**
     * Parse one table with lines.
     *
     * @receiver            table to parse
     * @param doc           whole website document
     * @param onProgress    reports back progress
     * @return              lines from this table
     */
    private fun Element.parseTable(doc: Document, onProgress: (String) -> Unit): List<Line> {
        val list: MutableList<Line> = mutableListOf()

        onProgress("getting started with parsing the table!")

        // Find all rows in the table
        val rows = this.select("tr[role=row]")

        // Skip over the first row, since that only includes header text
        for (i in 1 until rows.size) {

            val tds = rows[i].getElementsByTag("td")

            // Find line denominator (e.g. '102' or 'N7')
            val name = tds.first()?.text() ?: throw IOException("Row could not be found.")

            // Prevent adding a line multiple times
            if (name in addedLines) {
                onProgress("progress – skipped $name")
                continue
            }

            // Find all areas
            val areas = doc.findAreasForLine(name)

            onProgress("progress – row index: $i, line: $name")

            // Add the line's name to this list to prevent it from being added multiple times
            addedLines.add(name)

            // Construct and insert line into list
            list.add(Line(
                name = name,
                route = tds[1].text(),
                areas = areas,
                vehicleType = VehicleType.getType(rows[i].getElementsByClass("lisstIcon")[0].text()).toString(),
            ))
        }

        return list
    }

    /**
     * Finds all areas for a line.
     *
     * @receiver    whole website document
     * @param name  name of the line
     * @return      array of areas the given line is present in
     */
    private fun Document.findAreasForLine(name: String): Array<String> {
        val areas = arrayListOf<String>()

        // Find and iterate through all elements for this line
        this.select("td[class='sorting_1']").filter { e -> e.text() == name }.forEach { e ->

            // Find area in the element
            val area = (e.parents().select("div[class='card-header border-0 collapse-header']").first()?.text()
                ?: throw IOException("Area could not be found."))
                .prepareAreaString()

            // Avoid duplicate area entries
            if (area !in areas) {
                areas.add(area)
            }
        }

        return areas.toTypedArray()
    }

    /**
     * Trim whitespace, shorten "Stadt" to "ST_" and "Landkreis" to "LK_", and put the whole thing in uppercase.
     *
     * @receiver    string to edit
     * @return      edited string
     */
    private fun String.prepareAreaString() =
        this.trim().replace("Stadt ", "ST_").replace("Landkreis ", "LK_").uppercase()
}