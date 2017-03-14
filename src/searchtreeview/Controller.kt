package searchtreeview

import javafx.fxml.FXML
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.controlsfx.control.textfield.CustomTextField

class Controller {

    val root: TreeItem<String>

    init {
        val items1 = arrayOf("Appearance",
                "Menus and Toolbars",
                "System Settings",
                "File Colors",
                "Scopes",
                "Notifications",
                "Quick Lists",
                "Path Variables")
        val items2 = arrayOf("Passwords",
                "HTTP Proxy",
                "Updates",
                "Usage Statistics")
        root = TreeItem<String>("Appearance & Behavior")
        root.isExpanded = true
        root.children.addAll(items1.map{TreeItem(it)})
        root.children[1].isExpanded = true
        root.children[1].children.addAll(items2.map { TreeItem(it) })
    }

    @FXML lateinit private var treeView: TreeView<String>
    @FXML lateinit private var searchField: CustomTextField

    @FXML fun initialize() {
        val icon = javaClass.getResource("search.png").openStream().use { Image(it) }
        searchField.left = ImageView(icon)
        treeView.root = root
        TreeItemFilter(root).bind( searchField.textProperty() )
    }

}