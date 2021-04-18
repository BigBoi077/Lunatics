package cegepst.example.lunatics.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Achievement
import cegepst.example.lunatics.views.adapters.AchievementAdapter

private const val PARAM_GAME_ID = "gameId"

class GameAchievementsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var achievements: ArrayList<Achievement>
    private lateinit var adapter: AchievementAdapter
    private var gameId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameId = it.getString(PARAM_GAME_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.adapter = AchievementAdapter(achievements)
        this.recyclerView = view.findViewById(R.id.list)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(view.context)
        this.adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, achievements: ArrayList<Achievement>) =
            GameAchievementsFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_GAME_ID, param1.toString())
                }
                this.achievements = achievements
            }
    }
}