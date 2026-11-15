package com.aparokio.aparok.ui.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Key
import com.aparokio.aparok.core_models.ObjectType
import com.aparokio.aparok.core_utils.ext.arg
import com.aparokio.aparok.core_utils.ext.subscribe
import com.aparokio.aparok.core_utils.ext.toast
import com.aparokio.aparok.core_utils.ui.BaseFragment
import com.aparokio.aparok.databinding.FragmentCreateObjectBinding
import com.aparokio.aparok.di.common.componentManager
import com.aparokio.aparok.presentation.objects.CreateObjectViewModel
import com.aparokio.aparok.ui.chats.ChatFragment
import com.aparokio.aparok.ui.sets.ObjectSetFragment
import javax.inject.Inject

class CreateObjectFragment : BaseFragment<FragmentCreateObjectBinding>(R.layout.fragment_create_object) {

    @Inject
    lateinit var factory: CreateObjectViewModel.Factory
    private val vm by viewModels<CreateObjectViewModel> { factory }

    private val mType get() = arg<Key>(TYPE_KEY)

    override fun onStart() {
        jobs += lifecycleScope.subscribe(vm.createObjectStatus) { state ->
            when (state) {
                is CreateObjectViewModel.State.Error -> {
                    activity?.toast(state.msg)
                }
                is CreateObjectViewModel.State.Success -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.createObjectFragment, true)
                        .build()
                    val layout = state.layout
                    when (layout) {
                        ObjectType.Layout.COLLECTION, ObjectType.Layout.SET -> {
                            findNavController().navigate(
                                R.id.dataViewNavigation,
                                ObjectSetFragment.args(
                                    ctx = state.id,
                                    space = state.space
                                ),
                                navOptions
                            )
                        }
                        ObjectType.Layout.CHAT, ObjectType.Layout.CHAT_DERIVED -> {
                            findNavController().navigate(
                                R.id.chatScreen,
                                ChatFragment.args(
                                    ctx = state.id,
                                    space = state.space
                                ),
                                navOptions
                            )
                        }
                        else -> {
                            findNavController().navigate(
                                R.id.objectNavigation,
                                EditorFragment.args(
                                    ctx = state.id,
                                    space = state.space
                                ),
                                navOptions
                            )
                        }
                    }
                }
                is CreateObjectViewModel.State.Exit -> {
                    findNavController().popBackStack()
                }
            }
        }
        super.onStart()
        vm.onStart(mType)
    }

    override fun onStop() {
        vm.onStop()
        super.onStop()
    }

    override fun injectDependencies() {
        componentManager().createObjectComponent.get().inject(this)
    }

    override fun releaseDependencies() {
        componentManager().createObjectComponent.release()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateObjectBinding = FragmentCreateObjectBinding.inflate(
        inflater, container, false
    )

    companion object {
        const val TYPE_KEY = "arg.ui.editor.create.type"
    }
}