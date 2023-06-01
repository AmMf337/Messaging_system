package model;

public interface IGraph<T> {

    public void dfs();
    public void dfsVisit(Vertex<T> u,int time);

    public void bfs(T origin);

    public void createEdge(T vertexA,T vertexB,int weight);


    public void addVertex(Vertex<T> vertex);

    public void deleteVertex(T value);
    public void deleteEdge(T vertexA,T vertexB);

    public void floydWarshall();

    public  void prim(T value);
}
