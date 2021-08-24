package com.example.plumercv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plumercv.R
import com.example.plumercv.adapter.ReposAdapter.ReposVh
import com.example.plumercv.model.RepoModel

/**
 * Created by Nimish Nandwana on 23/08/2021.
 * Description -
 */

class ReposAdapter : RecyclerView.Adapter<ReposVh>() {

    private val mlist = mutableListOf<RepoModel>()

    fun updateItems(list: List<RepoModel>) {
        mlist.clear()
        mlist.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposVh {
        return ReposVh(LayoutInflater.from(parent.context).inflate(R.layout.layout_repos, parent, false))
    }

    override fun onBindViewHolder(holder: ReposVh, position: Int) {
        holder.bindViews(mlist[position])
    }

    override fun getItemCount() = mlist.size

    class ReposVh(view: View) : RecyclerView.ViewHolder(view) {

        val repoName = view.findViewById<TextView>(R.id.tv_repo)

        fun bindViews(data: RepoModel) {
            repoName.text = data.fullName
        }
    }
}