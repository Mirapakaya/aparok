package com.aparokio.aparok.domain.workspace

import com.aparokio.aparok.core_models.membership.Membership
import com.aparokio.aparok.core_models.membership.MembershipTiers
import kotlinx.coroutines.flow.Flow

interface MembershipChannel {
    fun observe(): Flow<List<Membership.Event>>
    fun observeTiers(): Flow<List<MembershipTiers.Event>>
}