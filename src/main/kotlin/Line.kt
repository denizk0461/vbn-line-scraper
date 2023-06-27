data class Line(
    val name: String,
    val route: String,
    val areas: Array<String>,
    val vehicleType: String, // tram, bus, bürgerbus, etc. also includes Nacht
    val operationTime: String = "DAY",
    val color: String = "",
)
