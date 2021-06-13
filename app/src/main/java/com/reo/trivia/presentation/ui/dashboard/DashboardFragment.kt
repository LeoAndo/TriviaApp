package com.reo.trivia.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reo.trivia.R
import com.reo.trivia.databinding.FragmentDashboardBinding
import com.reo.trivia.presentation.BaseFragment
import javax.inject.Inject

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        binding.fragment = this@DashboardFragment
        binding.viewModel = viewModel

        viewModel.resultTrivia.observe(
            viewLifecycleOwner,
            { binding.textResultTrivia.text = it })
    }

    fun onClickButtonGetRandomTrivia() {
        viewModel.getRandomTrivia()
    }

    fun onClickButtonGetRandomYearTrivia() {
        viewModel.getRandomYearTrivia()
    }

    fun onClickButtonGetRandomMonthTrivia() {
        viewModel.getRandomMonthTrivia()
    }

    fun onClickButtonGetRandomDateTrivia() {
        viewModel.getRandomDateTrivia()
    }
}
