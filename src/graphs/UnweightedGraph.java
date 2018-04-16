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
        boolean[] visited = new boolean[vertices.size()];
        dfs(v,visited);
        Tree tree = new Tree(v,visited);
        return tree;
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    private void dfs(int v, boolean[] visited){
        // 1. sæt relevant position til TRUE
        visited[v] = true;
        // 2. skaf alle naboer til v
        // 3. for hver nabo u, besøg u såfrem den IKKE allerede er besøgt.
        for (Integer u : getNeighbors(v)) {  // v=1, u = 5,4,3,2
            // hvad skal vi spørge om her?
            if(!visited[u]){
                dfs(u,visited);
            }
        }
    }

    private List<Integer> getNeighbors(int u){
        List<Integer> list = new ArrayList<>();
        for (Edge edge : neighbors.get(u)) {
            list.add(edge.v);
        }
        return list;
    }

}








