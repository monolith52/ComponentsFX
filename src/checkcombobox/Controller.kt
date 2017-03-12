package checkcombobox

import javafx.fxml.FXML
import javafx.scene.control.MultipleSelectionModel
import org.controlsfx.control.CheckComboBox

class Controller {

    @FXML lateinit private var checkComboBox: CheckComboBox<String>

    @FXML
    fun initialize() {
        checkComboBox.items.add("item1")
        checkComboBox.items.add("item2")
        checkComboBox.items.add("item3")
        checkComboBox.items.add("item4")

        checkComboBox.checkModel.check(1)
    }

}