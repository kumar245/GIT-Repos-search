package dev.chaitan.gitusersearch.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val isLoading = ObservableBoolean(false)
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}