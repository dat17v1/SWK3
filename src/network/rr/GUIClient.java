package network.rr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class GUIClient extends Application {

    private double width = 1000;
    private double height = 1000;
    private PrintWriter pw;
    private GraphicsContext graphicsContext;
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, width,height);
        addNodes(vBox);
        vBox.getChildren().add(createDrawPane());

        assignKeyListener(scene);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void assignKeyListener(Scene scene) {
        //scene.addEventFilter(KeyEvent.KEY_PRESSED, ...); // nogle PC'er foretrækker denne
        // fremfor scene.setOnKeyPressed()
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    pw.println("UP"); break;
                case DOWN:  pw.println("DOWN"); break;
                case LEFT:  pw.println("LEFT"); break;
                case RIGHT: pw.println("RIGHT"); break;

            }
        });
    }

    private Canvas createDrawPane() {
        Canvas canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    graphicsContext.beginPath();
                    graphicsContext.moveTo(event.getX(), event.getY());
                    pw.println("P," + event.getX() + "," + event.getY());
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    graphicsContext.lineTo(event.getX(), event.getY());
                    graphicsContext.stroke();
                    pw.println("D," + event.getX() + "," + event.getY());
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    graphicsContext.lineTo(event.getX(), event.getY());
                    graphicsContext.stroke();
                    graphicsContext.closePath();
                    pw.println("R," + event.getX() + "," + event.getY());
                });
        initDraw(graphicsContext);
        return canvas;
    }

    private void addNodes(VBox vBox) {
        TextField nameField = new TextField("Hanna");
        nameField.setPromptText("name");
        TextField redField = new TextField("44");
        redField.setPromptText("red 0-255");
        TextField greenField = new TextField("133");
        greenField.setPromptText("green 0-255");
        TextField blueField = new TextField("200");
        blueField.setPromptText("blue 0-255");
        Button connectBtn = new Button("Connect");
        connectBtn.setOnAction( e -> {
            connect();
            nameField.setDisable(true);
            redField.setDisable(true);
            greenField.setDisable(true);
            blueField.setDisable(true);
            String colors = redField.getText() + "," + greenField.getText() +
                    "," +blueField.getText();
            pw.println(nameField.getText());
            pw.println(colors);
            graphicsContext.setStroke(Color.web("rgb(" + colors + ")"));
        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> {
            pw.println("QUIT");
            pw.close();
            System.exit(0);
        });
        vBox.getChildren().addAll(nameField, redField,
                greenField, blueField,connectBtn,quitButton);
    }

    private void initDraw(GraphicsContext gc){
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
    }

    private void connect(){
        try{
            InetAddress inetAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(inetAddress, 6780);
            pw = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("oprettet forbindelse til server");

        }catch (Exception e){

        }
    }
}
