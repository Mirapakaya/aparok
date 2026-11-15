package com.aparokio.aparok.presentation.home

import com.aparokio.aparok.core_models.ObjectWrapper
import com.aparokio.aparok.domain.spaces.ResolveSpaceHomepage

object SpaceHomepageResolver {

    fun isExplicitObjectHomepage(homepage: String?): Boolean =
        !homepage.isNullOrEmpty() && homepage !in ResolveSpaceHomepage.HOMEPAGE_SPECIAL_CONSTANTS

    fun shouldSkipWidgetInjection(spaceView: ObjectWrapper.SpaceView?): Boolean =
        isExplicitObjectHomepage(spaceView?.homepage)
}
