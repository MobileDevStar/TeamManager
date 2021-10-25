package com.itdev.teammanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.itdev.teammanager.Injection
import com.itdev.teammanager.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = Injection.provideViewModelFactory(this)
        update_user_button.setOnClickListener { updateUserName() }
    }

    override fun onStart() {
        super.onStart()

        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
        disposable.add(viewModel.userName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({this.user_name.text = it} , {error -> Log.e(TAG, "Unable to get username", error)}))

    }

    override fun onStop() {
        super.onStop()

        // clear all the subscription
        disposable.clear()
    }

    private fun updateUserName() {
        val userName = user_name_input.text.toString()

        update_user_button.isEnabled = false

        disposable.add(viewModel.updateUserName(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({update_user_button.isEnabled = true}, {error -> Log.e(TAG, "Unable to update username", error)}))
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}