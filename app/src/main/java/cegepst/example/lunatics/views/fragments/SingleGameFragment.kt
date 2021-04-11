package cegepst.example.lunatics.views.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Game
import cegepst.example.lunatics.views.activities.GameTrophyActivity
import cegepst.example.lunatics.views.activities.MainActivity
import cegepst.example.lunatics.views.activities.SameSeriesActivity
import com.bumptech.glide.Glide

private const val ARG_GAME_ID = "gameId"

class SingleGameFragment : Fragment() {

    private val name: TextView = view!!.findViewById(R.id.gameName)
    private val rating: TextView = view!!.findViewById(R.id.gameRating)
    private val website: TextView = view!!.findViewById(R.id.gameWebsite)
    private val released: TextView = view!!.findViewById(R.id.gameReleasedDate)
    private val sameSeries: ImageButton = view!!.findViewById(R.id.actionGetSingleGame)
    private val trophies: ImageButton = view!!.findViewById(R.id.actionGetSingleGame)
    private val goBack: ImageButton = view!!.findViewById(R.id.actionGetSingleGame)

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

    @SuppressLint("SetTextI18n")
    fun setContent(game: Game) {
        Glide.with(this).load(game.imageUrl).into(view?.findViewById(R.id.gameImage))
        name.text = game.name
        rating.text = "Rating | ${game.rating}"
        website.text = game.website
        released.text = "Released : ${game.released}"
        setOnClickEvents(game)
    }

    private fun setOnClickEvents(game: Game) {
        website.setOnClickListener {
            changeActivity(Intent(Intent.ACTION_VIEW, Uri.parse(game.website)))
        }
        sameSeries.setOnClickListener {
            changeActivity(Intent(view?.context, SameSeriesActivity::class.java).putExtra("gameId", game.id))
        }
        trophies.setOnClickListener {
            changeActivity(Intent(view?.context, GameTrophyActivity::class.java).putExtra("gameId", game.id))
        }
        goBack.setOnClickListener {
            changeActivity(Intent(view?.context, MainActivity::class.java))
        }
    }

    private fun changeActivity(intent: Intent) {
        startActivity(intent)
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