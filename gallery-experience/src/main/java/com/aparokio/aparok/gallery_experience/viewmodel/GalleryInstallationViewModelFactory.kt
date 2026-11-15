package com.aparokio.aparok.gallery_experience.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.config.ConfigStorage
import com.aparokio.aparok.domain.gallery_experience.DownloadGalleryManifest
import com.aparokio.aparok.domain.gallery_experience.ImportExperience
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.multiplayer.UserPermissionProvider
import com.aparokio.aparok.domain.spaces.CreateSpace
import com.aparokio.aparok.domain.spaces.GetSpaceViews
import com.aparokio.aparok.domain.workspace.EventProcessImportChannel
import com.aparokio.aparok.presentation.spaces.SpaceGradientProvider
import javax.inject.Inject

class GalleryInstallationViewModelFactory @Inject constructor(
    private val viewModelParams: GalleryInstallationViewModel.ViewModelParams,
    private val downloadGalleryManifest: DownloadGalleryManifest,
    private val importExperience: ImportExperience,
    private val analytics: Analytics,
    private val getSpaceViews: GetSpaceViews,
    private val createSpace: CreateSpace,
    private val urlBuilder: UrlBuilder,
    private val spaceGradientProvider: SpaceGradientProvider,
    private val  userPermissionProvider: UserPermissionProvider,
    private val eventProcessChannel: EventProcessImportChannel,
    private val configStorage: ConfigStorage
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryInstallationViewModel(
            viewModelParams = viewModelParams,
            downloadGalleryManifest = downloadGalleryManifest,
            importExperience = importExperience,
            analytics = analytics,
            getSpaceViews = getSpaceViews,
            urlBuilder = urlBuilder,
            spaceGradientProvider = spaceGradientProvider,
            createSpace = createSpace,
            userPermissionProvider = userPermissionProvider,
            eventProcessChannel = eventProcessChannel,
            configStorage = configStorage
        ) as T
    }
}