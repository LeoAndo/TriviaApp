package com.reo.trivia.presentation.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reo.trivia.R
import com.reo.trivia.databinding.FragmentNotificationsBinding
import com.reo.trivia.presentation.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NotificationsFragment : BaseFragment(R.layout.fragment_notifications) {

    private val adapter = TriviaAdapter()
    private lateinit var binding: FragmentNotificationsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NotificationsViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)

        binding.list.adapter = adapter
        viewModel.resultTrivia.observe(
            viewLifecycleOwner,
            { adapter.updateItems(it) })
    }
}
