package com.aparokio.aparok.domain.layout

import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.domain.base.BaseUseCase
import com.aparokio.aparok.domain.base.Either

class GetSupportedObjectLayouts :
    BaseUseCase<List<ObjectType.Layout>, GetSupportedObjectLayouts.Params>() {

    override suspend fun run(params: Params) = safe {
        listOf(
            ObjectType.Layout.NOTE,
            ObjectType.Layout.BASIC,
            ObjectType.Layout.PROFILE,
            ObjectType.Layout.TODO,
        )
    }

    data class Params(val ctx: Id)
}