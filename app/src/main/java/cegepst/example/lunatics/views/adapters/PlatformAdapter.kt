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
import cegepst.example.lunatics.models.baseModels.Platform
import cegepst.example.lunatics.views.activities.GamesByPlatformActivity
import com.bumptech.glide.Glide

class PlatformAdapter(private val platforms: ArrayList<Platform>) :
    RecyclerView.Adapter<PlatformAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.platformImage)
        private val name: TextView = itemView.findViewById(R.id.platformName)
        private val released: TextView = itemView.findViewById(R.id.platformReleased)
        private val gameCount: TextView = itemView.findViewById(R.id.platformGameCount)
        private val button: ImageButton = itemView.findViewById(R.id.actionGetGamesByPlatform)

        @SuppressLint("SetTextI18n")
        fun setContent(platform: Platform) {
            Glide.with(itemView).load(platform.backgroundImage).centerCrop().into(image)
            name.text = platform.name
            released.text = "Released in ${platform.yearStart}"
            gameCount.text = "${platform.gameCount} games"
            button.setOnClickListener {
                val intent = Intent(itemView.context, GamesByPlatformActivity::class.java)
                intent.putExtra("platformId", platform.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_platform, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(platforms[position])
    }

    override fun getItemCount(): Int {
        return platforms.size
    }


}