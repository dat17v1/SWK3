package weightedgraph;

import java.util.*;

public class Graph {

    private List<Vertex> vertices = new ArrayList<>();
    //  find en måde at gemme Edge objekter
    private Map<Vertex, List<Edge>> edgeMap = new HashMap<>();

    public Map<Vertex, List<Edge>> getEdgeMap() {
        return edgeMap;
    }

    public List<Integer> getNeighbors(int vertex){
        List<Integer> list = new ArrayList<>();
        List<Edge> edgeList = edgeMap.get(getVertex(vertex));
        for (Edge edge : edgeList) {
            list.add(edge.endVertex);
        }
        return list;
    }

    public boolean addVertex(Vertex vertex){
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
            edgeMap.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    public Vertex getVertex(int index){
        if(index >= 0 && index < vertices.size()) {
            return vertices.get(index);
        }
        return null;
    }



    public void addEdge(Edge edge){ // automatisk tilføj modsat retning
        //1. find en måde at gemme Edge objekter. DONE
        //2. Lav et NYT Edge objekt i denne metode, og gem
        Edge edge2 = new Edge(edge.startVertex, edge.endVertex, edge.weight);
        Vertex startVertex = getVertex(edge.startVertex);
        Edge edge3 = new Edge(edge.endVertex, edge.startVertex , edge.weight);
        Vertex endVertex = getVertex(edge.endVertex);
        edgeMap.get(startVertex).add(edge2);
        edgeMap.get(endVertex).add(edge3);
    }

    public void printGraph(){
        for (Map.Entry<Vertex, List<Edge>> entry : edgeMap.entrySet()) {
            System.out.println("Vertex: " + entry.getKey());
            for (Edge edge : entry.getValue()) {
                System.out.println(getVertex(edge.startVertex) +
                        " to " + getVertex(edge.endVertex) +
                        " weight " + edge.weight);
            }
        }
    }

    public Tree dfs(int root){
        // 1. besøg alle Vertices i grafen
        // kan godt anvende en privat rekursiv hjælper-metode
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];
        List<Integer> searchOrder = new ArrayList<>();
        dfs(root, parent, isVisited, searchOrder);
        return new Tree(root, searchOrder, parent);
    }

    private void dfs(int vertex, int[] parent, boolean[] isVisited, List<Integer> searchOrder){
            isVisited[vertex] = true;
            searchOrder.add(vertex);
        for (Integer neighbor : getNeighbors(vertex)) {
            if(! isVisited[neighbor]){
                parent[neighbor] = vertex;
                dfs(neighbor, parent, isVisited, searchOrder);
            }
        }
    }

    public int getSize() {
        return vertices.size();
    }

    public class Tree{
        int root;
        List<Integer> searchOrder;
        int[] parent;

        public Tree(int root, List<Integer> searchOrder, int[] parent) {
            this.root = root;
            this.searchOrder = searchOrder;
            this.parent = parent;
        }
        public String toString(){
            String out = "Root: " + root + "\n";
            for (Integer vertex:searchOrder) {
                out += " visited: " + vertex + "\n";
            }
            out += "Parents: \n";
            for (int i = 0; i < vertices.size(); i++) {
                out += "vertex: " + i + " has parent: " + parent[i] + "\n";
            }

            return out;
        }
    }



    public Stack<Vertex> dijkstra(int source, int target){
        int[] dist = new int[vertices.size()];
        int[] prev = new int[vertices.size()];
        Set<Integer> set = new HashSet<>();
        for (int i=0; i < vertices.size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            set.add(i);
        }
        dist[source] = 0;
        while(!set.isEmpty()){
          int u = getSmallestDistanceInSet(set, dist);
          set.remove(u);
            for (Integer v : getNeighbors(u)) {
                // find edge from u to v:
               Edge edgeFromUtoV = getEdgeFromUtoV(u, v);
                int alt = dist[u] + edgeFromUtoV.weight;
                if(alt < dist[v]){
                    dist[v] = alt;
                    prev[v] = u;
                }
            }
        }
        return getShortestPath(target, dist[target], prev);
    }

    private Stack<Vertex> getShortestPath(int target, int totalDistance, int[] prev) {
        Stack<Vertex> shortestPath = new Stack<>();
        int u = target;

        while(prev[u] >= 0){
            shortestPath.push(getVertex(u));
            u = prev[u];
        }
        shortestPath.push(getVertex(u));
        System.out.println("Dijkstra result:");
        System.out.println(shortestPath.toString());
        Stack<Vertex> shortestPathCopy = (Stack<Vertex>) shortestPath.clone();
        while(!shortestPath.isEmpty()){

            System.out.println(shortestPath.pop());
        }
        System.out.println("total distance: " + totalDistance);
        return shortestPathCopy;
    }

    private Edge getEdgeFromUtoV(int u, int v) {
        List<Edge> edges = edgeMap.get(getVertex(u));
        Edge theEdge = null;
        for (Edge edge : edges) {
            if(edge.startVertex == u && edge.endVertex == v){
                theEdge = edge;
                break;
            }
        }
        return theEdge;
    }

    private int getSmallestDistanceInSet(Set<Integer> set, int[] dist){
        int smallest = Integer.MAX_VALUE;
        int indexAtSmallest = -1;
        for (Integer v :
                set) {
            if(dist[v] < smallest){
                 smallest = dist[v];
                 indexAtSmallest = v;
            }
        }
        return indexAtSmallest;
    }

}
