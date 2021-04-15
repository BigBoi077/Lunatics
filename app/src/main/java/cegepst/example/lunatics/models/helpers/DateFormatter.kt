package cegepst.example.lunatics.models.helpers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val YEAR_AGO = 1L
private const val MONTH_AGO = 1L

class DateFormatter {

    companion object {
        fun getYearAgo(pattern: String): String {
            val yearAgo =
                LocalDate.now().minusYears(YEAR_AGO).format(DateTimeFormatter.ofPattern(pattern))
            return "$yearAgo,${LocalDate.now().format(DateTimeFormatter.ofPattern(pattern))}"
        }

        fun getMontAgo(pattern: String): String {
            val monthAgo =
                LocalDate.now().minusMonths(MONTH_AGO).format(DateTimeFormatter.ofPattern(pattern))
            return "$monthAgo,${LocalDate.now().format(DateTimeFormatter.ofPattern(pattern))}"
        }
    }
}