package com.jakubaniola.multipokeql.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri

class AndroidBrowserNavigator(private val context: Context) : BrowserNavigator {
    override fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}
