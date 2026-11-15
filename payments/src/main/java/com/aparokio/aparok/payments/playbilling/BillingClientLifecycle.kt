package com.aparokio.aparok.payments.playbilling

import android.app.Activity
import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import com.aparokio.aparok.domain.base.AppCoroutineDispatchers
import com.aparokio.aparok.payments.models.MembershipPurchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillingClientLifecycle(
    private val dispatchers: AppCoroutineDispatchers,
    private val applicationContext: Context,
    private val scope: CoroutineScope
) : DefaultLifecycleObserver {

    private val _subscriptionPurchases = MutableStateFlow<BillingPurchaseState>(BillingPurchaseState.NoPurchases)
    val subscriptionPurchases: StateFlow<BillingPurchaseState> = _subscriptionPurchases.asStateFlow()

    private val _builderSubProductWithProductDetails = MutableStateFlow<BillingClientState>(BillingClientState.NotAvailable)
    val builderSubProductWithProductDetails: StateFlow<BillingClientState> = _builderSubProductWithProductDetails.asStateFlow()

    fun setupSubIds(ids: List<String>) {}

    override fun onCreate(owner: LifecycleOwner) {}
    override fun onDestroy(owner: LifecycleOwner) {}

    fun launchBillingFlow(activity: Activity, params: BillingFlowParams): Int {
        return 0
    }
}

sealed class BillingClientState {
    data object Loading : BillingClientState()
    data class Error(val message: String) : BillingClientState()
    data class Connected(val productDetails: List<ProductDetails>) : BillingClientState()
    data object NotAvailable : BillingClientState()
}

sealed class BillingPurchaseState {
    data object Loading : BillingPurchaseState()
    data class HasPurchases(
        val purchases: List<MembershipPurchase>,
        val isNewPurchase: Boolean
    ) : BillingPurchaseState()
    data object NoPurchases : BillingPurchaseState()
}
