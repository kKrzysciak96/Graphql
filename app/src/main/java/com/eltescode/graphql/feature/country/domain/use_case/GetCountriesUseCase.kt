package com.eltescode.graphql.feature.country.domain.use_case

import com.eltescode.graphql.feature.country.domain.CountryClient
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry

class GetCountriesUseCase(private val client: CountryClient) {

    suspend operator fun invoke(): List<SimpleCountry>{
       return client.getCountries().sortedBy { it.name }
    }
}