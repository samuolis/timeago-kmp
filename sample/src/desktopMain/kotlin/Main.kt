import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.samuolis.timeago.sample.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TimeAgo Sample"
    ) {
        App()
    }
}
