package com.aparokio.aparok.core_ui.widgets

import android.content.Context
import android.util.AttributeSet
import com.aparokio.aparok.core_ui.features.objects.ObjectActionAdapter
import com.aparokio.aparok.presentation.objects.ObjectAction

class CollectionActionWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseActionWidget<ObjectAction>(context, attrs) {

    override fun provideAdapter() = ObjectActionAdapter { action -> actionListener(action) }
}