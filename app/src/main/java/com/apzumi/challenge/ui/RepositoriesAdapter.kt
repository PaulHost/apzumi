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
        holder.setRepoIcon(item.isGitHub)
    }

    override fun getItemCount(): Int = values.size

    fun sortByAlphabet(isByAlphabet: Boolean = true) {
        if (isByAlphabet) values.sortBy { it.name }
        else values.sortBy { it.user }
    }

    fun setList(list: List<RepositoryModel>?) {
        if (list?.isNotEmpty() ?: false) {
            values.clear()
            values.addAll(list!!)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val user: TextView = view.findViewById(R.id.tv_user)
        val avatar: AppCompatImageView = view.findViewById(R.id.iv_avatar)
        val icon: AppCompatImageView = view.findViewById(R.id.iv_repo)

        fun setAvatar(url: String) = Picasso.get()
            .load(url)
            .resize(50, 50)
            .into(avatar)

        fun setRepoIcon(isGitHub: Boolean) {
            if (isGitHub) icon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_github))
            else Picasso.get()
                .load("https://pics.freeicons.io/uploads/icons/png/17054170491556105311-512.png")
                .into(icon)
        }
    }
}