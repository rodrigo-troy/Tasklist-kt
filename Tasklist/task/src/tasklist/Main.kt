package tasklist


fun main() {
    val tasklist = Tasklist()

    while (tasklist.getStatus() != Status.EXITING) {
        if (tasklist.getStatus() == Status.WAITING_FOR_NEW_ACTION) {
            println("Input an action (add, print, end):")
        }

        if (tasklist.getStatus() == Status.START_ADDING_TASK) {
            println("Input a new task (enter a blank line to end):")
        }

        tasklist.inputAction(readln().trim().lowercase())
    }
}


