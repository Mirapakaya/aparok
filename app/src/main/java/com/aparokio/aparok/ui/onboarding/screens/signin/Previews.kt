package com.aparokio.aparok.ui.onboarding.screens.signin

import androidx.compose.runtime.Composable
import com.aparokio.aparok.core_ui.common.DefaultPreviews
import com.aparokio.aparok.presentation.onboarding.login.OnboardingMnemonicLoginViewModel.SetupState

@DefaultPreviews
@Composable
private fun RecoveryScreenPreview() {
    RecoveryScreen(
        onBackClicked = {},
        onNextClicked = {},
        onActionDoneClicked = {},
        onScanQrClicked = {},
        state = SetupState.Idle,
        onEnterMyVaultClicked = {},
        onDebugAccountTraceClicked = {},
        onRetryMigrationClicked = {},
        onStartMigrationClicked = {}
    )
}

@DefaultPreviews
@Composable
private fun RecoveryScreenLoadingPreview() {
    RecoveryScreen(
        onBackClicked = {},
        onNextClicked = {},
        onActionDoneClicked = {},
        onScanQrClicked = {},
        state = SetupState.InProgress,
        onEnterMyVaultClicked = {},
        onDebugAccountTraceClicked = {},
        onRetryMigrationClicked = {},
        onStartMigrationClicked = {}
    )
}