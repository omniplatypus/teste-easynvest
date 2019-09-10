package com.gabriel.melo.testeeasynvest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.melo.testeeasynvest.dao.InvestmentDAO
import com.gabriel.melo.testeeasynvest.model.request.SimulationRequest
import com.gabriel.melo.testeeasynvest.model.response.SimulationResponse
import kotlinx.coroutines.launch

class InvestmentViewModel : ViewModel() {
    private lateinit var simulationResult: MutableLiveData<SimulationResponse>
    private suspend fun simular(simulationRequest: SimulationRequest) {
        if (!::simulationResult.isInitialized) {
            simulationResult = MutableLiveData()
        }
        InvestmentDAO.simular(simulationRequest, simulationResult::postValue)
    }

    fun getSimulation() : LiveData<SimulationResponse> {
        if (!::simulationResult.isInitialized) {
            simulationResult = MutableLiveData()
        }
        return simulationResult
    }

    fun simulate(simulationRequest: SimulationRequest) {
        viewModelScope.launch {
            simular(simulationRequest)
        }}
}