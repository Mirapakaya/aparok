package com.aparokio.aparok.core_ui.features.dataview

import com.aparokio.aparok.core_models.ui.ObjectIcon
import com.aparokio.aparok.presentation.sets.model.Viewer
import com.aparokio.aparok.test_utils.MockDataFactory
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertIs
import kotlin.test.assertNull

class GridDiffUtilTest {

    @Test
    fun `showIcon changed - header payload exists`() {
        val old = Viewer.GridView.Row(
            id = MockDataFactory.randomUuid(),
            showIcon = false,
            objectIcon = ObjectIcon.Basic.Image(
                hash = MockDataFactory.randomUuid()
            )
        )
        val new = old.copy(
            showIcon = true
        )
        val payload = ViewerGridAdapter.GridDiffUtil.getChangePayload(
            old, new
        )
        val payloadCasted = assertIs<List<Int>>(payload)

        assertContains(payloadCasted, ViewerGridAdapter.GridDiffUtil.OBJECT_HEADER_CHANGED)
    }

    @Test
    fun `nothing changed - payloads is empty`() {
        val old = Viewer.GridView.Row(
            id = MockDataFactory.randomUuid(),
            showIcon = false,
            objectIcon = ObjectIcon.Basic.Image(
                hash = MockDataFactory.randomUuid()
            )
        )
        val new = old.copy()
        val payload = ViewerGridAdapter.GridDiffUtil.getChangePayload(
            old, new
        )
        assertNull(payload)
    }
}