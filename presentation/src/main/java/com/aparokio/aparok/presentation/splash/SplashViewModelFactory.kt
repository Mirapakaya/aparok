package com.aparokio.aparok.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.CrashReporter
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.auth.interactor.CheckAuthorizationStatus
import com.aparokio.aparok.domain.auth.interactor.GetLastOpenedObject
import com.aparokio.aparok.domain.auth.interactor.LaunchAccount
import com.aparokio.aparok.domain.auth.interactor.LaunchWallet
import com.aparokio.aparok.domain.deeplink.PendingIntentStore
import com.aparokio.aparok.domain.launch.PreferredSpaceIdHolder
import com.aparokio.aparok.domain.misc.DeepLinkResolver
import com.aparokio.aparok.domain.misc.LocaleProvider
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.search.SearchObjects
import com.aparokio.aparok.domain.page.CreateObjectByTypeAndTemplate
import com.aparokio.aparok.domain.spaces.GetLastOpenedSpace
import com.aparokio.aparok.domain.subscriptions.GlobalSubscriptionManager
import com.aparokio.aparok.domain.vault.SetVaultSearchHighlightSeen
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.auth.account.MigrationHelperDelegate
import javax.inject.Inject

/**
 * Created by Konstantin Ivanov
 * email :  ki@agileburo.com
 * on 2019-10-21.
 */
class SplashViewModelFactory @Inject constructor(
    private val checkAuthorizationStatus: CheckAuthorizationStatus,
    private val launchAccount: LaunchAccount,
    private val launchWallet: LaunchWallet,
    private val analytics: Analytics,
    private val getLastOpenedObject: GetLastOpenedObject,
    private val crashReporter: CrashReporter,
    private val localeProvider: LocaleProvider,
    private val spaceManager: SpaceManager,
    private val analyticSpaceHelperDelegate: AnalyticSpaceHelperDelegate,
    private val globalSubscriptionManager: GlobalSubscriptionManager,
    private val getLastOpenedSpace: GetLastOpenedSpace,
    private val createObjectByTypeAndTemplate: CreateObjectByTypeAndTemplate,
    private val spaceViews: SpaceViewSubscriptionContainer,
    private val migration: MigrationHelperDelegate,
    private val deepLinkResolver: DeepLinkResolver,
    private val pendingIntentStore: PendingIntentStore,
    private val searchObjects: SearchObjects,
    private val preferredSpaceIdHolder: PreferredSpaceIdHolder,
    private val setVaultSearchHighlightSeen: SetVaultSearchHighlightSeen
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SplashViewModel(
            checkAuthorizationStatus = checkAuthorizationStatus,
            launchAccount = launchAccount,
            launchWallet = launchWallet,
            analytics = analytics,
            getLastOpenedObject = getLastOpenedObject,
            crashReporter = crashReporter,
            localeProvider = localeProvider,
            spaceManager = spaceManager,
            analyticSpaceHelperDelegate = analyticSpaceHelperDelegate,
            globalSubscriptionManager = globalSubscriptionManager,
            getLastOpenedSpace = getLastOpenedSpace,
            createObjectByTypeAndTemplate = createObjectByTypeAndTemplate,
            spaceViews = spaceViews,
            migration = migration,
            deepLinkResolver = deepLinkResolver,
            pendingIntentStore = pendingIntentStore,
            searchObjects = searchObjects,
            preferredSpaceIdHolder = preferredSpaceIdHolder,
            setVaultSearchHighlightSeen = setVaultSearchHighlightSeen
        ) as T
}
