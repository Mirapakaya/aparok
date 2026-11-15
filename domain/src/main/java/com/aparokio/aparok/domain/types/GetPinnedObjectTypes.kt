package com.aparokio.aparok.domain.types

import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_models.primitives.TypeId
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.base.FlowInteractor
import com.aparokio.aparok.domain.config.UserSettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow

class GetPinnedObjectTypes @Inject constructor(
    private val repo: UserSettingsRepository,
    dispatchers: AppCoroutineDispatchers
) : FlowInteractor<GetPinnedObjectTypes.Params, List<TypeId>>(dispatchers.io) {

    override fun build(): Flow<List<TypeId>> {
        throw UnsupportedOperationException()
    }

    override fun build(params: Params) = repo.getPinnedObjectTypes(
        space = params.space
    ).catch { emit(emptyList()) }

    class Params(val space: SpaceId)
}