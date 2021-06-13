package com.reo.trivia.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.reo.trivia.di.Injectable

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), Injectable