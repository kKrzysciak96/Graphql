package com.eltescode.graphql.feature.country.domain

import com.eltescode.graphql.feature.country.domain.model.DetailedCountry
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry

interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?
}