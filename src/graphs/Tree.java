package graphs;

public class Tree {

    private int root;
    private boolean[] visited;

    public Tree(int root, boolean[] visited) {
        this.root = root;
        this.visited = visited;
    }


    public boolean areAllVerticesVisited(){
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]){
                count++;
            }
        }
        return count == visited.length;
    }

    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < visited.length; i++) {
            out += "Vertex: " + i + " visited: " + visited[i] + "\n";
        }
        return out;
    }


}
