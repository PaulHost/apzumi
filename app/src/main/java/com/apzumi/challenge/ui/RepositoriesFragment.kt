package com.apzumi.challenge.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.apzumi.challenge.R
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment() {
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private val viewModel by viewModel<RepositoriesViewModel>()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        repositoriesAdapter = RepositoriesAdapter(context as NavigationListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repositories_list, container, false)
        setHasOptionsMenu(true)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = repositoriesAdapter
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.sort, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        repositoriesAdapter.sortByAlphabet()
        return super.onOptionsItemSelected(item)
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