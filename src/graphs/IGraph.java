package graphs;

import java.util.List;

public interface IGraph<V> {
    public void addVertex(V vertex);
    public V getVertex(int v);
    public void addEdge(int u, int v);
    public List<V> getVertices();
    public Tree dfs(int v);
}
