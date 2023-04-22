package com.higor.restaurantautomation.utils

import java.util.UUID

fun String.toUUID(): UUID {
    return UUID.fromString(this)
}
