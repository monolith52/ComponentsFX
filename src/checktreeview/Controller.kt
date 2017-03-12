package checktreeview

import javafx.fxml.FXML
import javafx.scene.control.CheckBoxTreeItem
import javafx.scene.layout.Pane
import org.controlsfx.control.CheckTreeView

class Controller {

    @FXML lateinit private var checkTreeView: CheckTreeView<String>

    @FXML
    fun initialize() {
        val root = CheckBoxTreeItem<String>("root")
        val item2 = CheckBoxTreeItem("item2")
        root.isExpanded = true
        root.children.addAll(
                CheckBoxTreeItem("item1"),
                item2,
                CheckBoxTreeItem("item3"),
                CheckBoxTreeItem("item4")
        )
        checkTreeView.root = root
        checkTreeView.checkModel.check(item2)
    }

}