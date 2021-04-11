package cegepst.example.lunatics.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.viewModels.MainViewModel
import cegepst.example.lunatics.views.adapters.GameAdapter

private const val ARG_PROMPT_WELCOME = "prompt"

class GameFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var header: LinearLayout
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
        setScrollListener()
    }

    private fun setScrollListener() {
        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val animation = AnimationUtils.loadAnimation(view?.context, R.anim.item_animation_fall_up)
                if (LinearLayoutManager(view?.context).findFirstVisibleItemPosition() > 0) {
                    header.visibility = View.GONE
                    header.visibility = View.GONE
                    header.startAnimation(animation)
                } else {
                    header.visibility = View.VISIBLE
                    header.visibility = View.VISIBLE
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(welcome: String, adapter: GameAdapter, header: LinearLayout) =
                GameFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PROMPT_WELCOME, welcome)
                    }
                    this.adapter = adapter
                    this.header = header
                }
    }
}