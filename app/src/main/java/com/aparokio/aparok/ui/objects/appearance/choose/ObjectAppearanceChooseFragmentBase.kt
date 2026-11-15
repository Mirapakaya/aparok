package com.aparokio.aparok.ui.objects.appearance.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aparokio.aparok.R
import com.aparokio.aparok.core_ui.features.objects.appearance.ObjectAppearanceChooseAdapter
import com.aparokio.aparok.core_utils.ext.argString
import com.aparokio.aparok.core_utils.ext.drawable
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.core_utils.ext.toast
import com.aparokio.aparok.core_utils.ui.BaseBottomSheetFragment
import com.aparokio.aparok.databinding.FragmentObjAppearanceBaseBinding
import com.aparokio.aparok.presentation.objects.appearance.choose.ObjectAppearanceChooseSettingsView
import com.aparokio.aparok.presentation.objects.appearance.choose.ObjectAppearanceChooseViewModelBase
import com.aparokio.aparok.presentation.objects.appearance.choose.ObjectAppearanceChooseViewModelBase.State

abstract class ObjectAppearanceChooseFragmentBase
<I : ObjectAppearanceChooseSettingsView, T : ObjectAppearanceChooseViewModelBase<I>> :
    BaseBottomSheetFragment<FragmentObjAppearanceBaseBinding>() {

    protected val ctx: String get() = argString(CONTEXT_ID_KEY)
    protected val space: String get() = argString(SPACE_KEY)
    protected val block: String get() = argString(BLOCK_ID_KEY)

    protected abstract val vm: T

    @get:StringRes
    protected abstract val title: Int

    private val adapterAppearance by lazy {
        ObjectAppearanceChooseAdapter<I>(
            onItemClick = { item ->
                vm.onItemClicked(item = item, blockId = block, ctx = ctx)
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvScreenTitle.text = getString(title)
        with(binding.recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterAppearance
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(drawable(R.drawable.divider_object_appearance))
                }
            )
        }
    }

    final override fun onStart() {
        jobs += lifecycleScope.subscribe(vm.state) { state ->
            when (state) {
                is State.Success -> adapterAppearance.submitList(
                    state.items
                )
                is State.Error -> {
                    toast(state.msg)
                    dismiss()
                }
                is State.Dismiss -> dismiss()
            }
        }
        super.onStart()
        vm.onStart(block)
    }

    final override fun onStop() {
        vm.onStop()
        super.onStop()
    }

    final override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentObjAppearanceBaseBinding = FragmentObjAppearanceBaseBinding.inflate(
        inflater, container, false
    )

    companion object {
        const val CONTEXT_ID_KEY = "arg.object.ctx"
        const val SPACE_KEY = "arg.object.space"
        const val BLOCK_ID_KEY = "arg.object.block"
    }
}