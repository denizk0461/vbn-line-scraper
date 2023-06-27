import java.io.IOException

enum class VehicleType {
    TRAIN,
    TRAM,
    BUS,
    BUERGERBUS,
    TAXI,
    AST,
    NACHT,
    ;

    companion object {

        // URLs for the vehicle icons on the website
        private val trainUrl = "/uploads/_processed_/3/c/csm_haf_prod_reg_5def92b6ab.png"
        private val tramUrl = "/uploads/_processed_/b/3/csm_haf_prod_tram_507ffce0b0.png"
        private val busUrl = "/uploads/_processed_/5/b/csm_haf_prod_bus_2482a6cd66.png"
        private val burgerBusUrl = "/uploads/tx_rscwliniendownloader/BuergerBus_icon_rund.svg"
        private val taxiUrl = "doesn't exist"
        private val astUrl = "/uploads/tx_rscwliniendownloader/haf_prod_taxi.svg"
        private val nightUrl = "/uploads/tx_rscwliniendownloader/Nacht_blau.svg"

        /**
         * Retrieve vehicle type based on the line's icon URL.
         *
         * @param url           URL to look up
         * @return              VehicleType
         * @throws IOException  if the type could not be found
         */
        @Throws(IOException::class)
        fun getType(url: String): VehicleType = when (url) {
            trainUrl -> TRAIN
            tramUrl -> TRAM
            busUrl -> BUS
            burgerBusUrl -> BUERGERBUS
            taxiUrl -> TAXI
            astUrl -> AST
            nightUrl -> NACHT
            else -> throw IOException("VehicleType for URL $url not found.")
        }
    }
}