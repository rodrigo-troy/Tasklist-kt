package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 20:47
 */
enum class Priority(private val priority: String) {
    CRITICAL("C"),
    HIGH("H"),
    NORMAL("N"),
    LOW("L"),
    NONE("");

    companion object {
        fun getPriority(priority: String): Priority {
            return when (priority.uppercase().trim()) {
                CRITICAL.priority -> CRITICAL
                HIGH.priority -> HIGH
                NORMAL.priority -> NORMAL
                LOW.priority -> LOW
                else -> NONE
            }
        }
    }

    override fun toString(): String {
        return priority
    }
}
