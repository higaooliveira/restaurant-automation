package com.higor.restaurantautomation.utils

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.UUID

object HateoasHelper {

    fun buildUrlToGetRequest(id: UUID): URI = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri()
}
