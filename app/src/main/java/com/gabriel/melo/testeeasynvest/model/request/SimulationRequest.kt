package com.gabriel.melo.testeeasynvest.model.request

import java.util.*

open class SimulationRequest (
    var index: String? = "CDI",
    var investedAmount: Double = 0.0,
    var rate: Double = 0.0,
    @JvmField var isTaxFree: Boolean? = false,
    var maturityDate: Date? = null
)