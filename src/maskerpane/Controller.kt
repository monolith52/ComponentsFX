package maskerpane

import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.controlsfx.control.MaskerPane

class Controller {

    @FXML lateinit private var maskerPane: MaskerPane
    @FXML lateinit private var button: Button

    @FXML fun onButtonAction(@Suppress("UNUSED_PARAMETER") event: ActionEvent) {
        val task = object : Task<String>() {
            override fun call(): String {
                (0..400).forEach { count ->
                    updateProgress(count.toDouble(), 400.0)
                    Thread.sleep(10)
                }
                return "done"
            }
        }
        maskerPane.progressProperty().bind(task.progressProperty())
        maskerPane.visibleProperty().bind(task.runningProperty())
        Thread(task).start()
    }
}