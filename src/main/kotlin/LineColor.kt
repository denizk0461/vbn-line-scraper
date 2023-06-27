enum class LineColor {

    BSAG_BLUE,
    BSAG_BLUE_DARK,
    BSAG_BLUE_LIGHT,
    BSAG_GREEN_DARK,
    BSAG_GREEN_LIGHT,
    BSAG_LAVENDER,
    BSAG_ORANGE,
    BSAG_PINK,
    BSAG_RED,
    BSAG_TURQUOISE,
    BSAG_VIOLET,
    BSAG_YELLOW,

    // BREMERHAVEN BUS doesn't share colors between lines
    BHV_502,   // #fffb00; can share approximately with BSAG 6
    BHV_503,   // #fbd09f
    BHV_504,   // #a71a75; can share with BSAG 24
    BHV_505,   // #154193; can share with BSAG 10, 90
    BHV_506,   // #48bdd7; can share with BSAG 3
    BHV_507,   // #a75f49
    BHV_508,   // #d4e8d6
    BHV_509,   // #009746; can share with BSAG 1, 25
    BHV_510,   // #dae084
    BHV_512,   // #d1cce6; can share approximately with BSAG 22
    BHV_514,   // #ee7e1a; can share with BSAG 57/58
    BHV_517,   // #b0cb18; can share with BSAG 8
    BHV_S,     // #cc3300
    BHV_HL,    // #f4b0b5
    BHV_ML,    // #a76181
    BHV_NL,    // #846473
    BHV_WHITE, // #ffffff; for all other lines

    /*
     * VWG lines share similar but not quite identical colors. These can be merged in the app-themed colors.
     */
    VWG_LAVENDER, // #c5a5cc; 301
    VWG_RED, // #e31d26; 302, 304
    VWG_GREEN, // #36ab48; 306
    VWG_GREEN_VARIANT, // #36b449; 330
    VWG_BLUE, // #239cd7; 308
    VWG_PINK, // #ed2093; 309
    VWG_RED_DARK, // #b32024; 310
    VWG_YELLOW_DARK, // #886a29; 311
    VWG_BLUE_GREY, // #49697e; 313
    VWG_LIME, // #bbcf31; 314
    VWG_BLUE_DARK, // #0d5b99; 315
    VWG_GREEN_DARK, // #4e8c40; 318
    VWG_YELLOW_VARIANT, // #878629; 320
    VWG_ORANGE_LIGHT, // #f9c08a; 321
    VWG_TURQUOISE, // #149da9; 322
    VWG_BLUE_LIGHT, // #49c1ee; 323
    VWG_ORANGE, // #f2931f; 324
    VWG_PURPLE, // #8c7aa0; 325
    VWG_PINK_DARK, // #b11f71; 329
    VWG_PURPLE_DARK, // #80488c; 340
    VWG_TEAL_DARK, // #036480; 350
    VWG_NIGHT, // #000000; N25, N36, N37, N38, N39, N40, N41

    DEFAULT, // TODO this may be unnecessary

    // AllerBus lines all share a single color
    ALLERBUS, // #99ccff

    /*
     * Colors from VBN Fahrplaner, use these either when no color is specified, or if the user wishes to not use
     * line-specific colors.
     */
    VBN_DEFAULT_REGIO,          // #52267d; lifted from Fahrplaner regional train icon
    VBN_DEFAULT_SBAHN,          // #139445; lifted from Fahrplaner regional train icon
    VBN_DEFAULT_TRAM,           // #e30613
    VBN_DEFAULT_BUS,            // #005ca9
    VBN_DEFAULT_BUERGERBUS,     // #c20e1a
    VBN_DEFAULT_LT,             // #005ca9; identical with bus, used for Linientaxi, maybe edit this
    VBN_DEFAULT_AST,            // #ffcc14; used for AST as well as
    ;

    fun getColor(line: String, type: VehicleType): LineColor = when (line) {
        // BSAG lines
        "2" -> BSAG_BLUE
        "10", "63", "90" -> BSAG_BLUE_DARK
        "3", "21", "61", "91", "92", "N6", "N10" -> BSAG_BLUE_LIGHT
        "1", "25", "62", "81", "98", "N1" -> BSAG_GREEN_DARK
        "8", "20", "29", "31", "52", "65", "66", "80", "N7" -> BSAG_GREEN_LIGHT
        "22", "N94" -> BSAG_LAVENDER
        "27", "44", "57", "58", "82", "95", "N5" -> BSAG_ORANGE
        "N3" -> BSAG_PINK
        "4", "26", "40", "41", "94", "N4" -> BSAG_RED
        "5" -> BSAG_TURQUOISE
        "24", "37", "96" -> BSAG_VIOLET
        "6", "28", "33", "34", "38", "39", "42", "55", "93", "N9" -> BSAG_YELLOW

        // BREMERHAVEN BUS lines
        "502" -> BHV_502
        "503" -> BHV_503
        "504" -> BHV_504
        "505" -> BHV_505
        "506" -> BHV_506
        "507" -> BHV_507
        "508" -> BHV_508
        "509" -> BHV_509
        "510" -> BHV_510
        "512" -> BHV_512
        "514" -> BHV_514
        "517" -> BHV_517
        "S" -> BHV_S
        "HL" -> BHV_HL
        "ML" -> BHV_ML
        "NL" -> BHV_NL
        "513", "515", "516", "518", "519" -> BHV_WHITE

        // VWG lines
        "301" -> VWG_LAVENDER
        "302", "304" -> VWG_RED
        "306" -> VWG_GREEN
        "330" -> VWG_GREEN_VARIANT
        "308" -> VWG_BLUE
        "309" -> VWG_PINK
        "310" -> VWG_RED_DARK
        "311" -> VWG_YELLOW_DARK
        "313" -> VWG_BLUE_GREY
        "314" -> VWG_LIME
        "315" -> VWG_BLUE_DARK
        "318" -> VWG_GREEN_DARK
        "320" -> VWG_YELLOW_VARIANT
        "321" -> VWG_ORANGE_LIGHT
        "322" -> VWG_TURQUOISE
        "323" -> VWG_BLUE_LIGHT
        "324" -> VWG_ORANGE
        "325" -> VWG_PURPLE
        "329" -> VWG_PINK_DARK
        "340" -> VWG_PURPLE_DARK
        "350" -> VWG_TEAL_DARK
        "N25", "N36", "N37", "N38", "N39", "N40", "N41" -> VWG_NIGHT

        // AllerBus lines
        "701", "711", "712", "713", "714", "715", "717", "718", "725", "781", "782", "783", "784", "500", "510", "550",
        "556" -> ALLERBUS

        else -> getColorForVehicleType(type)
    }

    /**
     * Retrieve default VBN color for a specific vehicle type.
     *
     * @param type  VehicleType
     * @return      color
     */
    private fun getColorForVehicleType(type: VehicleType): LineColor = when (type) {
        // Use different colors for S-Bahn and other regional trains
        VehicleType.TRAIN -> if (name.substring(0, 2) == "RS") VBN_DEFAULT_SBAHN else VBN_DEFAULT_REGIO
        VehicleType.TRAM -> VBN_DEFAULT_TRAM
        VehicleType.BUS -> VBN_DEFAULT_BUS
        VehicleType.BUERGERBUS -> VBN_DEFAULT_BUERGERBUS
        VehicleType.TAXI -> VBN_DEFAULT_LT
        VehicleType.AST -> VBN_DEFAULT_AST
        VehicleType.NACHT -> DEFAULT
    }
}