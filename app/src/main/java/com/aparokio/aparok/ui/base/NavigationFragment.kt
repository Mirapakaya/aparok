package com.aparokio.aparok.ui.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.aparokio.aparok.BuildConfig
import com.aparokio.aparok.core_utils.common.EventWrapper
import com.aparokio.aparok.core_utils.ui.BaseFragment
import com.aparokio.aparok.presentation.navigation.AppNavigation
import com.aparokio.aparok.presentation.navigation.AppNavigation.Command
import timber.log.Timber

@Deprecated("Use NavigationRouter directly")
abstract class NavigationFragment<BINDING : ViewBinding>(
    @LayoutRes private val layout: Int
) : BaseFragment<BINDING>(layout) {

    private val navigationRouter by lazy {
        NavigationRouter((requireActivity() as AppNavigation.Provider).nav())
    }

    val navObserver = Observer<EventWrapper<Command>> { event ->
        event.getContentIfNotHandled()?.let {
            try {
                throttle { navigationRouter.navigate(it) }
            } catch (e: Exception) {
                Timber.e(e, "Navigation: $it")
                if (BuildConfig.DEBUG) {
                    throw e
                }
            }
        }
    }
}