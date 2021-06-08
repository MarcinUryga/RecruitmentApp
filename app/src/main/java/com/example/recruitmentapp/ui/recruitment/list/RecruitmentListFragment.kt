package com.example.recruitmentapp.ui.recruitment.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recruitmentapp.EventObserver
import com.example.recruitmentapp.R
import com.example.recruitmentapp.databinding.FragmentTasksBinding
import com.example.recruitmentapp.source.Result
import com.example.recruitmentapp.ui.recruitment.details.DetailsFragmentArgs
import com.example.recruitmentapp.ui.recruitment.list.adapter.TasksAdapter
import com.example.recruitmentapp.ui.recruitment.list.model.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecruitmentListFragment : Fragment() {
    private val viewModel by viewModels<RecruitmentListViewModel>()
    private var _viewDataBinding: FragmentTasksBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewDataBinding = FragmentTasksBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupTasksAdapter()
        observeLiveData()
    }

    private fun setupTasksAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            tasksAdapter = TasksAdapter(viewModel)
            viewDataBinding.tasksView.recyclerView.adapter = tasksAdapter
        } else {
            Log.d(TAG, "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun observeLiveData() {
        viewModel.tasks.observe(viewLifecycleOwner, {
            if (it is Result.Success && it.data.isNotEmpty()) tasksAdapter.submitList(it.data)
        })

        viewModel.openTaskDetailsEvent.observe(viewLifecycleOwner, EventObserver {
            openTaskDetails(it)
        })
    }

    private fun openTaskDetails(task: Task) {
        val navHostFragmentTablet: Fragment? =
            childFragmentManager.findFragmentById(R.id.navHostFragmentTablet)

        navHostFragmentTablet?.let {
            openTaskDetailsOnTablet(it, task)
        } ?: run {
            openTaskDetailsOnPhone(task)
        }
    }

    private fun openTaskDetailsOnPhone(task: Task) {
        val action = RecruitmentListFragmentDirections
            .startDetailsFragment(task.title, task.descriptionUrl)
        findNavController().navigate(action)
    }

    private fun openTaskDetailsOnTablet(fragment: Fragment, task: Task) {
        val args = DetailsFragmentArgs.Builder()
            .setTitle(task.title)
            .setUrl(task.descriptionUrl)
            .build()
            .toBundle()
        fragment.findNavController().navigate(R.id.startDetailsFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding.tasksView.recyclerView.adapter = null
        _viewDataBinding = null
    }

    companion object {
        private const val TAG = "RecruitmentListFragment"
    }
}