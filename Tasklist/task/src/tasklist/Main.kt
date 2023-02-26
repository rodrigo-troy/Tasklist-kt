package tasklist


fun main() {
    val tasklist = Tasklist()

    while (tasklist.getStatus() != Status.EXIT) {
        if (tasklist.getStatus() == Status.WAITING_FOR_NEW_ACTION) {
            println("Input an action (add, print, edit, delete, end):")
        }

        if (tasklist.getStatus() == Status.START_ADDING_TASK || tasklist.getStatus() == Status.START_EDITING_DESCRIPTION) {
            println("Input a new task (enter a blank line to end):")
        }

        if (tasklist.getStatus() == Status.ADDING_PRIORITY || tasklist.getStatus() == Status.EDITING_PRIORITY) {
            println("Input the task priority (C, H, N, L):")
        }

        if (tasklist.getStatus() == Status.ADDING_DATE || tasklist.getStatus() == Status.EDITING_DATE) {
            println("Input the date (yyyy-mm-dd):")
        }

        if (tasklist.getStatus() == Status.ADDING_TIME || tasklist.getStatus() == Status.EDITING_TIME) {
            println("Input the time (hh:mm):")
        }

        if (tasklist.getStatus() == Status.START_DELETING_TASK) {
            println("Input the task number (1-${tasklist.getTasksNumber()}):")
        }

        if (tasklist.getStatus() == Status.START_EDITING_TASK) {
            println("Input the task number (1-${tasklist.getTasksNumber()}):")
        }

        if (tasklist.getStatus() == Status.START_EDITING_FIELD) {
            println("Input a field to edit (priority, date, time, task):")
        }

        tasklist.inputAction(readln().trim().lowercase())
    }
}


