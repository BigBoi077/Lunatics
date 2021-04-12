package cegepst.example.lunatics.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.lunatics.R
import cegepst.example.lunatics.models.baseModels.Platform

class PlatformAdapter(private val platforms: ArrayList<Platform>) :
        RecyclerView.Adapter<PlatformAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.platformName)

        @SuppressLint("SetTextI18n")
        fun setContent(platform: Platform) {
            name.text = platform.content.name
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