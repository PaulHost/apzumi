package com.apzumi.challenge.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.apzumi.challenge.R
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class InfoFragment : Fragment() {

    private val viewModel: RepositoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.info_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString(REPOSITORY_NAME, "")
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner, {
            it?.first { it.name == name }?.let { repository ->
                (view.findViewById(R.id.tv_name) as TextView).text = repository.name
                (view.findViewById(R.id.tv_user) as TextView).text = repository.user
                (view.findViewById(R.id.tv_description) as TextView).text = repository.details
                Picasso.get()
                    .load(repository.avatar)
                    .resize(50, 50)
                    .into(view.findViewById(R.id.iv_avatar) as ImageView)
            }
        })
    }

    companion object {
        private const val REPOSITORY_NAME = "repo_name"
        fun newInstance(name: String) = InfoFragment().apply {
            arguments = Bundle().apply { putString(REPOSITORY_NAME, name) }
        }
    }

}