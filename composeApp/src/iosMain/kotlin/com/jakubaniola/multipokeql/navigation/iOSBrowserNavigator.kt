package com.jakubaniola.multipokeql.navigation

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class iOSBrowserNavigator : BrowserNavigator {
    override fun openBrowser(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}
