package com.higor.restaurantautomation.domain.dto

data class PaginationDto<T>(
    val list: List<T>,
    val page: Int,
    val totalPages: Int,
    val totalRecords: Int,
    val last: Boolean,
) {

    data class Builder<T>(
        var list: List<T> = listOf(),
        var page: Int? = null,
        var totalPages: Int? = null,
        var totalRecords: Int? = null,
        var last: Boolean? = null,
    ) {
        fun list(list: List<T>) = apply { this.list = list }
        fun page(page: Int) = apply { this.page = page }
        fun totalPages(totalPages: Int) = apply { this.totalPages = totalPages }
        fun totalRecords(totalRecords: Int) = apply { this.totalRecords = totalRecords }
        fun last(last: Boolean) = apply { this.last = last }

        fun build(): PaginationDto<T> {
            return PaginationDto(
                list = list,
                page = page!!,
                totalPages = totalPages!!,
                totalRecords = totalRecords!!,
                last = last!!,
            )
        }
    }
}
