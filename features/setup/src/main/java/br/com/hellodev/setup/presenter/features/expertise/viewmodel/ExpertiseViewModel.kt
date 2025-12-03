package br.com.hellodev.setup.presenter.features.expertise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.domain.model.expertise.Expertise
import br.com.hellodev.setup.presenter.features.expertise.action.ExpertiseAction
import br.com.hellodev.setup.presenter.features.expertise.state.ExpertiseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpertiseViewModel : ViewModel() {

    private var _state = MutableStateFlow(ExpertiseState())
    var state: StateFlow<ExpertiseState> = _state

    init {
        getExpertise()
    }

    fun dispatchAction(action: ExpertiseAction) {
        when (action) {
            is ExpertiseAction.OnSelect -> {
                onSelect(expertise = action.expertise)
            }
        }
    }

    private fun getExpertise() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = false,
                expertises = Expertise.items()
            )
        }
    }

    private fun onSelect(expertise: Expertise) {
        val selectedExpertise = _state.value.expertises.first { it.id == expertise.id }
        val selectedExpertises = _state.value.selectedExpertises.toMutableList()

        if (selectedExpertises.contains(selectedExpertise)) {
            // Remove o item selecionado
            selectedExpertises.remove(selectedExpertise)
        } else if (selectedExpertises.size < 5) {
            // Adiciona o item apenas se o limite não foi atingido
            selectedExpertises.add(selectedExpertise)
        }

        _state.value = _state.value.copy(
            selectedExpertises = selectedExpertises
        )
    }


}