package model;

public class Edge<T> {
    Vertex<T> start;
    Vertex<T> end;
    int weight;
  public Edge(Vertex<T> startingVertex, Vertex<T>  endingVertex, int weight){
      this.start = startingVertex;
      end = endingVertex;
      this.weight = weight;
    }

    public Vertex<T> getStart() {
        return start;
    }

    public void setStart(Vertex<T> start) {
        this.start = start;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public void setEnd(Vertex<T> end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
      return "Start: "+start.getValue()+"\n"+
              "End: "+end.getValue()+"\n"+
              "weight: "+weight;
    }
}
