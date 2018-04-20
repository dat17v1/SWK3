package weightedgraph;

public class Vertex {

    String name;
    int x, y;

    public Vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Vertex(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
