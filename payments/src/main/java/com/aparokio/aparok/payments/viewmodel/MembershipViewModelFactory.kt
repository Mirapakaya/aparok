package com.aparokio.aparok.payments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aparokio.aparok.analytics.base.Analytics
import com.aparokio.aparok.domain.auth.interactor.GetAccount
import com.aparokio.aparok.domain.payments.GetMembershipCodeInfo
import com.aparokio.aparok.domain.payments.GetMembershipEmailStatus
import com.aparokio.aparok.domain.payments.GetMembershipPaymentUrl
import com.aparokio.aparok.domain.payments.IsMembershipNameValid
import com.aparokio.aparok.domain.payments.RedeemMembershipCode
import com.aparokio.aparok.domain.payments.SetMembershipEmail
import com.aparokio.aparok.domain.payments.VerifyMembershipEmailCode
import com.aparokio.aparok.payments.playbilling.BillingClientLifecycle
import com.aparokio.aparok.presentation.membership.provider.MembershipProvider
import javax.inject.Inject

class MembershipViewModelFactory @Inject constructor(
    private val analytics: Analytics,
    private val billingClientLifecycle: BillingClientLifecycle,
    private val getAccount: GetAccount,
    private val membershipProvider: MembershipProvider,
    private val getMembershipPaymentUrl: GetMembershipPaymentUrl,
    private val isMembershipNameValid: IsMembershipNameValid,
    private val setMembershipEmail: SetMembershipEmail,
    private val verifyMembershipEmailCode: VerifyMembershipEmailCode,
    private val getMembershipEmailStatus: GetMembershipEmailStatus,
    private val getMembershipCodeInfo: GetMembershipCodeInfo,
    private val redeemMembershipCode: RedeemMembershipCode
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MembershipViewModel(
            analytics = analytics,
            billingClientLifecycle = billingClientLifecycle,
            getAccount = getAccount,
            membershipProvider = membershipProvider,
            getMembershipPaymentUrl = getMembershipPaymentUrl,
            isMembershipNameValid = isMembershipNameValid,
            setMembershipEmail = setMembershipEmail,
            verifyMembershipEmailCode = verifyMembershipEmailCode,
            getMembershipEmailStatus = getMembershipEmailStatus,
            getMembershipCodeInfo = getMembershipCodeInfo,
            redeemMembershipCode = redeemMembershipCode
        ) as T
    }
}