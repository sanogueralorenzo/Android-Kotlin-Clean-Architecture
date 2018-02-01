package com.sanogueralorenzo.domain.usecase

interface UseCase<out T> {
    fun execute(): T
}
