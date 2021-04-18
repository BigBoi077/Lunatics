package cegepst.example.lunatics.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Achievement
import com.bumptech.glide.Glide

class AchievementAdapter(private val achievements: List<Achievement>) :
    RecyclerView.Adapter<AchievementAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.achievementImage)
        private val name = itemView.findViewById<TextView>(R.id.achievementName)
        private val description = itemView.findViewById<TextView>(R.id.achievementDescription)
        private val percentage = itemView.findViewById<TextView>(R.id.achievementSuccessPercentage)

        @SuppressLint("SetTextI18n")
        fun setContent(achievement: Achievement) {
            Glide.with(itemView).load(achievement.imageUrl).override(500, 500).centerCrop().into(image)
            name.text = achievement.name
            description.text = achievement.description
            percentage.text = "${achievement.successPercentage}%"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_achievement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AchievementAdapter.ViewHolder, position: Int) {
        holder.setContent(achievements[position])
    }

    override fun getItemCount(): Int {
        return achievements.size
    }
}