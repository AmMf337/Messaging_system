package model;

import java.util.*;

public class GraphAbjacentList<T> implements IGraph<T>{

    boolean isManaged;
    ArrayList<Vertex<T>> vertexes;
    private int time;
    public GraphAbjacentList(boolean isManaged) {
        this.isManaged = isManaged;
        vertexes = new ArrayList<>(time);
        time = 0;
    }

    @Override
    public void dfs() {
        for (Vertex<T> vertex : vertexes) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }
        for (Vertex<T> vertex : vertexes) {
            if(vertex.getColor()==Color.WHITE){
                dfsVisit(vertex,time);
            }
        }
    }

    @Override
    public void dfsVisit(Vertex<T> u, int time) {
        time++;
        u.setDistance(time);
        u.setColor(Color.GREY);
        for (Vertex<T> v:u.getVertexes()) {
            if(v.getColor()== Color.WHITE) {
                v.setPredecessor(u);
                dfsVisit(v,time);
            }
        }
        u.setColor(Color.BLACK);
        time++;
    }

    @Override
    public void bfs(Object origin) {
        int originPosition = 0;
        for (int i = 0;i<vertexes.size();i++) {
            if(vertexes.get(i).getValue() !=origin){
                vertexes.get(i).setColor(Color.WHITE);
                vertexes.get(i).setDistance(0);
                vertexes.get(i).setPredecessor(null);
            }else{
                originPosition = i;
            }
        }
        vertexes.get(originPosition).setColor(Color.GREY);
        vertexes.get(originPosition).setDistance(0);
        vertexes.get(originPosition).setPredecessor(null);
        Queue<Vertex<T>> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertexes.get(originPosition));
        while (!vertexQueue.isEmpty()){
            Vertex<T> u = vertexQueue.poll();
            for (Vertex<T> n:u.getVertexes()) {
                if(n.getColor()== Color.WHITE){
                    n.setColor(Color.GREY);
                    n.setDistance(u.getDistance()+1);
                    n.setPredecessor(u);
                    vertexQueue.add(n);
                }
            }
            u.setColor(Color.BLACK);
        }

    }

    @Override
    public void createEdge(T vertexA,T vertexB,int weight) {
        int positionA = -1;
        int positionB = -1;
        for (int i = 0;i<vertexes.size();i++) {
            if(vertexes.get(i).getValue()==vertexA){
                positionA = i;
            }
            if(vertexes.get(i).getValue()==vertexB){
                positionB = i;
            }
        }
        
        if(positionA !=-1 && positionB != -1 && isManaged){
            if(!vertexes.get(positionA).getVertexes().contains(vertexes.get(positionB))){
                Vertex<T> v = vertexes.get(positionB);
                v.setWeight(weight);
                vertexes.get(positionA).addVertex(v);
            }
            
            return;
        } else if (positionA !=-1 && positionB != -1 && !isManaged) {
            if(!vertexes.get(positionA).getVertexes().contains(vertexes.get(positionB))){
                Vertex<T> r = vertexes.get(positionB);
                r.setWeight(weight);
                vertexes.get(positionA).addVertex(r);
            }
            if(!vertexes.get(positionB).getVertexes().contains(vertexes.get(positionA))){
                Vertex<T> c = vertexes.get(positionA);
                c.setWeight(weight);
                vertexes.get(positionB).addVertex(c);
            }
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertexes.add(vertex);
    }

    @Override
    public void deleteVertex(T value) {
        for (Vertex<T> vertex : vertexes) {
            for (Vertex<T> vertex2 : vertex.getVertexes()) {
                if (vertex2.getValue() == value) {
                    vertexes.remove(vertex);
                }

            }
        }
        for(int i = 0;i<vertexes.size();i++){
            if(vertexes.get(i).getValue()==value){
                vertexes.remove(i);
            }
        }
    }
    public Vertex<T> searchVertex(T value) throws Exception{
        int position = -1;
        for (int i = 0;i<vertexes.size();i++) {
            if(vertexes.get(i).getValue()==value){
               position = i;
            }
        }
        if(position!=1){
            return vertexes.get(position);
        }else{
            throw new Exception("vertex not found");
        }
    }

    @Override
    public void deleteEdge(T vertexA,T vertexB) {
        int positionA = 0;
        int positionB = 0;
        for (int i = 0;i<vertexes.size();i++) {
            if(vertexes.get(i).getValue()==vertexA){
                positionA = i;
            }
            if(vertexes.get(i).getValue()==vertexB){
                positionB = i;
            }
        }
        if(isManaged){
            
            if(vertexes.get(positionA).getVertexes().contains(vertexes.get(positionB))){
                vertexes.get(positionA).getVertexes().remove(vertexes.get(positionB));
            }
            
        }else{
            if(vertexes.get(positionA).getVertexes().contains(vertexes.get(positionB))){
                vertexes.get(positionA).getVertexes().remove(vertexes.get(positionB));
            }
            if(vertexes.get(positionB).getVertexes().contains(vertexes.get(positionA))){
                vertexes.get(positionB).getVertexes().remove(vertexes.get(positionA));
            }
        }
    }
    public void dijkstra(T origin){
        int originPosition = 0;
        PriorityQueue<Vertex<T>> s = new PriorityQueue<>(Collections.reverseOrder(new ComparatorDistance<T>()));
        for(int i = 1;i<vertexes.size();i++){
            if(vertexes.get(i).getValue()!=origin){
                vertexes.get(i).setDistance(Integer.MAX_VALUE);
                vertexes.get(i).setPredecessor(null);
                s.add(vertexes.get(i));
            }else{
                originPosition = i;
            }

        }
        vertexes.get(originPosition).setDistance(0);
        while (!s.isEmpty()){
            Vertex<T> u = s.poll();
            for (Vertex<T> vertex: u.getVertexes()) {
                int alt = u.getDistance() + vertex.getWeight();
                if(alt<vertex.getDistance()){
                    s.remove(vertex);
                    vertex.setDistance(alt);
                    vertex.setWeight(0);
                    vertex.setPredecessor(u);
                    s.add(vertex);
                }
            }
        }
    }
    
    public ArrayList<Vertex<T>> getVertexes() {
        return vertexes;
    }
    
}
