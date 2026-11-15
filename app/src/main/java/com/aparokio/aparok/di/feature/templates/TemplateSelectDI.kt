package com.aparokio.aparok.di.feature.templates

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.di.feature.onboarding.AuthScreenScope
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.core_models.UrlBuilder
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.domain.objects.StoreOfRelations
import com.aparokio.aparok.domain.templates.ApplyTemplate
import com.aparokio.aparok.domain.templates.GetTemplates
import com.aparokio.aparok.domain.workspace.SpaceManager
import com.aparokio.aparok.presentation.analytics.AnalyticSpaceHelperDelegate
import com.aparokio.aparok.presentation.editor.cover.CoverImageHashProvider
import com.aparokio.aparok.presentation.onboarding.OnboardingViewModel
import com.aparokio.aparok.providers.DefaultCoverImageHashProvider
import com.aparokio.aparok.ui.templates.TemplateSelectFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [TemplateSelectModule::class],
    dependencies = [TemplateSelectDependencies::class]
)
@TemplateSelectScope
interface TemplateSelectComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: TemplateSelectDependencies): TemplateSelectComponent
    }

    fun inject(fragment: TemplateSelectFragment)
}

@Module
object TemplateSelectModule {
    @JvmStatic
    @Provides
    @TemplateSelectScope
    fun applyTemplate(repo: BlockRepository, dispatchers: AppCoroutineDispatchers): ApplyTemplate =
        ApplyTemplate(
            repo = repo,
            dispatchers = dispatchers
        )

    @JvmStatic
    @Provides
    @TemplateSelectScope
    fun getTemplates(
        repo: BlockRepository,
        spaceManager: SpaceManager,
        dispatchers: AppCoroutineDispatchers
    ): GetTemplates =
        GetTemplates(
            repo = repo,
            spaceManager = spaceManager,
            dispatchers = dispatchers
        )

    @JvmStatic
    @TemplateSelectScope
    @Provides
    fun provideCoverImageHashProvider(): CoverImageHashProvider = DefaultCoverImageHashProvider()

    @Module
    interface Declarations {
        @Binds
        @AuthScreenScope
        fun bindViewModelFactory(factory: OnboardingViewModel.Factory): ViewModelProvider.Factory
    }
}

interface TemplateSelectDependencies : ComponentDependencies {
    fun urlBuilder(): UrlBuilder
    fun storeOfRelations(): StoreOfRelations
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun analytics() : Analytics
    fun blockRepository(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun spaceManager(): SpaceManager
    fun analyticSpaceHelper(): AnalyticSpaceHelperDelegate
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TemplateSelectScope