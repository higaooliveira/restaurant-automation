package com.higor.restaurantautomation.domain.service.contracts


interface Crudable<T> {

    fun getById(id: Long): T
    fun getAll(): List<T>
    fun delete(id: Long)
}