package customfield

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.CheckBoxTreeItem
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import org.controlsfx.control.CheckTreeView
import org.controlsfx.control.textfield.CustomPasswordField
import org.controlsfx.control.textfield.CustomTextField
import javax.swing.border.LineBorder

class Controller {

    @FXML lateinit private var textField: CustomTextField
    @FXML lateinit private var passwordField: CustomPasswordField

    @FXML
    fun initialize() {
        textField.left = LeftLabel()
        passwordField.left = LeftLabel()
    }

    class LeftLabel : Label() {
        init {
            `setStyle`("-fx-border-color: #cccccc; -fx-border-width: 0px 1px 0px 0px;")
            text = "secure"
            graphic = ImageView(Image("customfield/lock.png"))
            textFill = Color(11.0/255, 128.0/255, 67.0/255, 1.0)
            padding = Insets(5.0, 10.0, 5.0, 5.0)
        }
    }
}