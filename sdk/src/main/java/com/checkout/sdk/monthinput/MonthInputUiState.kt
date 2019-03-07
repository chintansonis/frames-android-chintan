package com.checkout.sdk.monthinput

import com.checkout.sdk.architecture.UiState
import com.checkout.sdk.utils.DateFormatter
import java.text.DateFormatSymbols


data class MonthInputUiState(val months: List<String> = emptyList(), val position: Int = 0) :
    UiState {

    companion object {
        fun create(dateFormatter: DateFormatter): MonthInputUiState {
            val months = DateFormatSymbols().shortMonths
            for (i in 0 until months.size) {
                months[i] = months[i].toUpperCase() + " - " + dateFormatter.formatMonth(i + 1)
            }
            return MonthInputUiState(months = months.asList())
        }
    }
}
