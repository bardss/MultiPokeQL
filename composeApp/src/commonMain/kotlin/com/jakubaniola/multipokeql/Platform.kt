package com.jakubaniola.multipokeql

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform