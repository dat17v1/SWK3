package graphs;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ConnectedCircles extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new CirclePane(), 450, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class CirclePane extends Pane {

        public CirclePane(){
            setOnMouseClicked(e -> {
                Point2D point2D = new Point2D(e.getX(), e.getY());
                if(!isInsideCircle(point2D)) {
                    getChildren().add(new Circle(e.getX(), e.getY(), 40));
                }else{
                    Circle circle = getCircle(point2D);
                    getChildren().remove(circle);
                }
                colorIfConnected();
            });
        }

    private IGraph<Node> graph = new UnweightedGraph<>();
    private void colorIfConnected(){
            if(getChildren().size() == 0){
                return;
            }
            for(Node node: getChildren()){ // her laver vi grafen, men uden Edges
                graph.addVertex(node);
            }
            for(int i=0; i<getChildren().size(); i++){
                for(int j= i + 1; j<getChildren().size(); j++) {
                    Circle circle1 = (Circle)getChildren().get(i);
                    Circle circle2 = (Circle)getChildren().get(j);
                    if(overlaps(circle1, circle2)){
                      graph.addEdge(i,j);
                    }
                }
            }
        Tree tree = graph.dfs(0);
        System.out.println("Result:" + tree);
        boolean areAllConnected = tree.areAllVerticesVisited();
       
        for (Node circle : getChildren()) {
            if(areAllConnected){
                ((Circle)circle).setFill(Color.RED);
            }else{
                ((Circle)circle).setFill(Color.WHITE);
                ((Circle)circle).setStroke(Color.BLACK);
            }
        }

    }

    private Circle getCircle(Point2D point){
            for(Node circle: this.getChildren()){
                if(circle.contains(point))
                    return (Circle)circle;
            }
            return null;
        }


    private boolean isInsideCircle(Point2D point){
        for(Node circle: this.getChildren()){
            if(circle.contains(point))
                return true;
        }
        return false;
    }

    }

    public static boolean overlaps(Circle c1, Circle c2){
        Point2D c1Center = new Point2D(c1.getCenterX(), c1.getCenterY());
        Point2D c2Center = new Point2D(c2.getCenterX(), c2.getCenterY());
        double radiusForBoth = c1.getRadius() + c2.getRadius();
        return c1Center.distance(c2Center) <= radiusForBoth;
    }
}
