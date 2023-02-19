package tasklist

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 20:41
 */

data class Task(
        var description: String,
        var date: String,
        var time: String,
        var priority: Priority) {

    fun addDescription(description: String) {
        this.description += "\n    $description"
    }

    fun dateIsValid(date: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.isLenient = false

        return try {
            dateFormat.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }

    fun descriptionIsEmpty(): Boolean {
        return description.isEmpty()
    }

    fun timeIsValid(time: String): Boolean {
        val timeFormat = SimpleDateFormat("HH:mm")
        timeFormat.isLenient = false

        return try {
            timeFormat.parse(time)
            true
        } catch (e: ParseException) {
            false
        }
    }
}
