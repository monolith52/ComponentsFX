package filetreeview

import javafx.fxml.FXML
import javafx.scene.control.TreeCell
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.util.Callback
import java.io.File

class Controller {

    val root = buildTree(File(".").absoluteFile.parentFile)

    private fun buildTree(file: File): TreeItem<File> {
        val item = TreeItem<File>(file)
        item.children.addAll( file.listFiles().map{
            if (it.isDirectory) buildTree(it) else TreeItem<File>(it)
        })
        return item
    }

    @FXML lateinit private var treeView: TreeView<File>

    @FXML fun initialize() {
        treeView.root = root
        treeView.cellFactory = Callback {
            CustomLabel()
        }
    }

}

class CustomLabel : TreeCell<File>() {
    companion object {
        var icons = mutableMapOf<String, Image>()
        val defaultIcon = Image(CustomLabel::class.java.getResourceAsStream("text.png"))
        val folderIcon = Image(CustomLabel::class.java.getResourceAsStream("folder.png"))
        init {
            val image = Image(CustomLabel::class.java.getResourceAsStream("image.png"))
            icons.put("png", image)
            icons.put("jpg", image)
            icons.put("class", Image(CustomLabel::class.java.getResourceAsStream("exec.png")))
            icons.put("xml", Image(CustomLabel::class.java.getResourceAsStream("markup.png")))
        }
    }

    override fun updateItem(item: File?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            text = ""
            graphic = null
        } else {
            text = item.name
            graphic = ImageView(getIconFor(item.isDirectory, item.extension.toLowerCase()))
        }
    }

    private fun getIconFor(isDir: Boolean, extension: String) : Image {
        if (isDir) return folderIcon
        return icons.get(extension) ?: defaultIcon
    }
}
