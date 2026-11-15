package com.aparokio.aparok.domain.widgets

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.FlowInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import com.aparokio.aparok.core_models.WidgetSections
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Use-case for observing widget sections configuration for a space.
 * Returns the order and visibility settings for home screen sections.
 */
class ObserveWidgetSections @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : FlowInteractor<ObserveWidgetSections.Params, WidgetSections>(dispatchers.io) {

    override fun build(): Flow<WidgetSections> {
        throw UnsupportedOperationException("Use build(params) instead")
    }

    override fun build(params: Params): Flow<WidgetSections> {
        return userSettingsRepository
            .observeWidgetSections(params.spaceId)
            .catch { emit(WidgetSections.default()) }
    }

    data class Params(val spaceId: SpaceId)
}
