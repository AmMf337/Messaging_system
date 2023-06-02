package model;
import java.util.*;

public class Grafo<V> {
    private final int NUM_VERTICES;
    private V[][] grafo;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.NUM_VERTICES = numVertices;
        grafo = (V[][]) new Object[NUM_VERTICES][NUM_VERTICES];

        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                grafo[i][j] = null;
            }
        }
    }

    public Grafo() {
        this(5);
    }

    public void insertarArista(int v1, int v2, V pesoArista) throws ArrayIndexOutOfBoundsException,
            IllegalArgumentException {
        if (pesoArista == null) {
            throw new IllegalArgumentException();
        }
        this.grafo[v1][v2] = pesoArista;
        this.grafo[v2][v1] = pesoArista;
    }

    public boolean existeArista(int v1, int v2) throws ArrayIndexOutOfBoundsException {
        return (grafo[v1][v2] != null);
    }

    public V obtenerPesoArista(int v1, int v2) throws ArrayIndexOutOfBoundsException {
        return grafo[v1][v2];
    }

    public V eliminarArista(int v1, int v2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (grafo[v1][v2] == null)
            throw new IllegalArgumentException("La arista No existe");
        V peso = grafo[v1][v2];
        grafo[v1][v2] = null;
        grafo[v2][v1] = null;
        return peso;
    }

    public void liberarGrafo() {
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                grafo[i][j] = null;
            }
        }
    }

    public void imprimirGrafo() {
        System.out.printf("  %d", 0);
        for (int i = 1; i < grafo.length; i++) {
            System.out.printf(" %d", i);
        }
        System.out.println();

        for (int i = 0; i < grafo.length; i++) {
            System.out.printf("%d ", i);
            for (int j = 0; j < grafo[i].length; j++) {
                System.out.printf("%s ", grafo[i][j]);
            }
            System.out.println();
        }
    }


    public boolean tieneAdyacentes(int v) throws ArrayIndexOutOfBoundsException {
        int vActual = 0;
        boolean existeLista = false;

        while (vActual < this.NUM_VERTICES && !existeLista) {
            if (grafo[v][vActual] != null) {
                existeLista = true;
            } else {
                vActual = vActual + 1;
            }
        }

        return existeLista;
    }

    public int obtenerPrimerAdy(int v) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        int adyacente = -1;
        int vActual = 0;
        boolean existeLista = false;

        while (vActual < this.NUM_VERTICES && !existeLista) {
            if (grafo[v][vActual] == null) {
                vActual = vActual + 1;
            } else {
                adyacente = vActual;
                existeLista = true;
            }
        }

        if (!existeLista)
            throw new UnsupportedOperationException("No existe Lista");

        return adyacente;
    }

    public int sgteAdyacente(int v, int anteriorAd)
            throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        int adyacente = 0;
        int vActual = anteriorAd + 1;
        boolean existeLista = false;

        while (vActual < this.NUM_VERTICES && !existeLista) {
            if (grafo[v][vActual] == null) {
                vActual = vActual + 1;
            } else {
                adyacente = vActual;
                existeLista = true;
            }
        }

        if (!existeLista)
            adyacente = -1;
        return adyacente;
    }
   
    public List<Integer> dijkstra(int origen) throws ArrayIndexOutOfBoundsException {
        List<Integer> distancias = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        for (int i = 0; i < NUM_VERTICES; i++) {
            distancias.add(Integer.MAX_VALUE);
        }
        distancias.set(origen, 0);

        // Recorrer todos los vértices
        for (int i = 0; i < NUM_VERTICES; i++) {
            int verticeActual = obtenerVerticeConMenorDistancia(distancias, visitados);
            visitados.add(verticeActual);

            // Actualizar las distancias de los vértices adyacentes no visitados
            for (int j = 0; j < NUM_VERTICES; j++) {
                if (!visitados.contains(j) && grafo[verticeActual][j] != null) {
                    int distanciaArista = (int) grafo[verticeActual][j];
                    int distanciaTotal = distancias.get(verticeActual) + distanciaArista;

                    if (distanciaTotal < distancias.get(j)) {
                        distancias.set(j, distanciaTotal);
                    }
                }
            }
        }

        return distancias;
    }

    private int obtenerVerticeConMenorDistancia(List<Integer> distancias, Set<Integer> visitados) {
        int minDistancia = Integer.MAX_VALUE;
        int minVertice = -1;

        for (int i = 0; i < NUM_VERTICES; i++) {
            if (!visitados.contains(i) && distancias.get(i) < minDistancia) {
                minDistancia = distancias.get(i);
                minVertice = i;
            }
        }

        return minVertice;
    }
    public List<Integer> bfs(int origen) throws ArrayIndexOutOfBoundsException {
        List<Integer> recorrido = new ArrayList<>();
        Queue<Integer> cola = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();

        cola.offer(origen);
        visitados.add(origen);

        while (!cola.isEmpty()) {
            int verticeActual = cola.poll();
            recorrido.add(verticeActual);

            for (int i = 0; i < NUM_VERTICES; i++) {
                if (!visitados.contains(i) && grafo[verticeActual][i] != null) {
                    cola.offer(i);
                    visitados.add(i);
                }
            }
        }

        return recorrido;
    }
    public List<Integer> dfs(int origen) throws ArrayIndexOutOfBoundsException {
        List<Integer> recorrido = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        dfsRecursivo(origen, visitados, recorrido);

        return recorrido;
    }

    private void dfsRecursivo(int vertice, Set<Integer> visitados, List<Integer> recorrido) {
        visitados.add(vertice);
        recorrido.add(vertice);

        for (int i = 0; i < NUM_VERTICES; i++) {
            if (!visitados.contains(i) && grafo[vertice][i] != null) {
                dfsRecursivo(i, visitados, recorrido);
            }
        }
    }
    public V[][] floydWarshall() {
        V[][] distancias = Arrays.copyOf(grafo, NUM_VERTICES);

        for (int k = 0; k < NUM_VERTICES; k++) {
            for (int i = 0; i < NUM_VERTICES; i++) {
                for (int j = 0; j < NUM_VERTICES; j++) {
                    if (distancias[i][k] != null && distancias[k][j] != null) {
                        V distanciaIK = distancias[i][k];
                        V distanciaKJ = distancias[k][j];
                        V distanciaIJ = distancias[i][j];

                        if (distanciaIK instanceof Number && distanciaKJ instanceof Number && distanciaIJ instanceof Number) {
                            double pesoIK = ((Number) distanciaIK).doubleValue();
                            double pesoKJ = ((Number) distanciaKJ).doubleValue();
                            double pesoIJ = ((Number) distanciaIJ).doubleValue();
                            double nuevaDistancia = pesoIK + pesoKJ;

                            if (distanciaIJ == null || nuevaDistancia < pesoIJ) {
                                distancias[i][j] = (V) Double.valueOf(nuevaDistancia);
                            }
                        }
                    }
                }
            }
        }

        return distancias;
    }
    
    public List<Arista<V>> kruskal() {
        List<Arista<V>> arbolMinimo = new ArrayList<>();
        PriorityQueue<Arista<V>> colaPrioridad = new PriorityQueue<>(Comparator.comparing(Arista::getPeso));
        DisjointSet<Integer> disjointSet = new DisjointSet<>();

        for (int i = 0; i < NUM_VERTICES; i++) {
            for (int j = i + 1; j < NUM_VERTICES; j++) {
                if (grafo[i][j] != null) {
                    colaPrioridad.offer(new Arista<>(i, j, grafo[i][j]));
                }
            }
        }

        for (int i = 0; i < NUM_VERTICES; i++) {
            disjointSet.makeSet(i);
        }

        while (!colaPrioridad.isEmpty()) {
            Arista<V> aristaActual = colaPrioridad.poll();
            int vertice1 = aristaActual.getVertice1();
            int vertice2 = aristaActual.getVertice2();

            if (disjointSet.findSet(vertice1) != disjointSet.findSet(vertice2)) {
                arbolMinimo.add(aristaActual);

                disjointSet.union(vertice1, vertice2);
            }
        }

        return arbolMinimo;
    }

    private static class Arista<V> {
        private int vertice1;
        private int vertice2;
        private V peso;

        public Arista(int vertice1, int vertice2, V peso) {
            this.vertice1 = vertice1;
            this.vertice2 = vertice2;
            this.peso = peso;
        }

        public int getVertice1() {
            return vertice1;
        }

        public int getVertice2() {
            return vertice2;
        }

        public V getPeso() {
            return peso;
        }
    }

    private static class DisjointSet<T> {
        private Map<T, Node<T>> map;

        public DisjointSet() {
            map = new HashMap<>();
        }

        public void makeSet(T data) {
            Node<T> node = new Node<>(data);
            map.put(data, node);
        }

        public T findSet(T data) {
            Node<T> node = map.get(data);
            if (node == null) {
                return null;
            }

            Node<T> parent = findSet(node.getParent());
            node.setParent(parent);
            return parent != null ? parent.getData() : data;
        }

        public void union(T data1, T data2) {
            T parent1 = findSet(data1);
            T parent2 = findSet(data2);

            if (parent1 != null && parent2 != null && !parent1.equals(parent2)) {
                Node<T> node1 = map.get(parent1);
                Node<T> node2 = map.get(parent2);

                if (node1.getRank() >= node2.getRank()) {
                    node2.setParent(node1);
                    if (node1.getRank() == node2.getRank()) {
                        node1.setRank(node1.getRank() + 1);
                    }
                } else {
                    node1.setParent(node2);
                }
            }
        }

        private static class Node<T> {
            private T data;
            private Node<T> parent;
            private int rank;

            public Node(T data) {
                this.data = data;
                this.parent = this;
                this.rank = 0;
            }

            public T getData() {
                return data;
            }

            public Node<T> getParent() {
                return parent;
            }

            public void setParent(Node<T> parent) {
                this.parent = parent;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }
        }
    }
}
