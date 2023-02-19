package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 19:20
 */
class Tasklist {
    private val tasks = mutableListOf<Task>()
    private var status = Status.WAITING_FOR_NEW_ACTION
    private val blankRegex = Regex("^(\\t|\\s)*\$")
    private var currentTask: Task = Task("",
                                         "",
                                         "",
                                         Priority.NONE)

    fun getStatus() = status

    fun inputAction(task: String) {
        if (status == Status.START_ADDING_TASK || status == Status.ADDING_PRIORITY || status == Status.ADDING_DATE || status == Status.ADDING_TIME || status == Status.ADDING_DESCRIPTION) {
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
        status = Status.EXIT
        println("Tasklist exiting!")
    }

    private fun addTask(inputText: String) {
        if (status == Status.START_ADDING_TASK) {
            status = Status.ADDING_PRIORITY
        }

        if (status == Status.ADDING_PRIORITY) {
            when (inputText.lowercase().trim()) {
                "c" -> currentTask.priority = Priority.CRITICAL
                "h" -> currentTask.priority = Priority.HIGH
                "n" -> currentTask.priority = Priority.NORMAL
                "l" -> currentTask.priority = Priority.LOW
                else -> return
            }

            status = Status.ADDING_DATE
            return
        }

        if (status == Status.ADDING_DATE && !currentTask.dateIsValid(inputText)) {
            println("The input date is invalid")
            return
        }

        if (status == Status.ADDING_DATE && currentTask.dateIsValid(inputText)) {
            currentTask.date = inputText
            status = Status.ADDING_TIME
            return
        }

        if (status == Status.ADDING_TIME && !currentTask.timeIsValid(inputText)) {
            println("The input time is invalid")
            return
        }

        if (status == Status.ADDING_TIME && currentTask.timeIsValid(inputText)) {
            currentTask.time = inputText
            status = Status.ADDING_DESCRIPTION
            return
        }

        if (status == Status.ADDING_DESCRIPTION && currentTask.descriptionIsEmpty() && blankRegex.matches(inputText)) {
            println("The task is blank")
            status = Status.WAITING_FOR_NEW_ACTION
            return
        }

        if (status == Status.ADDING_DESCRIPTION && !blankRegex.matches(inputText)) {
            currentTask.addDescription(inputText)
            return
        }

        if (status == Status.ADDING_DESCRIPTION && !currentTask.descriptionIsEmpty() && blankRegex.matches(inputText)) {
            currentTask.addDescription(inputText)
            tasks.add(currentTask)
            currentTask = Task("",
                               "",
                               "",
                               Priority.NONE)
            status = Status.WAITING_FOR_NEW_ACTION
        }

        return
    }

    private fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }

        tasks.indices.forEach { i -> println("${i + 1}${if (i <= 8) "  " else " "}${tasks[i].date} ${tasks[i].time} ${tasks[i].priority}\n${tasks[i].description}\n") }
    }
}
