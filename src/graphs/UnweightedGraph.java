package graphs;

import java.util.ArrayList;
import java.util.List;

public class UnweightedGraph<V> implements IGraph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    @Override
    public void addVertex(V vertex) {

    }

    @Override
    public V getVertex() {
        return null;
    }

    @Override
    public void addEdge(int u, int v) {

    }

    @Override
    public List<V> getVertices() {
        return null;
    }

    @Override
    public Tree dfs(int v) {
        return null;
    }
}
