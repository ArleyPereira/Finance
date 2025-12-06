package br.com.hellodev.credit_card.presenter.features.form.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.core.enums.feedback.FeedbackType
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.CLOSING_DAY
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.DUE_DAY
import br.com.hellodev.core.enums.input.credit_card.CreditCardInputType.NAME
import br.com.hellodev.core.enums.sheet.SheetType
import br.com.hellodev.core.firebase.FirebaseHelper
import br.com.hellodev.core.functions.capitalizeEachWord
import br.com.hellodev.credit_card.presenter.features.form.action.FormCreditCardAction
import br.com.hellodev.credit_card.presenter.features.form.state.FormCreditCardState
import br.com.hellodev.domain.model.feedback.Feedback
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormCreditCardViewModel(

) : ViewModel() {

    private var _state = MutableStateFlow(FormCreditCardState())
    var state: StateFlow<FormCreditCardState> = _state

    init {

    }

    fun dispatchAction(action: FormCreditCardAction) {
        when (action) {
            is FormCreditCardAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is FormCreditCardAction.OnLimitChange -> {
                onLimitChange(action.value)
            }

            is FormCreditCardAction.SaveCreditCard -> {
                saveCreditCard()
            }

            is FormCreditCardAction.ClearBottomSheet -> {
                clearBottomSheet()
            }

            is FormCreditCardAction.SetCurrentSheetType -> {
                setCurrentSheetType(action.type)
            }
        }
    }

    private fun saveCreditCard() {
        viewModelScope.launch {
            try {
                if (validateInputs() != null) {
                    _state.update { it.copy(inputError = validateInputs()) }
                    return@launch
                }

                _state.update { it.copy(isLoading = true) }

                delay(3000)

                _state.update { it.copy(isLoading = false) }

                //_event.send(SignupEvent.Navigation.Main)
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update {
                    it.copy(
                        feedback = Feedback(
                            show = true,
                            type = FeedbackType.ERROR,
                            title = FirebaseHelper.validError(exception.message)
                        )
                    )
                }
            }
        }
    }

    private fun onValueChange(value: String, type: CreditCardInputType) {
        when (type) {
            NAME -> {
                _state.update { it.copy(name = capitalizeEachWord(value)) }
            }

            CLOSING_DAY -> {
                _state.update {
                    it.copy(
                        closingDay = value.toInt(),
                        currentSheetType = SheetType.EMPTY
                    )
                }
            }

            DUE_DAY -> {
                _state.update {
                    it.copy(
                        dueDay = value.toInt(),
                        currentSheetType = SheetType.EMPTY
                    )
                }
            }

            else -> {}
        }

        _state.update { it.copy(inputError = null) }
    }

    private fun onLimitChange(value: Float) {
        _state.update { it.copy(limit = value, inputError = null) }
    }

    private fun validateInputs(): CreditCardInputType? {
//        val state = _state.value
//
//        return when {
//            !isValidName(state.name) -> NAME
//            state.limit <= 0f -> LIMIT
//            state.closingDay !in 1..31 -> CLOSING_DAY
//            state.dueDay !in 1..31 -> DUE_DAY
//            state.dueDay <= state.closingDay -> DUE_DAY
//            else -> null
//        }
        return null
    }

    private fun isValidData(): Boolean {
//        val name = isValidName(_state.value.name)
//        val limit = _state.value.limit > 0f
//        val closingDay = _state.value.closingDay in 1..31
//        val dueDay = _state.value.dueDay > _state.value.closingDay && _state.value.dueDay < 31
//
//        return name && limit && closingDay && dueDay
        return true
    }

    private fun inputFeedbackError() {
//        val inputError = when {
//            !isValidName(_state.value.name) -> NAME
//            _state.value.limit <= 0f -> LIMIT
//            _state.value.closingDay !in 0..31 -> CLOSING_DAY
//            _state.value.dueDay !in 0..31 -> DUE_DAY
//            else -> null
//        }
//
//        _state.update { it.copy(inputError = inputError) }
    }

    private fun setCurrentSheetType(type: SheetType) {
        _state.update { it.copy(currentSheetType = type) }
    }

    private fun clearBottomSheet() {
        _state.update { it.copy(currentSheetType = SheetType.EMPTY) }
    }

}