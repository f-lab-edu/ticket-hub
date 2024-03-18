package flab.tickethub.support.config.time

import java.util.*

interface DateTimeProvider {

    fun nowDate(): Date

}