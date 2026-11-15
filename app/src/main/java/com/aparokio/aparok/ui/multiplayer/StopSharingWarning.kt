package com.aparokio.aparok.ui.multiplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import com.aparokio.aparok.R
import com.aparokio.aparok.core_ui.foundation.Warning
import com.aparokio.aparok.core_utils.ui.BaseBottomSheetComposeFragment
import com.aparokio.aparok.ui.settings.typography

class StopSharingWarning : BaseBottomSheetComposeFragment() {

    var onAccepted: () -> Unit = {}
    var onCancelled: () -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme(typography = typography) {
                    Warning(
                        actionButtonText = stringResource(R.string.multiplayer_space_stop_sharing),
                        cancelButtonText = stringResource(R.string.cancel),
                        title = stringResource(R.string.multiplayer_space_stop_sharing),
                        subtitle = stringResource(R.string.multiplayer_space_stop_sharing_space_description),
                        onNegativeClick = {
                            onCancelled()
                            dismiss()
                        },
                        onPositiveClick = { onAccepted() },
                        isInProgress = false
                    )
                }
            }
        }
    }


    override fun injectDependencies() {
        // Do nothing
    }

    override fun releaseDependencies() {
        // Do nothing
    }
}