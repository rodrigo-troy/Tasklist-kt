package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 25-02-23
 * Time: 21:05
 */
enum class DueTag(val value: String) {
    IN_TIME("I"),
    TODAY("T"),
    OVERDUE("O");

    companion object {
        fun getDueTag(dueTag: String): DueTag {
            return when (dueTag.uppercase().trim()) {
                IN_TIME.value -> IN_TIME
                TODAY.value -> TODAY
                OVERDUE.value -> OVERDUE
                else -> IN_TIME
            }
        }
    }

    override fun toString(): String {
        return value
    }
}
