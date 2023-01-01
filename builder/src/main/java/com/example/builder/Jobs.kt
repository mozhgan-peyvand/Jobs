package com.example.builder

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.builder.ui.BuilderActivity

class Jobs (
    private var context: Context? = null,
    private var appId: String
    ) {

    private val activityResultRequestCode = 10

    private constructor(builder: Builder) : this(
        context = builder.context,
        appId = builder.appId,
    )

    class Builder {

        var context: Context? = null
            private set

        var appId: String = ""
            private set

        fun setContext(context: Context) = apply { this.context = context }

        fun setAppId(appId: String) = apply { this.appId = appId }

        fun build() = Jobs(this)
    }

    fun start() {
        BuilderActivity.appId = appId
        Intent(context, BuilderActivity::class.java).apply {
            (context as Activity).startActivityForResult(this, activityResultRequestCode)
        }
    }
}