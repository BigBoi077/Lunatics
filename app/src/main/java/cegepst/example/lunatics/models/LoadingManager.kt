package cegepst.example.lunatics.models

import androidx.lifecycle.MutableLiveData

class LoadingManager {

    private var state = MutableLiveData(States.NONE)

    fun reset() {
        state.value = States.NONE
    }

    fun isLoading() {
        state.value = States.LOADING
    }

    fun isSuccess() {
        state.value = States.SUCCESS
    }

    fun isError() {
        state.value = States.ERROR
    }
}