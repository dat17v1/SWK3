package network.rr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIClient extends Application {

    public static void main(String[] args) {
        Application.launch(args); // vi starter "manuelt"
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            // 1. Lav Container
        VBox vBox = new VBox();

        //2. Lav GUI element. Noget synligt
        Label label = new Label("Brug pilene til at styre din player");
        vBox.getChildren().add(label);

        //3. Lav Scene
        Scene scene = new Scene(vBox);

        //4. Angiv Scene i Stage
        primaryStage.setScene(scene);

        // 5. Kald på Stage.show()
        primaryStage.show();
    }
}
