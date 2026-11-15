package com.aparokio.aparok.di.feature

import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.core_utils.di.scope.PerScreen
import com.aparokio.aparok.di.common.ComponentDependencies
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.device.FileSharer
import com.aparokio.aparok.domain.media.UploadFile
import com.aparokio.aparok.domain.multiplayer.SpaceViewSubscriptionContainer
import com.aparokio.aparok.domain.objects.StoreOfObjectTypes
import com.aparokio.aparok.feature_create_object.presentation.CreateObjectViewModelFactory
import com.aparokio.aparok.feature_create_object.presentation.NewCreateObjectViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [CreateObjectFeatureDependencies::class],
    modules = [
        CreateObjectFeatureModule::class,
        CreateObjectFeatureModule.Declarations::class
    ]
)
@PerScreen
interface CreateObjectFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance vmParams: NewCreateObjectViewModel.VmParams,
            dependencies: CreateObjectFeatureDependencies
        ): CreateObjectFeatureComponent
    }

    fun viewModelFactory(): CreateObjectViewModelFactory
}

@Module
object CreateObjectFeatureModule {

    @JvmStatic
    @Provides
    @PerScreen
    fun provideUploadFile(
        repo: BlockRepository,
        dispatchers: AppCoroutineDispatchers
    ): UploadFile = UploadFile(repo = repo, dispatchers = dispatchers)

    @Module
    interface Declarations {
        @PerScreen
        @Binds
        fun bindViewModelFactory(
            factory: CreateObjectViewModelFactory
        ): ViewModelProvider.Factory
    }
}

interface CreateObjectFeatureDependencies : ComponentDependencies {
    fun storeOfObjectTypes(): StoreOfObjectTypes
    fun spaceViewSubscriptionContainer(): SpaceViewSubscriptionContainer
    fun blockRepo(): BlockRepository
    fun dispatchers(): AppCoroutineDispatchers
    fun fileSharer(): FileSharer
}
