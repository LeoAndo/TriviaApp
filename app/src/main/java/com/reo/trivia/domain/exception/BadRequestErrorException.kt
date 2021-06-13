package com.reo.trivia.domain.exception

class BadRequestErrorException(override val message: String) : ApplicationException() {
    companion object {
        private const val serialVersionUID = 0L
    }
}
