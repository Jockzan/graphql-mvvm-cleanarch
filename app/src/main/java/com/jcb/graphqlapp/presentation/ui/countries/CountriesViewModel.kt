package com.jcb.graphqlapp.presentation.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcb.graphqlapp.domain.entities.DetailCountry
import com.jcb.graphqlapp.domain.usecases.GetCountriesUSeCase
import com.jcb.graphqlapp.domain.usecases.GetCountryUseCase
import com.jcb.graphqlapp.domain.entities.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel
@Inject
constructor(
    private val getCountriesUseCase: GetCountriesUSeCase,
    private val getCountryUseCase: GetCountryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CountriesState())
    val state: StateFlow<CountriesState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false,
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code),
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update {
            it.copy(
                selectedCountry = null,
            )
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailCountry? = null,
        val error: String? = null,
    )
}
