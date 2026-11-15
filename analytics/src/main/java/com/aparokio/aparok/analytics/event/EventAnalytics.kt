package com.aparokio.aparok.analytics.event

import com.aparokio.aparok.analytics.props.Props

interface EventAnalytics {

    val name: String
    val props: Props

    data class Duration(
        var start: Long?,
        var middleware: Long?,
        var render: Long? = null
    )

    data class Aparok(
        override val name: String,
        override val props: Props = Props.empty(),
        val duration: Duration? = null
    ) : EventAnalytics
}