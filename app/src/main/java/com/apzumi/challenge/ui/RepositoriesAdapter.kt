package com.apzumi.challenge.ui

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.apzumi.challenge.R
import com.apzumi.challenge.data.model.RepositoryModel
import com.squareup.picasso.Picasso

class RepositoriesAdapter(
    private val values: MutableList<RepositoryModel> = mutableListOf()
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.user.text = item.user
        holder.setAvatar(item.avatar)
    }

    override fun getItemCount(): Int = values.size

    fun setList(list: List<RepositoryModel>) {
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val user: TextView = view.findViewById(R.id.tv_user)
        val avatar: AppCompatImageView = view.findViewById(R.id.iv_avatar)

        fun setAvatar(url: String) = Picasso.get()
            .load(url)
            .resize(50, 50)
            .into(avatar)

    }
}