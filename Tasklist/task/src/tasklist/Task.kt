package tasklist
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
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
    private val simpleTimeFormat = SimpleDateFormat("HH:mm")
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun getDueTag(): DueTag {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC-0")).date
        val taskDate = LocalDate(date.substring(0,
                                                4).toInt(),
                                 date.substring(5,
                                                7).toInt(),
                                 date.substring(8,
                                                10).toInt())
        val daysUntil = currentDate.daysUntil(taskDate)

        //println("currentDate: $currentDate")
        //println("taskDate: $taskDate")

        return when {
            daysUntil < 0 -> DueTag.OVERDUE
            daysUntil == 0 -> DueTag.TODAY
            else -> DueTag.IN_TIME
        }
    }

    fun setTaskDate(date: String) {
        val parse = simpleDateFormat.parse(date)
        this.date = simpleDateFormat.format(parse)
    }

    fun setTaskTime(task: String) {
        val parse = simpleTimeFormat.parse(task)
        this.time = simpleTimeFormat.format(parse)
    }

    fun addDescription(description: String) {
        this.description += "\n   $description"
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

    fun timeIsValid(time: String): Boolean {
        simpleTimeFormat.isLenient = false

        return try {
            simpleTimeFormat.parse(time)
            true
        } catch (e: ParseException) {
            false
        }
    }

    fun descriptionIsEmpty(): Boolean {
        return description.isEmpty()
    }

    override fun toString(): String {
        return "${this.date} ${this.time} ${this.priority} ${this.getDueTag()}${this.description}"
    }

    fun clearDescription() {
        this.description = ""
    }
}
