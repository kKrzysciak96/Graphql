package com.eltescode.graphql.feature.country.data


import com.eltescode.CountryQuery
import com.eltescode.CountriesQuery
import com.eltescode.graphql.feature.country.domain.model.DetailedCountry
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        name = name,
        capital = capital,
        code = code,
        emoji = emoji,
        currency = currency ?: "No currency" ,
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = name,
        capital = capital,
        code = code,
        emoji = emoji,
    )
}