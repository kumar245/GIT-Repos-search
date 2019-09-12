package dev.chaitan.gitusersearch.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dev.chaitan.gitusersearch.R
import dev.chaitan.gitusersearch.databinding.FragmentGitUserDetailBinding
import dev.chaitan.gitusersearch.model.Repo
import dev.chaitan.gitusersearch.rx.EventBus
import dev.chaitan.gitusersearch.rx.event.RxEvent
import dev.chaitan.gitusersearch.ui.base.BaseFragment

class GitUserDetailFragment : BaseFragment<FragmentGitUserDetailBinding, GitUserDetailViewModel>() {

    companion object {
        private val EXTRA_LOGIN = "extra_login"

        fun newInstance(login: String): GitUserDetailFragment {
            val fragment = GitUserDetailFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_LOGIN, login)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var adapter : RepoListAdapter
    private var login: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login = arguments!!.getString(EXTRA_LOGIN)
        initialize()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_git_user_detail
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GitUserDetailViewModel::class.java)
    }

    private fun initialize() {
        adapter = RepoListAdapter(mBinding.emptyTv)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL
        mBinding.viewModel = mViewModel
        mBinding.repoRv.layoutManager = layoutManager
        mBinding.repoRv.adapter = adapter
        subscribeToUserData()
        subscribeToEvents()
        initSearchView()
        mViewModel.getUserDetail(login!!)
    }

    private fun initSearchView(){
        mBinding.repoSv.isIconified = false
        mBinding.repoSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter(newText)
                return false
            }
        })
    }

    private fun subscribeToUserData() {
        mViewModel.repoList
            .observe(this,
                Observer<List<Repo>> { list -> adapter.setItems(list) })
    }

    private fun subscribeToEvents() {
        compositeDisposable!!
            .add(
                EventBus.listen(RxEvent.EventError::class.java)
                    .subscribe { showError(it) }
            )
    }
}