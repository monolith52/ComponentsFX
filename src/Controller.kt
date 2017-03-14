import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.stage.Stage
import javafx.stage.Window
import javafx.util.Callback
import sun.plugin2.jvm.RemoteJVMLauncher
import java.awt.event.ActionListener
import kotlin.reflect.KClass
import kotlin.reflect.primaryConstructor

class Controller {

    @FXML lateinit var listView: ListView<KClass<out Application>>

    @FXML fun initialize() {
        listView.items.add(breadcrumbbar.Main::class)
        listView.items.add(checkcombobox.Main::class)
        listView.items.add(checklistview.Main::class)
        listView.items.add(checktreeview.Main::class)
        listView.items.add(customfield.Main::class)
        listView.items.add(maskerpane.Main::class)
        listView.cellFactory = Callback { ClassCell() }
    }

    class ClassCell : ListCell<KClass<out Application>>() {
        val button = Button()
        init {
            padding = Insets(0.0, 0.0, 0.0, 0.0)
            button.padding = Insets(5.0, 5.0, 5.0, 5.0)
            button.prefWidthProperty().bind(widthProperty())
            graphic = button
        }

        override fun updateItem(item: KClass<out Application>?, empty: Boolean) {
            super.updateItem(item, empty)
            button.isVisible = !empty
            if (item == null) return
            button.text = item.qualifiedName
            button.onAction = EventHandler {
                val stage = Stage()
                item.primaryConstructor?.call()?.start(stage)
            }
        }
    }
}