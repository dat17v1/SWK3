package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnweightedGraph<V> implements IGraph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    @Override
    public void addVertex(V vertex) {
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
        }
    }

    @Override
    public V getVertex(int v) {

        return vertices.get(v);
    }

    @Override
    public void addEdge(int u, int v) {
        neighbors.get(u).add(new Edge(u,v));
        neighbors.get(v).add(new Edge(v,u));
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }


    @Override
    public Tree dfs(int v) {
        return null;
    }

}
