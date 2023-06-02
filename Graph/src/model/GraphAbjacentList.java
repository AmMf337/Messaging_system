package model;

import java.util.*;

public class GraphAbjacentList<T> implements IGraph<T>{

    boolean isManaged;
    private ArrayList<Vertex<T>> vertexes;
    private int time;
    public GraphAbjacentList(boolean isManaged) {
        this.isManaged = isManaged;
        vertexes = new ArrayList<>(time);
        time = 0;
    }
    public String printBFS(T origin) {
        bfs(origin);

        String msj = "";
        for (Vertex<T> vertex : vertexes) {
            if (vertex.getColor() == Color.BLACK) {
                msj = vertex.getValue()+" -> ";
            }
        }


        return msj;
    }
    public String printGraph(){
        String msj = "";
        for (Vertex<T> v:vertexes) {
            msj += v.toString()+"\n";
        }
        return  msj;
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
    public void bfs(T origin) {
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
    public void createEdge(T vertexA,T vertexB, int weight) {
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
        for (Vertex<T> v:vertexes) {
            if(v.getValue().equals(vertex.getValue())){
                return;
            }
        }
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
    public Vertex<T> searchVertex(T value) {
        int position = -1;
        for (int i = 0;i<vertexes.size();i++) {
            if(vertexes.get(i).getValue()==value){
               position = i;
            }
        }
        if(position!=1){
            return vertexes.get(position);
        }else{
            return null;
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
    @Override
    public void floydWarshall() {
        int size = vertexes.size();
        int[][] distances = new int[size][size];
        int[][] next = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
            Arrays.fill(next[i], -1);
        }

        for (int i = 0; i < size; i++) {
            Vertex<T> u = vertexes.get(i);
            distances[i][i] = 0;
            for (Vertex<T> v : u.getVertexes()) {
                int j = vertexes.indexOf(v);
                distances[i][j] = v.getWeight();
                next[i][j] = j;
            }
        }


        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE &&
                            distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }


        for (int i = 0; i < size; i++) {
            Vertex<T> u = vertexes.get(i);
            for (Vertex<T> v : u.getVertexes()) {
                int j = vertexes.indexOf(v);
                v.setWeight(distances[i][j]);
                v.setPredecessor(vertexes.get(next[i][j]));
            }
        }
    }
    @Override
    public void prim(T value){
        PriorityQueue<Vertex<T>> q = new PriorityQueue<>(Collections.reverseOrder(new ComparatorWeight<T>()));
        for (Vertex<T> u:vertexes) {
            u.setWeight(Integer.MAX_VALUE);
            u.setColor(Color.WHITE);
            if(u.getValue()==value){
                u.setWeight(0);
                u.setPredecessor(null);
            }
            q.add(u);
        }
        Iterator<Vertex<T>> it = q.iterator();
        while(!q.isEmpty()){
            Vertex<T> u = q.poll();
            for (Vertex<T> v:u.getVertexes()) {
                if(v.getColor()==Color.WHITE){
                    for (Vertex<T> s: q) {
                        if(s.getValue()==v.getValue() && v.getWeight()<s.getWeight()){
                            q.remove(s);
                            s.setWeight(v.getWeight());
                            s.setPredecessor(u);
                            q.offer(s);
                        }
                        u.setColor(Color.BLACK);
                    }
                }
            }
        }
    }
    public ArrayList<Vertex<T>> getVertexes() {
        return vertexes;
    }
    @Override
    public ArrayList<Edge<T>> kruskal() {

        ArrayList<Edge<T>> mst = new ArrayList<>();


        ArrayList<Edge<T>> allEdges = new ArrayList<>();


        for (Vertex<T> vertex : vertexes) {
            ArrayList<Vertex<T>> neighbors = vertex.getVertexes();
            for (Vertex<T> neighbor : neighbors) {
                int weight = neighbor.getWeight();
                Edge<T> edge = new Edge<>(vertex, neighbor, weight);
                allEdges.add(edge);
            }
        }


        allEdges.sort(new ComparatorWeightEdges<>());


        Map<Vertex<T>, Vertex<T>> disjointSets = new HashMap<>();
        for (Vertex<T> vertex : vertexes) {
            disjointSets.put(vertex, vertex);
        }


        for (Edge<T> edge : allEdges) {
            Vertex<T> u = edge.getStart();
            Vertex<T>  v = edge.getEnd();


            Vertex<T> uSet = find(disjointSets, u);
            Vertex<T> vSet = find(disjointSets, v);
            if (!uSet.equals(vSet)) {

                mst.add(edge);


                disjointSets.put(vSet, uSet);
            }
        }

        return mst;
    }

    private Vertex<T> find(Map<Vertex<T>, Vertex<T>> disjointSets, Vertex<T> vertex) {
        if (!disjointSets.get(vertex).equals(vertex)) {
            disjointSets.put(vertex, find(disjointSets, disjointSets.get(vertex)));
        }
        return disjointSets.get(vertex);
    }


}
