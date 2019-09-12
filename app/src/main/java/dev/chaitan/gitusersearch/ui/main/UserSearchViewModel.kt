package dev.chaitan.gitusersearch.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import dev.chaitan.gitusersearch.api.repository.GitRepository
import dev.chaitan.gitusersearch.model.GitUser
import dev.chaitan.gitusersearch.model.response.UserSearchResponse
import dev.chaitan.gitusersearch.rx.EventBus
import dev.chaitan.gitusersearch.rx.event.RxEvent
import dev.chaitan.gitusersearch.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserSearchViewModel @Inject
constructor(gitRepository: GitRepository) : BaseViewModel() {

    val isListEmpty = ObservableBoolean(false)
    val userList = MutableLiveData<List<GitUser>>()
    private val gitRepository = gitRepository

    fun searchUser(keyWord: String){
        compositeDisposable.add(
            gitRepository.searchUser(keyWord,"repositories", "desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({onUserSearchSuccess(it)} , {onError(it)} ))

    }

    private fun onUserSearchSuccess(response: UserSearchResponse){
        userList.value = response.items
        isListEmpty.set(response.items.isEmpty())
    }

    private fun onError(throwable: Throwable){
        EventBus.publish(RxEvent.EventError(throwable))
    }
}
