package weightedgraph;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

public class GraphView extends Pane {

    private Graph graph;


    public GraphView(Graph graph) {
        this.graph = graph;

        for (int i = 0; i < graph.getSize(); i++) {
            Vertex vertex = graph.getVertex(i);

            getChildren().add(new Circle(vertex.x, vertex.y, 16)); // Display a vertex
            getChildren().add(new Text(vertex.x - 8, vertex.y - 18, vertex.name));
            List<Integer> neighbors = graph.getNeighbors(i);
            for(int v: neighbors){
                // Draw an edge
                int x2 = graph.getVertex(v).x;
                int y2 = graph.getVertex(v).y;
                getChildren().add(new Line(vertex.x,vertex.y,x2,y2));
            }
        }
    }
}
