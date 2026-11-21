package com.aparokio.aparok.feature_object_type.ui.create

import com.aparokio.aparok.core_models.ui.ObjectIcon

sealed class UiTypeSetupTitleAndIconState {

    data object Hidden : UiTypeSetupTitleAndIconState()

    sealed class Visible : UiTypeSetupTitleAndIconState() {

        data class CreateNewType(
            val icon: ObjectIcon.TypeIcon.Default,
            val initialTitle: String = "",
            val initialPlural: String = ""
        ) : Visible()

        data class EditType(
            val icon: ObjectIcon.TypeIcon,
            val initialTitle: String?,
            val initialPlural: String?
        ) : Visible()
    }
}