package cegepst.example.lunatics.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cegepst.example.lunatics.R

private const val ARG_GAME_ID = "gameId"

class SingleGameFragment : Fragment() {
    private var gameId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameId = it.getInt(ARG_GAME_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_game, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingleGameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GAME_ID, param1)
                }
            }
    }
}