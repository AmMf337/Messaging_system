package model;

import java.util.Comparator;

public class ComparatorWeightEdges<T> implements Comparator<Edge<T>> {
    @Override
    public int compare(Edge<T> o1, Edge<T> o2) {
        if(o1.getWeight()< o2.getWeight()){
            return -1;
        }else if(o1.getWeight()> o2.getWeight()){
            return 1;
        }
        return 0;
    }
}
