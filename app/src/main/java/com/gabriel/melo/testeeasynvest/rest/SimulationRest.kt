package com.gabriel.melo.testeeasynvest.rest

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.gabriel.melo.testeeasynvest.model.response.SimulationResponse
import org.androidannotations.rest.spring.annotations.Rest
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.androidannotations.rest.spring.annotations.Get
import org.androidannotations.rest.spring.annotations.Path
import org.springframework.http.converter.StringHttpMessageConverter
import java.util.*


@Rest(rootUrl = "https://api-simulator-calc.easynvest.com.br/calculator", converters = [MappingJackson2HttpMessageConverter::class, StringHttpMessageConverter::class], interceptors = [SimulationInterceptor::class])
interface SimulationRest {
    @Get("/simulate?investedAmount={investedAmount}&index={index}&rate={rate}&isTaxFree={isTaxFree}&maturityDate={maturityDate}")
    fun simulate(@Path investedAmount: Double?,
                 @Path index: String?,
                 @Path rate: Double?,
                 @Path isTaxFree: Boolean?,
                 @Path maturityDate: String?) : SimulationResponse
}