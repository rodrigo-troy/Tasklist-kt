package tasklist

/**
 * Created with IntelliJ IDEA.
$ Project: Tasklist
 * User: rodrigotroy
 * Date: 15-02-23
 * Time: 20:41
 */
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class Task() {
  private var description: String = ""
  private var deadline: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date


}
