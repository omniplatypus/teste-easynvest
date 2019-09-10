package com.gabriel.melo.testeeasynvest.gui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gabriel.melo.testeeasynvest.R
import com.gabriel.melo.testeeasynvest.model.request.SimulationRequest
import com.gabriel.melo.testeeasynvest.viewModel.InvestmentViewModel
import kotlinx.android.synthetic.main.fragment_investment_form.*
import java.lang.IllegalStateException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InvestmentFormFragment : Fragment(R.layout.fragment_investment_form) {
    private lateinit var viewModel: InvestmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = activity?.let { ViewModelProviders.of(it).get(InvestmentViewModel::class.java) } ?: throw IllegalStateException()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_simular.setOnClickListener {
            if (validateFields()) {
                viewModel.simulate(
                    SimulationRequest(
                        investedAmount = valorInvestimento.rawValue.toDouble().div(100),
                        rate = percentualCDI.text.toString().toDouble(),
                        maturityDate = SimpleDateFormat("dd/MM/YYYY", Locale("pt-BR", "BR")).parse(dataVencimento.text.toString()
                        )
                    )
                )
                findNavController().navigate(R.id.investmentResultFragment)
            }
        }
        valorInvestimento.defaultLocale = Locale("pt_BR", "BR")
    }

    private fun validateFields() : Boolean {
        var success = true
        if (valorInvestimento.rawValue <= 0) {
            valorInvestimento.error = getString(R.string.preencha_o_valor)
            success = false
        }
        if (percentualCDI.text.isNullOrBlank()) {
            percentualCDI.error = getString(R.string.preencha_percentual_CDI)
            success = false
        }
        try {
            val formatter = SimpleDateFormat("dd/MM/YYYY", Locale("pt-BR", "BR"))
            formatter.isLenient = false
            if (dataVencimento.text.isNullOrBlank()) {
                dataVencimento.error = getString(R.string.preencha_a_data)
                success = false
            } else if (formatter.parse(dataVencimento.text.toString()).before(Date())) {
                dataVencimento.error = getString(R.string.data_invalida_erro)
                success = false
            }
        } catch (ex: ParseException) {
            dataVencimento.error = getString(R.string.data_invalida)
        }
        return success
    }
}