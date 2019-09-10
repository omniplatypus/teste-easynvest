package com.gabriel.melo.testeeasynvest.rest

import android.util.Log
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class SimulationInterceptor : ClientHttpRequestInterceptor {
    override fun intercept(
        request: HttpRequest?,
        body: ByteArray?,
        execution: ClientHttpRequestExecution?
    ): ClientHttpResponse {
        Log.d(this.javaClass.simpleName, "request=${request?.uri}\nbody=${body?.toString()}")
        return execution!!.execute(request, body)
    }
}