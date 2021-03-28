package com.apzumi.challenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apzumi.challenge.R
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment() {
    private val repositoriesAdapter = RepositoriesAdapter()
    private val viewModel by viewModel<RepositoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repositories_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = repositoriesAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRepositories()
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner, repositoriesAdapter::setList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepositoriesFragment()
    }
}