package com.higor.restaurantautomation.adapters.repository.user

import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.adapters.entity.User
import org.springframework.data.jpa.domain.Specification

object UserFilters {
    fun role(role: Role?): Specification<User> {
        return Specification { root, _, criteriaBuilder ->
            role?.let {
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("role")), it.name.lowercase())
            }
        }
    }

    fun name(name: String?): Specification<User> {
        return Specification { root, _, criteriaBuilder ->
            name?.let {
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), it.lowercase())
            }
        }
    }

    fun email(email: String?): Specification<User> {
        return Specification { root, _, criteriaBuilder ->
            email?.let {
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), it.lowercase())
            }
        }
    }

    fun phone(phone: String?): Specification<User> {
        return Specification { root, _, criteriaBuilder ->
            phone?.let {
                criteriaBuilder.equal(root.get<String>("phone"), it)
            }
        }
    }
}
