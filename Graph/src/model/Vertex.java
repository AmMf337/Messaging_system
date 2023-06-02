package model;

import java.util.ArrayList;

public class Vertex<T> {
    private ArrayList<Vertex<T>> vertexes;
    private Vertex<T> predecessor;
    private int distance;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private T value;
    private Color color;

    public Vertex(T value) {
        this.value = value;
        vertexes = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }
    public void addVertex(Vertex<T> vertex){
        vertexes.add(vertex);
    }
    public void setValue(T value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public ArrayList<Vertex<T>> getVertexes() {
        return vertexes;
    }

    public void setVertexes(ArrayList<Vertex<T>> vertexes) {
        this.vertexes = vertexes;
    }

   

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<T> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex<T> predecessor) {
        this.predecessor = predecessor;
    }
    @Override
    public String toString() {
        String msj = "Valor Vertice"+value.toString()+"\n";
        msj += "Distancia "+distance+"\n";
        msj += "Peso "+weight+"\n";
        msj += "Aristas: "+"\n";
        for (Vertex<T>a:vertexes) {
            msj += "Valor arista " +a.getValue()+" peso "+a.getWeight()+"\n";
        }
        if(this.predecessor != null){
            msj = "Valor predecesor "+predecessor.getValue();
        }
        return msj;
    }
}
