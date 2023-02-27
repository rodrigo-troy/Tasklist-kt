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

    fun getTasksNumber() = tasks.size

    fun getStatus() = status

    fun inputAction(task: String) {
        if (status == Status.START_EDITING_TASK ||
            status == Status.START_EDITING_FIELD ||
            status == Status.START_EDITING_DESCRIPTION ||
            status == Status.EDITING_DESCRIPTION ||
            status == Status.EDITING_PRIORITY ||
            status == Status.EDITING_DATE ||
            status == Status.EDITING_TIME) {
            edit(task)
            return
        }
        if (status == Status.START_DELETING_TASK) {
            delete(task)
            return
        }

        if (status == Status.START_ADDING_TASK ||
            status == Status.ADDING_PRIORITY ||
            status == Status.ADDING_DATE ||
            status == Status.ADDING_TIME ||
            status == Status.ADDING_DESCRIPTION) {
            addTask(task)
            return
        }

        when (task) {
            "edit" -> {
                if (tasks.isEmpty()) {
                    println("No tasks have been input")
                    return
                }

                status = Status.START_EDITING_TASK; printTasks()
            }
            "add" -> status = Status.ADDING_PRIORITY
            "print" -> printTable()
            "end" -> end()
            "delete" -> {
                if (tasks.isEmpty()) {
                    println("No tasks have been input")
                    return
                }

                status = Status.START_DELETING_TASK; printTasks()
            }
            else -> println("The input action is invalid")
        }
    }

    private fun edit(task: String) {
        if (status == Status.START_EDITING_DESCRIPTION) {
            status = Status.EDITING_DESCRIPTION
            currentTask.clearDescription()
        }

        if (status == Status.START_EDITING_TASK) {
            if (task.toIntOrNull() == null) {
                println("Invalid task number")
                return
            }

            if (task.toInt() < 1 || task.toInt() > tasks.size) {
                println("Invalid task number")
                return
            }

            currentTask = tasks[task.toInt() - 1]
            status = Status.START_EDITING_FIELD
            return
        }

        if (status == Status.START_EDITING_FIELD) {
            when (task.lowercase().trim()) {
                "task" -> status = Status.START_EDITING_DESCRIPTION
                "priority" -> status = Status.EDITING_PRIORITY
                "date" -> status = Status.EDITING_DATE
                "time" -> status = Status.EDITING_TIME
                else -> println("Invalid field")
            }
            return
        }

        if (status == Status.EDITING_PRIORITY && Priority.getPriority(task) != Priority.NONE) {
            currentTask.priority = Priority.getPriority(task)
            status = Status.WAITING_FOR_NEW_ACTION
            println("The task is changed")
            return
        }

        if (status == Status.EDITING_DATE && !currentTask.dateIsValid(task)) {
            println("The input date is invalid")
            return
        }

        if (status == Status.EDITING_DATE && currentTask.dateIsValid(task)) {
            currentTask.setTaskDate(task)
            status = Status.WAITING_FOR_NEW_ACTION
            println("The task is changed")
            return
        }

        if (status == Status.EDITING_TIME && !currentTask.timeIsValid(task)) {
            println("The input time is invalid")
            return
        }

        if (status == Status.EDITING_TIME && currentTask.timeIsValid(task)) {
            currentTask.setTaskTime(task)
            status = Status.WAITING_FOR_NEW_ACTION
            println("The task is changed")
            return
        }

        if (status == Status.EDITING_DESCRIPTION && currentTask.descriptionIsEmpty() && blankRegex.matches(task)) {
            println("The task is blank")
            status = Status.WAITING_FOR_NEW_ACTION
            return
        }

        if (status == Status.EDITING_DESCRIPTION && !blankRegex.matches(task)) {
            currentTask.addDescription(task)
            return
        }

        if (status == Status.EDITING_DESCRIPTION && !currentTask.descriptionIsEmpty() && blankRegex.matches(task)) {
            currentTask.addDescription(task)
            status = Status.WAITING_FOR_NEW_ACTION
            println("The task is changed")
            return
        }
    }

    private fun addTask(inputText: String) {
        if (status == Status.START_ADDING_TASK) {
            status = Status.ADDING_DESCRIPTION
        }

        if (status == Status.ADDING_PRIORITY && Priority.getPriority(inputText) != Priority.NONE) {
            currentTask.priority = Priority.getPriority(inputText)
            status = Status.ADDING_DATE
            return
        }

        if (status == Status.ADDING_DATE && !currentTask.dateIsValid(inputText)) {
            println("The input date is invalid")
            return
        }

        if (status == Status.ADDING_DATE && currentTask.dateIsValid(inputText)) {
            currentTask.setTaskDate(inputText)
            status = Status.ADDING_TIME
            return
        }

        if (status == Status.ADDING_TIME && !currentTask.timeIsValid(inputText)) {
            println("The input time is invalid")
            return
        }

        if (status == Status.ADDING_TIME && currentTask.timeIsValid(inputText)) {
            currentTask.setTaskTime(inputText)
            status = Status.START_ADDING_TASK
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

    private fun delete(inputText: String) {
        if (inputText.toIntOrNull() == null) {
            println("Invalid task number")
            return
        }

        if (inputText.toInt() < 1 || inputText.toInt() > tasks.size) {
            println("Invalid task number")
            return
        }

        tasks.removeAt(inputText.toInt() - 1)
        println("The task is deleted")
        status = Status.WAITING_FOR_NEW_ACTION
    }

    private fun end() {
        status = Status.EXIT
        println("Tasklist exiting!")
    }

    private fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }

        tasks.indices.forEach { i -> println("${i + 1}${if (i <= 8) "  " else " "}${tasks[i]}\n") }
    }

    fun printTable() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }

        println("+----+------------+-------+---+---+--------------------------------------------+")
        println("| N  |    Date    | Time  | P | D |                   Task                     |")
        println("+----+------------+-------+---+---+--------------------------------------------+")
        tasks.indices.forEach { i ->
            val task = tasks[i]
            val priority = task.priority
            val dueTag = task.getDueTag()
            val description = task.description
            val date = task.date
            val time = task.time
            val taskNumber = i + 1
            val taskNumberString = if (taskNumber <= 8) " $taskNumber  " else " $taskNumber "
            val priorityString = " ${ConsoleColor.getConsoleColor(priority)} "
            val dueTagString = " ${ConsoleColor.getConsoleColor(dueTag)} "
            val descriptionString = description.trim()
            val dateString = if (date.isEmpty()) "            " else " $date "
            val timeString = if (time.isEmpty()) "       " else " $time "

            val descriptionLines = mutableListOf<String>()
            descriptionString.split("\n").forEach { line ->
                val trimmedLine = line.trim()
                if (trimmedLine.length <= 44) {
                    descriptionLines.add(trimmedLine + " ".repeat(44 - trimmedLine.length))
                } else {
                    var descriptionLine = ""
                    trimmedLine.split(" ").forEach { word ->
                        if (trimmedLine.length + word.length <= 44) {
                            descriptionLine += "$word "
                        } else {
                            descriptionLines.add(descriptionLine)
                            descriptionLine = " $word "
                        }
                    }
                    descriptionLines.add(descriptionLine)
                }
            }

            descriptionLines.indices.forEach { j ->
                if (j == 0) {
                    println("|$taskNumberString|$dateString|$timeString|$priorityString|${dueTagString}|${descriptionLines[j]}|")
                } else {
                    println("|    |            |       |   |   |${descriptionLines[j]}|")
                }
            }
            println("+----+------------+-------+---+---+--------------------------------------------+")
        }
    }
}
