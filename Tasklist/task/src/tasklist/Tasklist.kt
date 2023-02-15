package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 19:20
 */
class Tasklist {
    private val tasks = mutableListOf<String>()
    private var status = Status.WAITING_FOR_NEW_ACTION
    private val blankRegex = Regex("^(\\t|\\s)*\$")
    private var currentTask = ""

    fun getStatus() = status

    fun inputAction(task: String) {
        if (status == Status.START_ADDING_TASK || status == Status.ADDING_TASK) {
            addTask(task)
            return
        }

        when (task) {
            "add" -> status = Status.START_ADDING_TASK
            "print" -> printTasks()
            "end" -> end()
            else -> println("The input action is invalid")
        }
    }

    private fun end() {
        status = Status.EXITING
        println("Tasklist exiting!")
    }

    private fun addTask(task: String) {
        if (status == Status.START_ADDING_TASK) {
            currentTask = ""
            status = Status.ADDING_TASK
        }

        if (currentTask.isEmpty() && blankRegex.matches(task)) {
            println("The task is blank")
            status = Status.WAITING_FOR_NEW_ACTION
            return
        }

        if (!blankRegex.matches(task)) {
            currentTask += "\n    $task"
            return
        }

        if (currentTask.isNotEmpty() && blankRegex.matches(task)) {
            currentTask += "\n    ${task.trim()}"
            tasks.add(currentTask.trim())
            currentTask = ""
            status = Status.WAITING_FOR_NEW_ACTION
        }

        return
    }

    private fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }

        tasks.indices.forEach { i -> println("${i + 1}${if (i <= 8) "  " else " "}${tasks[i]}\n") }
    }
}
