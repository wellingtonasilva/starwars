package br.com.wsilva.starwars.core

import io.reactivex.Scheduler

interface BasicSchedulers {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}