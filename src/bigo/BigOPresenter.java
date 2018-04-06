package bigo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BigOPresenter extends Application {

    BigO bigO = new BigO(this);
    private Label tOne = new Label("t1");
    private Label  tTwo = new Label("t2");
    private TextField n1Field = new TextField("20000");
    private TextField n2Field = new TextField("40000");
    NumberFormat formatter = new DecimalFormat("#0.000");
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hboxTxtFields = getNfieldsHBox();
        HBox hbox = getLabelHBox();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hboxTxtFields,hbox);

        for (int i = 1; i <= bigO.methods.size(); i++) {
            Button button = new Button("Method" + i);
            final int m = i;
            button.setOnAction(event -> {
                bigO.runMethod(m);
            });
            vBox.getChildren().add(button);
        }
        Scene scene = new Scene(vBox, 400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getLabelHBox() {
        HBox hbox = new HBox();
        Label methodLbl = new Label("Method");
        HBox.setMargin(tOne, new Insets(0,0,0,50));
        HBox.setMargin(tTwo, new Insets(0,0,0,80));
        hbox.getChildren().addAll(methodLbl,tOne,tTwo);
        return hbox;
    }

    private HBox getNfieldsHBox() {
        HBox hboxTxtFields = new HBox();
        HBox.setMargin(n1Field, new Insets(0,0,0,80));
        n1Field.setMaxWidth(110);
        n2Field.setMaxWidth(120);
        n1Field.textProperty().addListener((observable -> {
            setN1();
        }));
        setN1();
        n2Field.textProperty().addListener((observable -> {
            setN2();
        }));
        setN2();
        hboxTxtFields.getChildren().addAll(n1Field, n2Field);
        return hboxTxtFields;
    }

    private void setN2() {
        Integer n2 = Integer.parseInt(n2Field.getText());
        bigO.setN2(n2);
    }

    private void setN1() {
        Integer n1 = Integer.parseInt(n1Field.getText());
        bigO.setN1(n1);
    }


    public void setTimeResult(TimeResult timeResult) {
        tOne.setText("T(ms) "+ formatter.format(timeResult.getT1()/1000000.0));
        tTwo.setText("T(ms) "+ formatter.format(timeResult.getT2()/1000000.0));
        System.out.println("timeresult in: " + timeResult);
    }
}
