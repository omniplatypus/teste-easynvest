package com.gabriel.melo.testeeasynvest.dao

import com.gabriel.melo.testeeasynvest.model.request.SimulationRequest
import com.gabriel.melo.testeeasynvest.model.response.SimulationResponse
import com.gabriel.melo.testeeasynvest.rest.SimulationRest
import com.gabriel.melo.testeeasynvest.rest.SimulationRest_
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object InvestmentDAO {
    private val rest: SimulationRest = SimulationRest_(null)
    suspend fun simular(request: SimulationRequest, callback: (SimulationResponse) -> Unit) {
        withContext(Dispatchers.IO) {
            callback.invoke(rest.simulate(request.investedAmount, request.index, request.rate, request.isTaxFree, SimpleDateFormat("yyyy-MM-dd").format(request.maturityDate)))
        }
    }
}