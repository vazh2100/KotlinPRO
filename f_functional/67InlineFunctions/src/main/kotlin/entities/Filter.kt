package com.vazh2100.entities

interface Filter<T : Any> {
    fun suitable(item: T): Boolean
}