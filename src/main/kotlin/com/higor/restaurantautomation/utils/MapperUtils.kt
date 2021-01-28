package com.higor.restaurantautomation.utils

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.stereotype.Component

@Component
class Mapper: ModelMapper() {
    init {
        configuration.matchingStrategy = MatchingStrategies.STANDARD
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }



}

object MapperUtils{
    val mapper = Mapper()

    inline fun <S, reified T> convert(source: S): T = mapper.map(source, T::class.java)

    inline fun <S, reified T> merge(source: S, target: T) = mapper.map(source, target)

}