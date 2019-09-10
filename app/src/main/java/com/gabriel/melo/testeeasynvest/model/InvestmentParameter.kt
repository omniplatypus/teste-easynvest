package com.gabriel.melo.testeeasynvest.model

import com.gabriel.melo.testeeasynvest.model.request.SimulationRequest
import java.util.*

data class InvestmentParameter (
    var yearlyInterestRate: Double = 0.0,
    var maturityTotalDays: Int = 0,
    var maturityBusinessDays: Int = 0
) : SimulationRequest()