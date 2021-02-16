package com.higor.restaurantautomation.utils.contracts

interface Writable {
    fun write(filePath: String, content: String)
}