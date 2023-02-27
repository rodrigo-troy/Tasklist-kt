package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 27-02-23
 * Time: 11:50
 */
enum class ConsoleColor(private val value: String) {
    RED("\u001B[101m \u001B[0m"),
    GREEN("\u001B[102m \u001B[0m"),
    YELLOW("\u001B[103m \u001B[0m"),
    BLUE("\u001B[104m \u001B[0m"),
    BLACK("\u001B[100m \u001B[0m");

    companion object {
        fun getConsoleColor(priority: Priority): ConsoleColor {
            return when (priority) {
                Priority.CRITICAL -> RED
                Priority.HIGH -> YELLOW
                Priority.NORMAL -> GREEN
                Priority.LOW -> BLUE
                Priority.NONE -> BLACK
            }
        }

        fun getConsoleColor(dueTag: DueTag): ConsoleColor {
            return when (dueTag) {
                DueTag.OVERDUE -> RED
                DueTag.TODAY -> YELLOW
                DueTag.IN_TIME -> GREEN
            }
        }
    }


    override fun toString(): String {
        return value
    }
}
