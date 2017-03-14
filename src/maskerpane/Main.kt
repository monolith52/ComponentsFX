package maskerpane

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class Main() : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return
        val root: StackPane = FXMLLoader.load(javaClass.getResource("Main.fxml"))
        val scene = Scene(root, 300.0, 300.0)
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}