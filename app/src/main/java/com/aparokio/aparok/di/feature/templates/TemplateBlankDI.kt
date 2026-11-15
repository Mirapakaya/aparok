package com.aparokio.aparok.di.feature.templates

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.misc.DateProvider
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.primitives.FieldParser
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.editor.render.DefaultBlockViewRenderer
import com.aparokio.aparok.presentation.editor.toggle.ToggleStateHolder
import com.aparokio.aparok.presentation.templates.TemplateBlankViewModelFactory
import com.aparokio.aparok.presentation.widgets.collection.ResourceProvider
import com.aparokio.aparok.providers.DefaultCoverImageHashProvider
import com.aparokio.aparok.ui.templates.TemplateBlankFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [TemplateBlankModule::class],
    dependencies = [TemplateBlankDependencies::class]
)
@TemplateBlankScope
interface TemplateBlankComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: TemplateBlankDependencies): TemplateBlankComponent
    }

    fun inject(fragment: TemplateBlankFragment)
}

@Module
object TemplateBlankModule {

    @JvmStatic
    @TemplateBlankScope
    @Provides
    fun provideToggleHolder(): ToggleStateHolder = ToggleStateHolder.Default()

    @JvmStatic
    @TemplateBlankScope
    @Provides
    fun provideCoverImageHashProvider(): CoverImageHashProvider = DefaultCoverImageHashProvider()

    @JvmStatic
    @TemplateBlankScope
    @Provides
    fun provideViewModelFactory(
        renderer: DefaultBlockViewRenderer,
        storeOfRelations: StoreOfRelations,
        storeOfObjectTypes: StoreOfObjectTypes,
        fieldParser: FieldParser
    ): ViewModelProvider.Factory = TemplateBlankViewModelFactory(
        renderer = renderer,
        storeOfRelations = storeOfRelations,
        storeOfObjectTypes = storeOfObjectTypes,
        fieldParser = fieldParser
    )

    @Module
    interface Declarations {

        @TemplateBlankScope
        @Binds
        fun bindRenderer(
            defaultRenderer: DefaultBlockViewRenderer
        ): DefaultBlockViewRenderer
    }
}

interface TemplateBlankDependencies : ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun storeOfRelations(): StoreOfRelations
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun dateProvider(): DateProvider
    fun fieldsProvider(): FieldParser
    fun resourceProvider(): ResourceProvider
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TemplateBlankScope