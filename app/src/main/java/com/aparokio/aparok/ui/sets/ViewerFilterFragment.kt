package com.aparokio.aparok.ui.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Id
import com.aparokio.aparok.core_models.primitives.SpaceId
import com.aparokio.aparok.core_ui.extensions.drawable
import com.aparokio.aparok.core_ui.features.dataview.modals.FilterByAdapter
import com.aparokio.aparok.core_ui.layout.DividerVerticalItemDecoration
import com.aparokio.aparok.core_ui.reactive.clicks
import com.aparokio.aparok.core_utils.ext.argString
import com.aparokio.aparok.core_utils.ext.gone
import com.aparokio.aparok.core_utils.ext.invisible
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.core_utils.ext.toast
import com.aparokio.aparok.core_utils.ext.visible
import com.aparokio.aparok.core_utils.ui.BaseBottomSheetFragment
import com.aparokio.aparok.databinding.FragmentFilterBinding
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.di.feature.DefaultComponentParam
import com.aparokio.aparok.presentation.sets.filter.ViewerFilterCommand
import com.aparokio.aparok.presentation.sets.filter.ViewerFilterViewModel
import com.aparokio.aparok.ui.sets.modals.filter.CreateFilterFlowRootFragment
import com.aparokio.aparok.ui.sets.modals.filter.ModifyFilterFromInputFieldValueFragment
import com.aparokio.aparok.ui.sets.modals.filter.ModifyFilterFromSelectedValueFragment
import javax.inject.Inject

open class ViewerFilterFragment : BaseBottomSheetFragment<FragmentFilterBinding>() {

    private val ctx get() = argString(CONTEXT_ID_KEY)
    private val space get() = argString(SPACE_ID_KEY)
    private val viewer get() = argString(VIEWER_ID_KEY)

    private val filterAdapter by lazy {
        FilterByAdapter(
            click = { click -> vm.onFilterClicked(ctx = ctx, viewerId = viewer, click = click) }
        )
    }

    @Inject
    lateinit var factory: ViewerFilterViewModel.Factory
    private val vm: ViewerFilterViewModel by viewModels { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expand()
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = filterAdapter
        with(lifecycleScope) {
            subscribe(vm.commands) { observeCommands(it) }
            subscribe(binding.addButton.clicks()) { vm.onAddNewFilterClicked(viewerId = viewer) }
            subscribe(binding.doneBtn.clicks()) { vm.onDoneButtonClicked() }
            subscribe(binding.editBtn.clicks()) { vm.onEditButtonClicked() }
            subscribe(vm.views) { filterAdapter.update(it) }
            subscribe(vm.screenState) { render(it) }
        }
    }

    override fun onStart() {
        super.onStart()
        vm.onStart(viewerId = viewer)
    }

    override fun onStop() {
        super.onStop()
        vm.onStop()
    }

    private fun render(state: ViewerFilterViewModel.ScreenState) {
        when (state) {
            ViewerFilterViewModel.ScreenState.LIST -> {
                removeDivider()
                with(binding) {
                    editBtn.visible()
                    addButton.visible()
                    doneBtn.invisible()
                    txtEmptyState.gone()
                    recycler.addItemDecoration(
                        DividerVerticalItemDecoration(
                            divider = requireContext().drawable(R.drawable.divider_filter_list),
                            isShowInLastItem = false
                        ),
                        0
                    )
                }
            }
            ViewerFilterViewModel.ScreenState.EDIT -> {
                removeDivider()
                with(binding) {
                    doneBtn.visible()
                    editBtn.invisible()
                    addButton.invisible()
                    txtEmptyState.gone()
                    recycler.addItemDecoration(
                        DividerVerticalItemDecoration(
                            divider = requireContext().drawable(R.drawable.divider_filter_edit),
                            isShowInLastItem = false
                        ),
                        0
                    )
                }
            }
            ViewerFilterViewModel.ScreenState.EMPTY -> {
                removeDivider()
                with(binding) {
                    doneBtn.invisible()
                    editBtn.invisible()
                    addButton.visible()
                    txtEmptyState.visible()
                }
            }
        }
    }

    private fun removeDivider() {
        if (binding.recycler.itemDecorationCount > 0) binding.recycler.removeItemDecorationAt(0)
    }

    private fun observeCommands(command: ViewerFilterCommand) {
        when (command) {
            is ViewerFilterCommand.Modal.ShowRelationList -> {
                val fr = CreateFilterFlowRootFragment.new(
                    ctx = ctx,
                    space = space,
                    viewer = viewer
                )
                fr.show(parentFragmentManager, null)
            }
            is ViewerFilterCommand.Modal.UpdateInputValueFilter -> {
                val fr = ModifyFilterFromInputFieldValueFragment.new(
                    ctx = ctx,
                    space = space,
                    relation = command.relation,
                    index = command.filterIndex,
                    viewer = viewer
                )
                fr.showChildFragment(fr.javaClass.canonicalName)
            }
            is ViewerFilterCommand.Modal.UpdateSelectValueFilter -> {
                val fr = ModifyFilterFromSelectedValueFragment.new(
                    ctx = ctx,
                    space = space,
                    relation = command.relation,
                    index = command.filterIndex,
                    viewer = viewer
                )
                fr.showChildFragment(fr.javaClass.canonicalName)
            }
            is ViewerFilterCommand.ShowAdvancedFilterMessage -> {
                toast(getString(R.string.advanced_filter_description))
            }
        }
    }

    override fun injectDependencies() {
        componentManager()
            .viewerFilterComponent
            .get(
                params = DefaultComponentParam(
                    ctx = ctx,
                    space = SpaceId(space)
                )
            )
            .inject(this)
    }

    override fun releaseDependencies() {
        componentManager().viewerFilterComponent.release()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterBinding = FragmentFilterBinding.inflate(
        inflater, container, false
    )

    companion object {
        const val CONTEXT_ID_KEY = "arg.viewer.filters.context"
        const val SPACE_ID_KEY = "arg.viewer.filters.space-id"
        const val VIEWER_ID_KEY = "arg.viewer.filters.viewer"

        fun new(
            ctx: Id,
            space: Id,
            viewer: Id
        ) = ViewerFilterFragment().apply {
            arguments = bundleOf(
                CONTEXT_ID_KEY to ctx,
                SPACE_ID_KEY to space,
                VIEWER_ID_KEY to viewer
            )
        }
    }
}