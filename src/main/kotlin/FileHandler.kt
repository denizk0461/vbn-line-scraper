import com.google.gson.GsonBuilder
import java.io.File

class FileHandler {

    private val parentDirectory = "C:\\Users\\kduez\\Documents\\Creative Resources\\0011 Small Programming Projects\\001 VBN Line Scraper\\Output"

    fun writeToFile(fileName: String, lines: List<Line>) {
        val file = File("$parentDirectory\\$fileName")

        file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(lines))
    }
}