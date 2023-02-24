package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 19:35
 */
enum class Status {
    WAITING_FOR_NEW_ACTION,
    ADDING_DESCRIPTION,
    ADDING_PRIORITY,
    ADDING_DATE,
    ADDING_TIME,
    EDITING_DESCRIPTION,
    EDITING_PRIORITY,
    EDITING_DATE,
    EDITING_TIME,
    START_ADDING_TASK,
    START_DELETING_TASK,
    START_EDITING_TASK,
    START_EDITING_FIELD,
    EXIT
}
