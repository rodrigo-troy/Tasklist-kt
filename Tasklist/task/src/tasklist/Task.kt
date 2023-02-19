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
    private val dateFormat = "yyyy-MM-dd"
    private val simpleDateFormat = SimpleDateFormat(dateFormat)

    fun setTaskDate(date: String) {
        val parse = simpleDateFormat.parse(date)
        this.date = simpleDateFormat.format(parse)
    }

    fun addDescription(description: String) {
        this.description += "\n    $description"
    }

    fun dateIsValid(date: String): Boolean {
        this.simpleDateFormat.isLenient = false

        return try {
            simpleDateFormat.parse(date)
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
