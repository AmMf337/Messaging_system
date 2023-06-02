package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GraphAbjacentListTest {
    private GraphAbjacentList<Integer> graph;

    @Before
    public void setUp() {
        graph = new GraphAbjacentList<>(true);
    }

    @Test
    public void testDFS() {
        // Agregar vértices
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        // Crear aristas
        graph.createEdge(1, 2, 9);

        // Realizar DFS
        graph.dfs();

        // Verificar los valores de distancia después de DFS
        assertEquals(2,vertex2.getDistance());
       
    }
    @Test
    public void testDFS2() {
        
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        
        graph.createEdge(1, 2, 4);
        graph.createEdge(2, 3, 6);
        graph.createEdge(2, 4, 7);

        
        graph.dfs();

       
        assertTrue(vertex4.getColor()==Color.BLACK);
       
    }
    @Test
    public void testDFS3() {
        
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        
        graph.createEdge(1, 2, 3);
        graph.createEdge(2, 3, 2);
        graph.createEdge(2, 4, 1);
        graph.createEdge(1, 3, 5);
        graph.createEdge(4, 5, 7);
       
        graph.dfs();

        
        assertEquals(4,vertex5.getDistance());
       
    }

    @Test
    public void testBFSDistance1() {
        // Agregar vértices
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
       

        // Crear aristas
        graph.createEdge(1, 2, 5);
        

        // Realizar BFS desde el vértice 1
        graph.bfs(1);

        // Verificar los valores de distancia después de BFS
        
        assertTrue(graph.getVertexes().get(0).getColor()==Color.BLACK);
        
    }
    @Test
    public void testBFSDistance2() {
        
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        
        graph.createEdge(1, 2, 7);
        graph.createEdge(2, 3, 9);

       
        graph.bfs(1);

        
        
        assertTrue(graph.getVertexes().get(1).getColor()==Color.BLACK);
        
    }
    @Test
    public void testBFSDistance3() {
        // Agregar vértices
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        // Crear aristas
        graph.createEdge(1, 2, 4);
        graph.createEdge(2, 3, 2);
        graph.createEdge(3, 4, 1);
        // Realizar BFS desde el vértice 1
        graph.bfs(1);

        // Verificar los valores de distancia después de BFS
        
        assertTrue(graph.getVertexes().get(3).getColor()==Color.BLACK);
        
    }
   
    //Este es un grafo no dirigido por lo tanto solo adiciona el 2 vertice al primer parametro pero no viceversa
    @Test
    public void testCreate1Edge() {
        // Agregar vértices
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        // Crear una arista entre los vértices 1 y 2
        graph.createEdge(1, 2, 6);

        // Verificar la adición de la arista
        assertEquals(1, vertex1.getVertexes().size());
        
    }
    @Test
    public void testCreate2Edge() {
        
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
       
        graph.createEdge(1, 2, 7);
        graph.createEdge(1, 3, 9);
       
        assertEquals(2, vertex1.getVertexes().size());
        assertEquals(0, vertex2.getVertexes().size());
        
    }
    @Test
    public void testCreate3Edge() {
       
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
       
        graph.createEdge(1, 2, 4);
       
        graph.createEdge(1, 3, 3);
        graph.createEdge(3, 1, 2);

       
        assertEquals(2, vertex1.getVertexes().size());
        assertEquals(0, vertex2.getVertexes().size());
        assertEquals(1, vertex3.getVertexes().size());
    }

    @Test
    public void testAdd1Vertex(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        graph.addVertex(vertex1);
        assertSame(vertex1, graph.getVertexes().get(0));
    }
    @Test
    public void testAdd2Vertex(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        assertSame(vertex1, graph.getVertexes().get(0));
        assertSame(vertex2, graph.getVertexes().get(1));
    }
    @Test
    public void testAdd3Vertex(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        assertSame(vertex1, graph.getVertexes().get(0));
        assertSame(vertex2, graph.getVertexes().get(1));
        assertSame(vertex3, graph.getVertexes().get(2));
    }
    @Test
    public void testDeleteEdge1(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        
        graph.getVertexes().add(0, vertex1);
        graph.addVertex(vertex2);
        
        graph.createEdge(1, 2, 7);

        graph.deleteEdge(1, 2);

        assertEquals(0, vertex1.getVertexes().size());
        
    }
    @Test
    public void testDeleteEdge2(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        
        graph.getVertexes().add(0, vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        
        graph.createEdge(1, 2, 2);
        graph.createEdge(1, 3, 5);

        graph.deleteEdge(1, 2);
        graph.deleteEdge(1, 3);

        assertEquals(0, graph.getVertexes().get(0).getVertexes().size());
        
    }
    @Test
    public void testDeleteEdge3(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);

        graph.getVertexes().add(0, vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        
        graph.createEdge(1, 2, 6);
        graph.createEdge(1, 3, 4);
        graph.createEdge(1, 4, 7);

        graph.deleteEdge(1, 2);
        graph.deleteEdge(1, 3);
        graph.deleteEdge(1, 4);

        assertEquals(0, graph.getVertexes().get(0).getVertexes().size());
        
    }
    @Test
    public void testDeleteVertex1(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        

        graph.addVertex(vertex1);
        graph.deleteVertex(1);

        assertEquals(0, graph.getVertexes().size());
        
    }
    @Test
    public void testDeleteVertex2(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.deleteVertex(1);
        graph.deleteVertex(2);

        assertEquals(0, graph.getVertexes().size());
        
    }
    @Test
    public void testDeleteVertex3(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.createEdge(2, 1, 5);
        graph.createEdge(3, 1, 3);
        graph.deleteVertex(1);
        
        try{
            assertEquals(0, graph.searchVertex(2).getVertexes().size());
            assertEquals(0,graph.searchVertex(3).getVertexes().size() );
        }catch(Exception e){

        }
        
    }
    @Test
    public void testSearchVertex1(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        

        graph.addVertex(vertex1);
        
        
        try{
            assertEquals(0, graph.searchVertex(1).getVertexes().size());
        }catch(Exception e){

        }
        
    }
    @Test
    public void testSearchVertex2(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex2);
        
        
        try{
            assertEquals(0, graph.searchVertex(2).getVertexes().size());
        }catch(Exception e){

        }
        
    }
    @Test
    public void testSearchVertex3(){
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex2);
        
        
        try{
            assertSame("vertex not found", graph.searchVertex(3).getVertexes().size());
        }catch(Exception e){

        }
        
    }

    @Test
    public void testPrimWithEmptyGraph() {


        // Ejecutar el algoritmo prim
        graph.prim(0);


        assert graph.getVertexes().isEmpty();

    }

    @Test
    public void testPrimWithDisconnectedGraph() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        graph.prim(1);


        assertEquals(2,graph.getVertexes().size());
        assert graph.getVertexes().contains(vertex1);
        assert graph.getVertexes().contains(vertex2);
    }

    @Test
    public void testPrimWithConnectedGraph() {

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.prim(1);

        assert graph.getVertexes().size() == 3;

    }

    @Test
    public void testFloydWarshallWithConnectedGraph() {

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.createEdge(2, 1, 5);

        graph.createEdge(1, 3, 10);

        graph.floydWarshall();


        assertEquals(5, vertex1.getWeight());
        assert vertex2.getPredecessor() == null;

        assertEquals(0, vertex2.getWeight());
        assertEquals(vertex1.getPredecessor().getValue(), vertex1.getValue());


        assertEquals(10, vertex3.getWeight());
        assertEquals(vertex3.getPredecessor().getValue(), vertex3.getValue());


    }

    @Test
    public void testFloydWarshallWithDisconnectedGraph() {

        // Añadir vértices y aristas al grafo no conexo
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.floydWarshall();


        assert vertex1.getWeight() == 0;
        assert vertex1.getPredecessor() == null;

        assert vertex2.getWeight() == 0;
        assert vertex2.getPredecessor() == null;

        assert vertex3.getWeight() == 0;
        assert vertex3.getPredecessor() == null;


    }

    @Test
    public void testFloydWarshallWithGraphContainingNegativeCycle() {


        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.createEdge(vertex1.getValue(), vertex2.getValue(), 2);
        graph.createEdge(vertex2.getValue(), vertex3.getValue(), -5);
        graph.createEdge(vertex3.getValue(), vertex1.getValue(), 1);


        graph.floydWarshall();


        boolean hasNegativeCycle = graph.getVertexes().stream()
                .anyMatch(vertex -> vertex.getWeight() < 0);

        assert hasNegativeCycle;


    }
    @Test
    public void testKruskal() {

        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.createEdge(1, 2, 1);
        graph.createEdge(1, 3, 3);
        graph.createEdge(2, 3, 1);
        graph.createEdge(2, 4, 4);
        graph.createEdge(3, 4, 1);
        graph.createEdge(3, 5, 2);
        graph.createEdge(4, 5, 3);

        ArrayList<Edge<Integer>> minimumSpanningTree = graph.kruskal();

        Assert.assertEquals(4, minimumSpanningTree.size());

    }
    @Test
    public void testKruskalDisconnectedGraph() {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.createEdge(1, 2, 1);
        graph.createEdge(3, 4, 2);

        ArrayList<Edge<Integer>>  minimumSpanningTree = graph.kruskal();

        Assert.assertEquals(2, minimumSpanningTree.size());
        Edge<Integer> b = new Edge<>(v1,v2,1);
        Edge<Integer> c = new Edge<>(v3,v4,2);
        Assert.assertFalse(minimumSpanningTree.contains(b));
        Assert.assertFalse(minimumSpanningTree.contains(c));


    }
    @Test
    public void testKruskalGraphWithCycles() {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.createEdge(1, 2, 1);
        graph.createEdge(3, 4, 2);

        ArrayList<Edge<Integer>>  minimumSpanningTree = graph.kruskal();

        Assert.assertEquals(2, minimumSpanningTree.size());
        Edge<Integer> b = new Edge<>(v1,v2,1);
        Edge<Integer> c = new Edge<>(v3,v4,2);
        Assert.assertFalse(minimumSpanningTree.contains(b));
        Assert.assertFalse(minimumSpanningTree.contains(c));


    }






}
