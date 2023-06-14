package com.kt.startkit.util

fun String.toFirstCharUpperCase(): String {
    if (this.isEmpty()) {
        return this
    }

    return this.replaceFirstChar { it.uppercaseChar() }

}