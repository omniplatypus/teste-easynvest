package com.gabriel.melo.testeeasynvest.gui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gabriel.melo.testeeasynvest.R
import com.gabriel.melo.testeeasynvest.model.response.SimulationResponse
import com.gabriel.melo.testeeasynvest.viewModel.InvestmentViewModel
import kotlinx.android.synthetic.main.fragment_investment_result.*
import java.lang.IllegalStateException
import java.util.*

class InvestmentResultFragment : Fragment(R.layout.fragment_investment_result) {
    private lateinit var viewModel: InvestmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = activity?.let { ViewModelProviders.of(it).get(InvestmentViewModel::class.java) } ?: throw IllegalStateException()
        viewModel.getSimulation().observe(this, Observer { setupScreen(it) })
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_simular.findViewById<TextView>(R.id.buttonText).text = getString(R.string.simular_novamente)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            (btn_simular as CardView).setCardBackgroundColor(requireContext().getColor(R.color.verdeSimularNovamente))
        } else {
            (btn_simular as CardView).setCardBackgroundColor(requireContext().resources.getColor(R.color.verdeSimularNovamente))
        }
        btn_simular.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setupScreen(response: SimulationResponse) {
        brutoTotal.text = response.grossAmount.toCurrencyString()
        rendimentoTotal.text = response.grossAmountProfit.toCurrencyString()

        aplicadoInicialmente.text = response.investmentParameter?.investedAmount?.toCurrencyString()
        brutoInvestimento.text = response.grossAmount.toCurrencyString()
        rendimento.text = response.grossAmountProfit.toCurrencyString()
        irInvestimento.text = getIRString(response.taxesAmount, response.taxesRate)
        liquidoInvestimento.text = response.netAmount.toCurrencyString()

        val data = Calendar.getInstance()
        data.time = response.investmentParameter?.maturityDate
        dataResgate.text = data.let { "${it[Calendar.DAY_OF_MONTH]}/${it[Calendar.MONTH]}/${it[Calendar.YEAR]}" }
        diasCorridos.text = response.investmentParameter?.maturityTotalDays.toString()
        rendimentoMensal.text = response.monthlyGrossRateProfit.toPercentString()
        percentualCDI.text = response.investmentParameter?.rate?.toPercentString()
        rentabilidadeAnual.text = response.investmentParameter?.yearlyInterestRate?.toPercentString()
        rentabilidadePeriodo.text = response.rateProfit.toPercentString()
    }

    private fun Double.toCurrencyString() : String {
        return "R$ %.2f".format(this)
    }

    private fun getIRString(taxAmount: Double, taxRate: Double) : String {
        return taxAmount.toCurrencyString() + "(" + "%.2f".format(taxRate) + "%)"
    }

    private fun Double.toPercentString() : String {
        return "%.2f".format(this) + "%"
    }
}