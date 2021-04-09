package cegepst.example.lunatics.views

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.Game
import com.bumptech.glide.Glide

class GameAdapter(private val games: ArrayList<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.gameImage)
        private val name: TextView = itemView.findViewById(R.id.gameName)
        private val rating: TextView = itemView.findViewById(R.id.gameRating)
        private val metacritic: TextView = itemView.findViewById(R.id.gameMetacriticRating)
        private val released: TextView = itemView.findViewById(R.id.gameReleasedDate)
        private val button: ImageButton = itemView.findViewById(R.id.actionGetSingleGame)

        fun setContent(game: Game) {
            Glide.with(itemView).load(game.imageUrl).into(image)
            name.text = game.name
            rating.text = game.rating.toString()
            metacritic.text = game.metacritic.toString()
            released.text = game.released
            button.setOnClickListener {
                val intent = Intent(itemView.context, SingleGameActivity::class.java)
                intent.putExtra("gameId", game.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

}