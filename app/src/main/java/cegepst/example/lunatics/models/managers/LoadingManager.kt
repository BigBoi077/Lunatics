package cegepst.example.lunatics.models.managers

import androidx.lifecycle.MutableLiveData
import cegepst.example.lunatics.models.baseModels.States

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