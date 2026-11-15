package com.aparokio.aparok.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import com.aparokio.aparok.R
import com.aparokio.aparok.core_ui.extensions.throttledClick
import com.aparokio.aparok.core_ui.foundation.AlertConfig
import com.aparokio.aparok.core_ui.foundation.BUTTON_SECONDARY
import com.aparokio.aparok.core_ui.foundation.GenericAlert
import com.aparokio.aparok.core_utils.ui.BaseBottomSheetComposeFragment
import com.aparokio.aparok.ui.settings.typography

class AlertImportExperienceUnsupported : BaseBottomSheetComposeFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme(typography = typography) {
                    GenericAlert(
                        onFirstButtonClicked = throttledClick(
                            onClick = { dismiss() }
                        ),
                        config = AlertConfig.WithOneButton(
                            title = stringResource(id = R.string.alert_gallery_unsupported_title),
                            description = stringResource(R.string.alert_gallery_unsupported_description),
                            firstButtonText = stringResource(R.string.button_close),
                            firstButtonType = BUTTON_SECONDARY,
                            icon = R.drawable.ic_popup_alert_56
                        )
                    )
                }
            }
        }
    }
}