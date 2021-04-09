package cegepst.example.lunatics.models

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class ErrorManager {

    private lateinit var errorContainer: TextView
    private lateinit var list: RecyclerView
    private var error = ""

    fun reset() {
        toggleVisibility()
    }

    fun raiseError(content: String) {
        error = content
        toggleVisibility()
        errorContainer.text = error
    }

    fun setComponents(component: TextView, recyclerView: RecyclerView) {
        errorContainer = component
        list = recyclerView
    }

    private fun toggleVisibility() {
        if (errorContainer.isVisible) {
            errorContainer.visibility = View.GONE
        } else {
            errorContainer.visibility = View.VISIBLE
        }
    }
}