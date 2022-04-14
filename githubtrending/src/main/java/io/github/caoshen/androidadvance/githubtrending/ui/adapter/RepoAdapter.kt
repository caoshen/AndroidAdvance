package io.github.caoshen.androidadvance.githubtrending.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.caoshen.androidadvance.githubtrending.R
import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList

class RepoAdapter(private val repoList: RepoList) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo,
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = repoList.items.getOrNull(position)?.repo
    }

    override fun getItemCount(): Int {
        return repoList.count
    }
}