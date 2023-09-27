package com.eltescode.graphql.feature.country.data

import com.eltescode.CountryQuery
import com.eltescode.CountriesQuery
import com.apollographql.apollo3.ApolloClient
import com.eltescode.graphql.feature.country.domain.CountryClient
import com.eltescode.graphql.feature.country.domain.model.DetailedCountry
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry


class ApolloCountryClient(private val apolloClient: ApolloClient) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}


