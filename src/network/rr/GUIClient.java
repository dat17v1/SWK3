package network.rr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class GUIClient extends Application {
    private PrintWriter pw;

    public static void main(String[] args) {
        Application.launch(args); // vi starter "manuelt"
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            // 1. Lav Container
        VBox vBox = new VBox();

        //2. Lav GUI element. Noget synligt
        makeGUIElements(vBox);

        //3. Lav Scene
        Scene scene = new Scene(vBox, 200,200 );

        //4. Angiv Scene i Stage
        primaryStage.setScene(scene);

        // 5. Kald på Stage.show()
        primaryStage.show();
    }

    private void makeGUIElements(VBox vBox) {
        TextField nameField = new TextField();
        nameField.setPromptText("indtast navn");
        TextField redField = new TextField();
        redField.setPromptText("indtast rød 0-255");
        TextField greenField = new TextField();
        greenField.setPromptText("indtast grøn 0-255");
        TextField blueField = new TextField();
        blueField.setPromptText("indtast blå 0-255");
        Label label = new Label("Brug pilene til at styre din player");
        Button connectBtn = new Button("Forbind");
        connectBtn.setOnAction(event -> {
            System.out.println("de har trykket på mig!");
            // Opret forbindelse til Houston 192.168.0.8
            // Skal sende med følgende protokol:
            this.connectToServer();
            // 1. send navn på en seperat linie
            // 2. send farver (rgb) på en seperat linie "12,144,54"
        });
        vBox.getChildren().addAll(nameField,redField, greenField, blueField,label);
        vBox.getChildren().addAll(connectBtn);
    }

    private void connectToServer(){
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.8");
            Socket socket = new Socket(inetAddress, 6780);
            pw = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("har forbindelse til server");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
