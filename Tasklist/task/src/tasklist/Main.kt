package tasklist


fun main() {
    val tasklist = Tasklist()

    while (tasklist.getStatus() != Status.EXIT) {
        if (tasklist.getStatus() == Status.WAITING_FOR_NEW_ACTION) {
            println("Input an action (add, print, end):")
        }

        if (tasklist.getStatus() == Status.START_ADDING_TASK) {
            println("Input a new task (enter a blank line to end):")
        }

        if (tasklist.getStatus() == Status.ADDING_PRIORITY) {
            println("Input the task priority (C, H, N, L):")
        }

        if (tasklist.getStatus() == Status.ADDING_DATE) {
            println("Input the date (yyyy-mm-dd):")
        }

        if (tasklist.getStatus() == Status.ADDING_TIME) {
            println("Input the time (hh:mm):")
        }

        tasklist.inputAction(readln().trim().lowercase())
    }
}


