package com.higor.restaurantautomation.configuration.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception

@Component
class LoggingInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?,
    ) {
        with(request) {
            val attributes = mutableMapOf<String, String>()
            attributes["userId"] = getAttribute("userId").toString()
            attributes["requestId"] = requestId
            val message = buildString {
                append(method)
                append("(")
                append(response.status)
                append(") -> ")
                append(requestURI)
                append(" -> queryParams: ")
                append(parameterMap.toMutableMap())
                append(" -> pathParams: ")
                append(attributes)
            }
            logger.info(message)
        }
    }
}
