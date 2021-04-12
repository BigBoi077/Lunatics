package cegepst.example.lunatics.views.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.models.baseModels.Platform
import cegepst.example.lunatics.views.activities.GameAchievementActivity
import cegepst.example.lunatics.views.activities.MainActivity
import cegepst.example.lunatics.views.activities.SameSeriesActivity
import cegepst.example.lunatics.views.adapters.PlatformAdapter
import com.bumptech.glide.Glide
import com.google.gson.Gson

private const val ARG_GAME_ID = "gameId"

class SingleGameFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlatformAdapter
    private lateinit var game: Game
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent(game)
    }

    @SuppressLint("SetTextI18n")
    fun setContent(game: Game) {
        this.adapter = PlatformAdapter(game.platforms as ArrayList<Platform>)
        this.recyclerView = view!!.findViewById(R.id.listPlatforms)
        this.recyclerView.adapter = PlatformAdapter(game.platforms)
        this.recyclerView.layoutManager = LinearLayoutManager(view!!.context)

        Log.d("PLATFORM", game.platforms.size.toString())
        Log.d("GAME", Gson().toJson(game))

        this.adapter.notifyDataSetChanged()
        Glide.with(view!!.context).load(game.imageUrl).into(view?.findViewById(R.id.gameImage)!!)
        view!!.findViewById<TextView>(R.id.gameName).text = game.name
        view!!.findViewById<TextView>(R.id.gameRating).text = "Rating | ${game.rating}"
        view!!.findViewById<TextView>(R.id.gameWebsite).text = game.website
        setOnClickEvents(game)
    }

    private fun setOnClickEvents(game: Game) {
        view!!.findViewById<TextView>(R.id.gameWebsite).setOnClickListener {
            changeActivity(Intent(Intent.ACTION_VIEW, Uri.parse(game.website)))
        }
        view!!.findViewById<Button>(R.id.actionSameLineup).setOnClickListener {
            changeActivity(
                Intent(view?.context, SameSeriesActivity::class.java).putExtra(
                    "gameId",
                    game.id
                )
            )
        }
        view!!.findViewById<Button>(R.id.actionTrophies).setOnClickListener {
            changeActivity(
                    Intent(view?.context, GameAchievementActivity::class.java).putExtra(
                            "gameId",
                            game.id
                    )
            )
        }
        view!!.findViewById<Button>(R.id.actionReturn).setOnClickListener {
            changeActivity(Intent(view?.context, MainActivity::class.java))
        }
    }

    private fun changeActivity(intent: Intent) {
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(gameId: Int, game: Game) =
            SingleGameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GAME_ID, gameId.toString())
                }
                this.game = game
            }
    }
}