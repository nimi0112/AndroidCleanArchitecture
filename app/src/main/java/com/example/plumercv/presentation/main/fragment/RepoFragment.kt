package com.example.plumercv.presentation.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.plumercv.R
import com.example.plumercv.adapter.ReposAdapter
import com.example.plumercv.presentation.main.ErrorState
import com.example.plumercv.presentation.main.InitialState
import com.example.plumercv.presentation.main.LoadingState
import com.example.plumercv.presentation.main.RepoFragmentState
import com.example.plumercv.presentation.main.RepoLoadedState
import com.example.plumercv.presentation.main.viewmodel.RepoFragmentViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Nimish Nandwana on 23/08/2021.
 * Description -
 */

class RepoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RepoFragmentViewModel

    private val repoListAdapter by lazy { ReposAdapter() }

    lateinit var etUsername: EditText
    lateinit var btnGetResults: Button
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var tvError: TextView

    private val stateObserver = Observer<RepoFragmentState> { state ->
        state?.let {
            when (state) {
                is InitialState -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    tvError.visibility = View.GONE
                }
                is LoadingState -> {
                    progressBar.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                }
                is RepoLoadedState -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                    repoListAdapter.updateItems(it.data)
                    hideSoftKeyboard()
                    if(it.data.isEmpty()){
                        tvError.visibility = View.VISIBLE
                        tvError.text = "User does not have any repos"
                    }
                }
                is ErrorState -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                    tvError.text = state.errorMessage
                }
            }
        }
    }

    /**
     * Called when a fragment is first attached to its context.
     * [.onCreate] will be called after this.
     */
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoFragmentViewModel::class.java)
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_fragment_new, container, false)
        initializeRecyclerView(view)
        return view
    }

    private fun initializeRecyclerView(view: View) {

        etUsername = view.findViewById(R.id.et_username)
        btnGetResults = view.findViewById(R.id.btn_get_results)
        recyclerView = view.findViewById(R.id.rcv_repos)
        progressBar = view.findViewById(R.id.progress_bar)
        tvError = view.findViewById(R.id.tv_error)

        recyclerView.apply {
            adapter = repoListAdapter
        }
        btnGetResults.setOnClickListener {
            viewModel.getRepoList(etUsername.text.toString())
            hideSoftKeyboard()
        }
    }

}

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideSoftKeyboard() {
    activity?.apply {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService<InputMethodManager>()
            inputMethodManager?.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}