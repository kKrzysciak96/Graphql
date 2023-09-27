package com.eltescode.graphql.feature.country.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eltescode.graphql.feature.country.domain.model.DetailedCountry
import com.eltescode.graphql.feature.country.domain.model.SimpleCountry
import com.eltescode.graphql.feature.country.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state: StateFlow<CountriesState> = _state

    init {
        getCountries()
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null

    )

    fun getCountry(code: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(selectedCountry = useCases.getCountryUseCase(code)) }
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.value = state.value.copy(countries = useCases.getCountriesUseCase())
        }
    }

    fun dismissDialog(){
        _state.value = state.value.copy(selectedCountry = null)
    }
}