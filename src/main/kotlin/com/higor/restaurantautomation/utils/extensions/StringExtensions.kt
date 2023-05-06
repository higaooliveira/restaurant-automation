package com.higor.restaurantautomation.utils.extensions

import java.util.UUID

fun String.toUUID(): UUID {
    return UUID.fromString(this)
}
