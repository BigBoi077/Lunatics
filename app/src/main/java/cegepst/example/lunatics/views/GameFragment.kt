package cegepst.example.lunatics.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.viewModels.MainViewModel

private const val ARG_PROMPT_WELCOME = "prompt"

class GameFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: GameAdapter
    private var welcome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            welcome = it.getString(ARG_PROMPT_WELCOME)
        }
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.recyclerView = view.findViewById(R.id.listGames)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(view.context)
        view.findViewById<TextView>(R.id.promptPageWelcome).text = welcome
    }

    companion object {
        @JvmStatic
        fun newInstance(welcome: String, adapter: GameAdapter) =
                GameFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PROMPT_WELCOME, welcome)
                    }
                    this.adapter = adapter
                }
    }
}