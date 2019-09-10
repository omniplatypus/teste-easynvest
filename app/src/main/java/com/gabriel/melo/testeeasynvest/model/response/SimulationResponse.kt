package com.gabriel.melo.testeeasynvest.model.response

import com.gabriel.melo.testeeasynvest.model.InvestmentParameter

data class SimulationResponse (
    var investmentParameter: InvestmentParameter? = null,
    var grossAmount: Double = 0.0,
    var taxesAmount: Double = 0.0,
    var netAmount: Double = 0.0,
    var grossAmountProfit: Double = 0.0,
    var netAmountProfit: Double = 0.0,
    var annualGrossRateProfit: Double = 0.0,
    var monthlyGrossRateProfit: Double = 0.0,
    var dailyGrossRateProfit: Double = 0.0,
    var taxesRate: Double = 0.0,
    var rateProfit: Double = 0.0,
    var annualNetRateProfit: Double = 0.0
)