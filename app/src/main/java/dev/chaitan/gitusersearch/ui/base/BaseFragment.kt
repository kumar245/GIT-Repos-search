package dev.chaitan.gitusersearch.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dev.chaitan.gitusersearch.di.ViewModelFactory
import dev.chaitan.gitusersearch.rx.event.RxEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var mBinding: DB
    protected lateinit var mViewModel: VM
    protected var compositeDisposable: CompositeDisposable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return mBinding.root
    }

    override fun onDetach() {
        super.onDetach()
        compositeDisposable!!.dispose()
    }

    fun showError(it: RxEvent.EventError) {
        val dialog = AlertDialog.Builder(context!!)
            .setMessage(it.throwable.message)
            .setPositiveButton("OK")
            { dialogInterface: DialogInterface?, _: Int ->
                dialogInterface!!.dismiss()
            }
        dialog.create().show()
    }
}