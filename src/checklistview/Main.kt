package checklistview

import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class Main() : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return;
        val root: BorderPane = FXMLLoader.load(javaClass.getResource("Main.fxml"))
        val scene = Scene(root, 400.0, 400.0)
        primaryStage.scene = scene
        primaryStage.show()
    }
}

public fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}