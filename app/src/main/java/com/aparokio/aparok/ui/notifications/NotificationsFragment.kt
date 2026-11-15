package com.aparokio.aparok.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.aparokio.aparok.R
import com.aparokio.aparok.analytics.base.EventsDictionary
import com.aparokio.aparok.core_ui.notifications.NotificationsScreen
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.core_utils.ui.BaseBottomSheetComposeFragment
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.presentation.notifications.NotificationsViewModel
import com.aparokio.aparok.presentation.notifications.NotificationsViewModelFactory
import com.aparokio.aparok.ui.multiplayer.ShareSpaceFragment
import com.aparokio.aparok.ui.multiplayer.SpaceJoinRequestFragment
import com.aparokio.aparok.ui.settings.typography
import javax.inject.Inject
import timber.log.Timber

class NotificationsFragment : BaseBottomSheetComposeFragment() {

    @Inject
    lateinit var factory: NotificationsViewModelFactory

    private val vm by viewModels<NotificationsViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme(
                typography = typography
            ) {
                NotificationsScreen(
                    state = vm.state.collectAsStateWithLifecycle().value,
                    onActionButtonClick = vm::onNavigateToSpaceClicked,
                    onErrorButtonClick = vm::onErrorButtonClick,
                    onNotificationAction = vm::onNotificationAction
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        jobs += subscribe(vm.command) { command ->
            Timber.d("GalleryInstallationFragment command: $command")
            when (command) {
                is NotificationsViewModel.Command.Dismiss -> {
                    dismiss()
                }
                is NotificationsViewModel.Command.ViewSpaceJoinRequest -> {
                    runCatching {
                        findNavController().navigate(
                            R.id.spaceJoinRequestScreen,
                            SpaceJoinRequestFragment.args(
                                space = command.space,
                                member = command.member,
                                analyticsRoute = EventsDictionary.Routes.notification
                            )
                        )
                    }.onFailure {
                        Timber.e(it, "Error while navigation")
                    }
                }
                is NotificationsViewModel.Command.ViewSpaceLeaveRequest -> {
                    runCatching {
                        findNavController().navigate(
                            R.id.shareSpaceScreen,
                            ShareSpaceFragment.args(space = command.space)
                        )
                    }.onFailure {
                        Timber.e(it, "Error while navigation")
                    }
                }
                is NotificationsViewModel.Command.NavigateToSpace -> {
                    //TODO: implement navigation
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    override fun injectDependencies() {
        componentManager().notificationsComponent.get().inject(this)
    }

    override fun releaseDependencies() {
        componentManager().notificationsComponent.release()
    }
}