package com.eltescode.graphql.feature.country.domain.use_case

import com.eltescode.graphql.feature.country.domain.CountryClient
import com.eltescode.graphql.feature.country.domain.model.DetailedCountry

class GetCountryUseCase(private val client: CountryClient) {

    suspend operator fun invoke(code: String): DetailedCountry?{
       return client.getCountry(code)
    }
}