package com.aparokio.aparok.features.sets.dv

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aparokio.aparok.R
import com.aparokio.aparok.core_models.Block
import com.aparokio.aparok.core_models.ext.content
import com.aparokio.aparok.presentation.MockBlockContentFactory.StubTextContent
import com.aparokio.aparok.test_utils.MockDataFactory
import com.aparokio.aparok.test_utils.utils.checkHasText
import com.aparokio.aparok.ui.sets.ObjectSetFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ObjectSetHeaderTest : TestObjectSetSetup() {


    override val title: Block = Block(
        id = MockDataFactory.randomUuid(),
        content = StubTextContent(
            style = Block.Content.Text.Style.TITLE,
            text = "Data View UI Testing",
            marks = emptyList()
        ),
        children = emptyList(),
        fields = Block.Fields.empty()
    )

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun shouldRenderObjectSetTitleWithViewerTitle() {

        // SETUP

        val viewer = Block.Content.DataView.Viewer(
            id = MockDataFactory.randomUuid(),
            name = "Default Grid View",
            filters = emptyList(),
            sorts = emptyList(),
            viewerRelations = emptyList(),
            type = Block.Content.DataView.Viewer.Type.GRID
        )

        val dataview = Block(
            id = MockDataFactory.randomUuid(),
            children = emptyList(),
            fields = Block.Fields.empty(),
            content = Block.Content.DataView(
                viewers = listOf(viewer)
            )
        )

        val root = Block(
            id = ctx,
            fields = Block.Fields(emptyMap()),
            content = Block.Content.Smart,
            children = listOf(header.id, dataview.id)
        )

        val set = listOf(root, header, title, dataview)

        stubInterceptEvents()
        stubInterceptThreadStatus()
        stubSearchWithSubscription()
        stubSubscriptionEventChannel()
        stubOpenObjectSet(
            set = set,
            details = defaultDetails.details
        )

        // TESTING

        launchFragment(bundleOf(ObjectSetFragment.CONTEXT_ID_KEY to ctx))

        onView(withId(R.id.tvSetTitle)).checkHasText(title.content<Block.Content.Text>().text)
        onView(withId(R.id.tvCurrentViewerName)).checkHasText(viewer.name)
    }
}