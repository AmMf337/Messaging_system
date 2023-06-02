
package model;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GrafoTest<V> {
    private Grafo<Integer> grafo;

    @Before
    public void setUp() {
        grafo = new Grafo<>(5);
    }

    @Test
    public void testInsertarArista() {
        grafo.insertarArista(0, 1, 5);
        assertTrue(grafo.existeArista(0, 1));
        assertEquals(Integer.valueOf(5), grafo.obtenerPesoArista(0, 1));
    }

    @Test
    public void testExisteArista() {
        assertFalse(grafo.existeArista(0, 1));
    }

    @Test
    public void testObtenerPesoArista() {
        assertNull(grafo.obtenerPesoArista(0, 1));
    }

    @Test
    public void testEliminarArista() {
        grafo.insertarArista(0, 1, 5);
        assertEquals(Integer.valueOf(5), grafo.eliminarArista(0, 1));
        assertFalse(grafo.existeArista(0, 1));
        assertNull(grafo.obtenerPesoArista(0, 1));
    }

    @Test
    public void testLiberarGrafo() {
        grafo.insertarArista(0, 1, 5);
        grafo.liberarGrafo();
        assertFalse(grafo.existeArista(0, 1));
        assertNull(grafo.obtenerPesoArista(0, 1));
    }

    @Test
    public void testDijkstra() {
        grafo.insertarArista(0, 1, 10);
        grafo.insertarArista(0, 2, 5);
        grafo.insertarArista(1, 2, 2);
        grafo.insertarArista(1, 3, 1);
        grafo.insertarArista(2, 1, 3);
        grafo.insertarArista(2, 3, 9);
        grafo.insertarArista(2, 4, 2);
        grafo.insertarArista(3, 4, 4);
        grafo.insertarArista(4, 3, 6);

        List<Integer> distancias = grafo.dijkstra(0);
        assertEquals(5, distancias.size());
        assertEquals(Integer.valueOf(0), distancias.get(0));
        assertEquals(Integer.valueOf(8), distancias.get(1));
        assertEquals(Integer.valueOf(5), distancias.get(2));
        assertEquals(Integer.valueOf(9), distancias.get(3));
    }
    @Test
    public void testTieneAdyacentes_VerticeSinAdyacentes_DebeRetornarFalse() {
        Grafo<Integer> grafo = new Grafo<>(3);

        boolean resultado = grafo.tieneAdyacentes(0);

        assertFalse(resultado);
    }

    @Test
    public void testTieneAdyacentes_VerticeConAdyacentes_DebeRetornarTrue() {
        Grafo<Integer> grafo = new Grafo<>(3);
        grafo.insertarArista(0, 1, 10);

        boolean resultado = grafo.tieneAdyacentes(0);

        assertTrue(resultado);
    }

    @Test
    public void testTieneAdyacentes_VerticeFueraDeRango_DebeLanzarExcepcion() {
        Grafo<Integer> grafo = new Grafo<>(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            grafo.tieneAdyacentes(5);
        });
    }
    @Test
    public void testObtenerPrimerAdy_VerticeSinAdyacentes_DebeLanzarExcepcion() {
        Grafo<Integer> grafo = new Grafo<>(3);

        assertThrows(UnsupportedOperationException.class, () -> {
            grafo.obtenerPrimerAdy(0);
        });
    }

    @Test
    public void testObtenerPrimerAdy_VerticeConAdyacentes_DebeRetornarPrimerAdyacente() {
        
        Grafo<Integer> grafo = new Grafo<>(3);
        grafo.insertarArista(0, 1, 10);

        int resultado = grafo.obtenerPrimerAdy(0);

        assertEquals(1, resultado);
    }

    @Test
    public void testObtenerPrimerAdy_VerticeFueraDeRango_DebeLanzarExcepcion() {
        Grafo<Integer> grafo = new Grafo<>(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            grafo.obtenerPrimerAdy(5);
        });
    }
    @Test
    public void testSgteAdyacente_NoHaySiguienteAdyacente_DebeRetornarMenosUno() {
        Grafo<Integer> grafo = new Grafo<>(3);
        grafo.insertarArista(0, 1, 10);

        int resultado = grafo.sgteAdyacente(0, 1);

        assertEquals(-1, resultado);
    }

    @Test
    public void testSgteAdyacente_HaySiguienteAdyacente_DebeRetornarSiguienteAdyacente() {
        Grafo<Integer> grafo = new Grafo<>(3);
        grafo.insertarArista(0, 1, 10);
        grafo.insertarArista(0, 2, 20);

        int resultado = grafo.sgteAdyacente(0, -1);

        assertEquals(1, resultado);
    }

    @Test
    public void testSgteAdyacente_VerticeFueraDeRango_DebeLanzarExcepcion() {
        Grafo<Integer> grafo = new Grafo<>(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            grafo.sgteAdyacente(5, 0);
        });
    }
    @Test
    public void testBfs_GrafoConUnVertice_DebeRetornarListaConUnSoloVertice() {
        Grafo<Integer> grafo = new Grafo<>(1);

        List<Integer> resultado = grafo.bfs(0);

        assertEquals(1, resultado.size());
        assertEquals(0, resultado.get(0));
    }

    @Test
    public void testBfs_GrafoConVariosVertices_DebeRetornarRecorridoBfsCorrecto() {
        Grafo<Integer> grafo = new Grafo<>(6);
        grafo.insertarArista(0, 1, 10);
        grafo.insertarArista(0, 2, 20);
        grafo.insertarArista(1, 3, 30);
        grafo.insertarArista(2, 3, 40);
        grafo.insertarArista(2, 4, 50);
        grafo.insertarArista(3, 4, 60);
        grafo.insertarArista(3, 5, 70);

        List<Integer> resultado = grafo.bfs(0);

        assertEquals(6, resultado.size());
        assertEquals(0, resultado.get(0));
        assertEquals(1, resultado.get(1));
        assertEquals(2, resultado.get(2));
        assertEquals(3, resultado.get(3));
        assertEquals(4, resultado.get(4));
        assertEquals(5, resultado.get(5));
    }

    @Test
    public void testBfs_VerticeFueraDeRango_DebeLanzarExcepcion() {
        
        Grafo<Integer> grafo = new Grafo<>(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            grafo.bfs(5);
        });
    }
    
    
    

}