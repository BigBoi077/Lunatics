package cegepst.example.lunatics.views.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Genre
import cegepst.example.lunatics.views.activities.GamesByGenreActivity
import com.bumptech.glide.Glide

class GenreAdapter(private val genres: ArrayList<Genre>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.genreImage)
        private val name: TextView = itemView.findViewById(R.id.genreName)
        private val gameCount: TextView = itemView.findViewById(R.id.genreGameCount)
        private val button: ImageButton = itemView.findViewById(R.id.actionGetGamesByGenre)

        @SuppressLint("SetTextI18n")
        fun setContent(genre: Genre) {
            Glide.with(itemView).load(genre.imageUrl).centerCrop().into(image)
            name.text = genre.name
            gameCount.text = "${genre.gameCount} games"
            button.setOnClickListener {
                val intent = Intent(itemView.context, GamesByGenreActivity::class.java)
                intent.putExtra("genreId", genre.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}
