package com.reo.trivia.domain.exception

class NetworkErrorException(override val message: String) : ApplicationException() {
    companion object {
        private const val serialVersionUID = 3292793513624829097L
    }
}
