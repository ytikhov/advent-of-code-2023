package com.ytikhov.util

class FileData(filename: String) {
    val data = this::class.java.classLoader.getResource(filename)?.readText()?.split("\n") ?: listOf()
}