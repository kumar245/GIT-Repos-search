package dev.chaitan.gitusersearch.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dev.chaitan.gitusersearch.MainActivity
import dev.chaitan.gitusersearch.R
import dev.chaitan.gitusersearch.databinding.FragmentUserSearchBinding
import dev.chaitan.gitusersearch.model.GitUser
import dev.chaitan.gitusersearch.rx.EventBus
import dev.chaitan.gitusersearch.rx.event.RxEvent
import dev.chaitan.gitusersearch.ui.RecyclerItemClickListener
import dev.chaitan.gitusersearch.ui.base.BaseFragment
import dev.chaitan.gitusersearch.ui.detail.GitUserDetailFragment

class UserSearchFragment :
    BaseFragment<FragmentUserSearchBinding, UserSearchViewModel>(),
    RecyclerItemClickListener {

    companion object {
        fun newInstance() = UserSearchFragment()
    }

    private val adapter = GitUserAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_user_search
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        mViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UserSearchViewModel::class.java)
    }

    override fun onItemClick(position: Int, view: View) {
        val user= mViewModel.userList.value!![position]
        val fragment=GitUserDetailFragment.newInstance(user.login)
        (activity as MainActivity).showFragment(fragment)
    }

    private fun initialize() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL
        mBinding.viewModel = mViewModel
        mBinding.userRv.layoutManager = layoutManager
        mBinding.userRv.adapter = adapter

        subscribeToUserData()
        subscribeToEvents()
        initSearchView()
    }

    private fun initSearchView(){
        mBinding.userSearchView.isIconified = false
        mBinding.userSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mViewModel.searchUser(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun subscribeToUserData() {
        mViewModel.userList
            .observe(this,
                Observer<List<GitUser>> { list -> adapter.addUsers(list) })
    }

    private fun subscribeToEvents() {
        compositeDisposable!!
            .add(
                EventBus.listen(RxEvent.EventError::class.java)
                    .subscribe({ showError(it) })
            )
    }
}
