package tasklist

fun main() {
    val tasks = mutableListOf<String>()
    println("Input the tasks (enter a blank line to end):")

    while (true) {
        val task = readln()

        if (task.isBlank()) {
            break
        }

        tasks.add(task.trim())
    }

    if (tasks.isEmpty()) {
        println("No tasks have been input")
        return
    }

    tasks.indices.forEach { i -> println("${i + 1}${if (i <= 8) "  " else " "}${tasks[i]}") }
}


