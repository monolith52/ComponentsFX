package checklistview

import javafx.fxml.FXML
import org.controlsfx.control.CheckListView

class Controller {

    @FXML lateinit private var checkListView: CheckListView<String>

    @FXML
    fun initialize() {
        checkListView.items.add("item1")
        checkListView.items.add("item2")
        checkListView.items.add("item3")
        checkListView.items.add("item4")

        checkListView.checkModel.check(1)
    }

}