package com.aparokio.aparok.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.library.StorelessSubscriptionContainer
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.workspace.InterceptFileLimitEvents
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.domain.workspace.SpacesUsageInfo
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import javax.inject.Inject

class SpacesStorageViewModelFactory @Inject constructor(
    private val vmParams: SpacesStorageViewModel.VmParams,
    private val analytics: Analytics,
    private val spaceManager: SpaceManager,
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val spacesUsageInfo: SpacesUsageInfo,
    private val interceptFileLimitEvents: InterceptFileLimitEvents,
    private val storelessSubscriptionContainer: StorelessSubscriptionContainer,
    private val getAccount: GetAccount,
    private val membershipProvider: MembershipProvider,
    private val userPermissionProvider: UserPermissionProvider,
    private val configStorage: ConfigStorage
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = SpacesStorageViewModel(
        analytics = analytics,
        spaceManager = spaceManager,
        appCoroutineDispatchers = appCoroutineDispatchers,
        spacesUsageInfo = spacesUsageInfo,
        interceptFileLimitEvents = interceptFileLimitEvents,
        storelessSubscriptionContainer = storelessSubscriptionContainer,
        getAccount = getAccount,
        membershipProvider = membershipProvider,
        userPermissionProvider = userPermissionProvider,
        configStorage = configStorage,
        vmParams = vmParams
    ) as T
}