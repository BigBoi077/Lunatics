package cegepst.example.lunatics.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.views.adapters.PlatformAdapter

class PlatformFragment : Fragment() {

    private lateinit var lambda: () -> Unit
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: PlatformAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_platform, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.recyclerView = view.findViewById(R.id.listPlatforms)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(view.context)
        setScrollListener()
    }

    private fun setScrollListener() {
        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    lambda()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(adapter: PlatformAdapter, lambda: () -> Unit) =
            PlatformFragment().apply {
                this.adapter = adapter
                this.lambda = lambda
            }
    }
}