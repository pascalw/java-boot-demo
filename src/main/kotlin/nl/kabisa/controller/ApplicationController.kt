package nl.kabisa.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.util.stream.Collectors

open class ApplicationController {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = e.bindingResult.fieldErrors.stream()
                .map { error -> Error(error.field, error.defaultMessage) }
                .collect(Collectors.toList<Error>())

        val errorResponse = ErrorResponse("Validation error", errors)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    class ErrorResponse @JvmOverloads constructor(val message: String, val errors: List<Error> = emptyList<Error>())

    class Error(val field: String, val message: String)
}
