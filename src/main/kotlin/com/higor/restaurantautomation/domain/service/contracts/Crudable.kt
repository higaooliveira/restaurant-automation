package com.higor.restaurantautomation.domain.service.contracts

import java.util.UUID

interface Crudable<T> {

    fun getById(id: UUID): T
    fun delete(id: UUID)
}
