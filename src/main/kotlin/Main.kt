
fun main() {
    println("Getting started babyyy!")
    val lines = Scraper().scrape {
        println(it)
    }
        println("i am done.")

    FileHandler().writeToFile("lines${System.currentTimeMillis()}.json", lines)
}