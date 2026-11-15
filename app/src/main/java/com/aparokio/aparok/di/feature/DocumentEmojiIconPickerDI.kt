package com.aparokio.aparok.di.feature

import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.core_models.Payload
import com.aparokio.aparok.core_utils.di.scope.PerModal
import com.aparokio.aparok.domain.block.repo.BlockRepository
import com.aparokio.aparok.domain.icon.RemoveDocumentIcon
import com.aparokio.aparok.domain.icon.RemoveTextBlockIcon
import com.aparokio.aparok.domain.icon.SetDocumentEmojiIcon
import com.aparokio.aparok.domain.icon.SetDocumentImageIcon
import com.aparokio.aparok.domain.icon.SetTextBlockEmoji
import com.aparokio.aparok.domain.icon.SetTextBlockImage
import com.aparokio.aparok.emojifier.data.Emoji
import com.aparokio.aparok.emojifier.suggest.EmojiSuggester
import com.aparokio.aparok.presentation.editor.picker.ObjectIconPickerViewModelFactory
import com.aparokio.aparok.presentation.editor.picker.ObjectSetIconPickerViewModelFactory
import com.aparokio.aparok.presentation.editor.picker.TextBlockIconPickerViewModelFactory
import com.aparokio.aparok.presentation.util.Dispatcher
import com.aparokio.aparok.ui.editor.modals.ObjectIconPickerFragment
import com.aparokio.aparok.ui.editor.modals.TextBlockIconPickerFragment
import com.aparokio.aparok.ui.sets.ObjectSetIconPickerFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [ObjectIconPickerBaseModule::class, ObjectIconPickerModule::class])
@PerModal
interface ObjectIconPickerComponent {
    @Subcomponent.Builder
    interface Builder {
        fun base(module: ObjectIconPickerBaseModule): Builder
        fun module(module: ObjectIconPickerModule): Builder
        fun build(): ObjectIconPickerComponent
    }

    fun inject(fragment: ObjectIconPickerFragment)
}

@Subcomponent(modules = [TextBlockIconPickerModule::class])
@PerModal
interface TextBlockIconPickerComponent {

    @Subcomponent.Builder
    interface Builder {
        fun module(module: TextBlockIconPickerModule): Builder
        fun build(): TextBlockIconPickerComponent
    }

    fun inject(fragment: TextBlockIconPickerFragment)
}

@Subcomponent(modules = [ObjectIconPickerBaseModule::class, ObjectSetIconPickerModule::class])
@PerModal
interface ObjectSetIconPickerComponent {
    @Subcomponent.Builder
    interface Builder {
        fun base(module: ObjectIconPickerBaseModule): Builder
        fun module(module: ObjectSetIconPickerModule): Builder
        fun build(): ObjectSetIconPickerComponent
    }

    fun inject(fragment: ObjectSetIconPickerFragment)
}

@Module
object ObjectIconPickerBaseModule {

    @Provides
    @PerModal
    fun provideSetDocumentEmojiIconUseCase(
        repo: BlockRepository
    ): SetDocumentEmojiIcon = SetDocumentEmojiIcon(
        repo = repo
    )

    @Provides
    @PerModal
    fun provideRemoveDocumentIconUseCase(
        repo: BlockRepository
    ): RemoveDocumentIcon = RemoveDocumentIcon(
        repo = repo
    )
}

@Module
object ObjectIconPickerModule {

    @Provides
    @PerModal
    fun provideViewModelFactory(
        setEmojiIcon: SetDocumentEmojiIcon,
        setImageIcon: SetDocumentImageIcon,
        removeDocumentIcon: RemoveDocumentIcon,
        emojiSuggester: EmojiSuggester,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics
    ): ObjectIconPickerViewModelFactory = ObjectIconPickerViewModelFactory(
        setEmojiIcon = setEmojiIcon,
        setImageIcon = setImageIcon,
        removeDocumentIcon = removeDocumentIcon,
        emojiSuggester = emojiSuggester,
        emojiProvider = Emoji,
        dispatcher = dispatcher,
        analytics = analytics
    )
}

@Module
object TextBlockIconPickerModule {

    @Provides
    @PerModal
    fun provideSetEmojiIcon(
        repo: BlockRepository
    ): SetTextBlockEmoji = SetTextBlockEmoji(
        repo = repo
    )

    @Provides
    @PerModal
    fun provideRemoveIcon(
        repo: BlockRepository
    ): RemoveTextBlockIcon = RemoveTextBlockIcon(
        repo = repo
    )

    @Provides
    @PerModal
    fun provideSetImage(
        repo: BlockRepository
    ): SetTextBlockImage = SetTextBlockImage(
        repo = repo
    )

    @Provides
    @PerModal
    fun provideViewModelFactory(
        setEmojiIcon: SetTextBlockEmoji,
        setImageIcon: SetTextBlockImage,
        removeDocumentIcon: RemoveTextBlockIcon,
        emojiSuggester: EmojiSuggester,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics
    ): TextBlockIconPickerViewModelFactory = TextBlockIconPickerViewModelFactory(
        setEmojiIcon = setEmojiIcon,
        setImageIcon = setImageIcon,
        removeDocumentIcon = removeDocumentIcon,
        emojiSuggester = emojiSuggester,
        emojiProvider = Emoji,
        dispatcher = dispatcher,
        analytics = analytics
    )
}

@Module
object ObjectSetIconPickerModule {
    @Provides
    @PerModal
    fun provideViewModelFactory(
        setEmojiIcon: SetDocumentEmojiIcon,
        setImageIcon: SetDocumentImageIcon,
        removeDocumentIcon: RemoveDocumentIcon,
        emojiSuggester: EmojiSuggester,
        dispatcher: Dispatcher<Payload>,
        analytics: Analytics
    ): ObjectSetIconPickerViewModelFactory = ObjectSetIconPickerViewModelFactory(
        setEmojiIcon = setEmojiIcon,
        setImageIcon = setImageIcon,
        removeDocumentIcon = removeDocumentIcon,
        emojiSuggester = emojiSuggester,
        emojiProvider = Emoji,
        dispatcher = dispatcher,
        analytics = analytics
    )
}