package weightedgraph;

public class Edge {

    int startVertex;
    int endVertex;
    int weight;

    public Edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public Edge(int startVertex, int endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }
}
