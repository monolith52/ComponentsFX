package breadcrumbbar

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TreeItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.controlsfx.control.BreadCrumbBar


class Controller {

    @FXML lateinit private var bar: BreadCrumbBar<String>

    @FXML
    private fun initialize() {
        val root = TreeItem("ルート")
        val item1 = TreeItem("アイテム1")
        val item2 = TreeItem("アイテム2")
        val item11 = TreeItem("アイテム11")
        val item12 = TreeItem("アイテム12")
        item1.children.addAll(item11, item12)
        root.children.addAll(item1, item2)
        bar.selectedCrumb = item11

        bar.isAutoNavigationEnabled = true
        bar.setOnCrumbAction { event ->
//            val source = event.source as BreadCrumbBar<String>
//            val item = event.selectedCrumb
//            val value = item.value
        }
    }

//    @FXML lateinit private var bar: BreadCrumbBar<Image>
//
//    @FXML
//    private fun initialize() {
//        val root = TreeItem(Image("breadcrumbbar/root.png"))
//        val item1 = TreeItem(Image("breadcrumbbar/item1.png"))
//        val item2 = TreeItem(Image("breadcrumbbar/item2.png"))
//        val item11 = TreeItem(Image("breadcrumbbar/item11.png"))
//        val item12 = TreeItem(Image("breadcrumbbar/item12.png"))
//        item1.children.addAll(item11, item12)
//        root.children.addAll(item1, item2)
//
//        bar.selectedCrumb = item11
//        val factory = bar.crumbFactory
//        bar.setCrumbFactory { item ->
//            Button("", ImageView(item.value))
//        }
//    }


}