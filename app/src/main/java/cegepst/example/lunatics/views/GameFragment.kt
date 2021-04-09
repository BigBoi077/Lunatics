package cegepst.example.lunatics.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.viewModels.MainViewModel

private const val ARG_PROMPT_WELCOME = "prompt"

class GameFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GameAdapter
    private var welcome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            welcome = it.getString(ARG_PROMPT_WELCOME)
        }
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        adapter = viewModel.adapter
        view?.findViewById<RecyclerView>(R.id.listGames)?.layoutManager = LinearLayoutManager(view?.context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.listGames).adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(welcome: String) =
                GameFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PROMPT_WELCOME, welcome)
                    }
                }
    }
}