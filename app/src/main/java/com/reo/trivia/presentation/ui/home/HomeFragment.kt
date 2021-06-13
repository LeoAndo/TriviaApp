package com.reo.trivia.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reo.trivia.R
import com.reo.trivia.databinding.FragmentHomeBinding
import com.reo.trivia.presentation.BaseFragment
import com.reo.trivia.presentation.ui.notifications.TriviaAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.fragment = this@HomeFragment
        binding.viewModel = viewModel

        viewModel.resultTrivia.observe(
            viewLifecycleOwner,
            { binding.textResultTrivia.text = it })
    }

    fun onClickButtonGetTrivia() {
        if (validate()) {
            viewModel.getTrivia()
        } else {
            Toast.makeText(requireContext(), "Please select month or date.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun validate(): Boolean {
        var validated = true

        val filledDateDropdown =
            binding.root.findViewById<AutoCompleteTextView>(R.id.filled_date_dropdown)
        val filledMonthDropdown =
            binding.root.findViewById<AutoCompleteTextView>(R.id.filled_month_dropdown)

        if (filledMonthDropdown.text.toString().isEmpty()
            && filledDateDropdown.text.toString().isEmpty()
        ) {
            validated = false
        }
        return validated
    }
}
