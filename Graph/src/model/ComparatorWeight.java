package model;
import java.util.Comparator;

public class ComparatorWeight<T> implements Comparator<Vertex<T>>  {

    @Override
    public int compare(Vertex<T> o1, Vertex<T> o2) {
        if(o1.getWeight()< o2.getWeight()){
            return -1;
        }else if(o1.getWeight()> o2.getWeight()){
            return 1;
        }
        return 0;
    }
}
