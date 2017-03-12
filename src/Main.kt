import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class Main() : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return;
        val loader = FXMLLoader(javaClass.getResource("List.fxml"))
        val root: BorderPane = loader.load()
        val scene = Scene(root, 300.0, 400.0)
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}