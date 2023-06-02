package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrafoTest {
    private Grafo<Integer> grafo;

    @BeforeEach
    public void setUp() {
        grafo = new Grafo<>();
    }

    @Test
    public void testInsertarArista() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(1, 2, 3);
        grafo.insertarArista(2, 3, 2);

        Assertions.assertTrue(grafo.existeArista(0, 1));
        Assertions.assertTrue(grafo.existeArista(1, 2));
        Assertions.assertTrue(grafo.existeArista(2, 3));
        Assertions.assertFalse(grafo.existeArista(0, 2));
    }

    @Test
    public void testObtenerPesoArista() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(1, 2, 3);
        grafo.insertarArista(2, 3, 2);

        Assertions.assertEquals(5, grafo.obtenerPesoArista(0, 1));
        Assertions.assertEquals(3, grafo.obtenerPesoArista(1, 2));
        Assertions.assertEquals(2, grafo.obtenerPesoArista(2, 3));
    }

    @Test
    public void testEliminarArista() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(1, 2, 3);
        grafo.insertarArista(2, 3, 2);

        Assertions.assertTrue(grafo.existeArista(0, 1));
        Assertions.assertEquals(5, grafo.eliminarArista(0, 1));
        Assertions.assertFalse(grafo.existeArista(0, 1));
    }

    @Test
    public void testTieneAdyacentes() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(1, 2, 3);

        Assertions.assertTrue(grafo.tieneAdyacentes(0));
        Assertions.assertTrue(grafo.tieneAdyacentes(1));
        Assertions.assertFalse(grafo.tieneAdyacentes(2));
    }

    @Test
    public void testObtenerPrimerAdy() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(0, 2, 3);

        Assertions.assertEquals(1, grafo.obtenerPrimerAdy(0));
        Assertions.assertEquals(2, grafo.obtenerPrimerAdy(1));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> grafo.obtenerPrimerAdy(2));
    }

    @Test
    public void testSgteAdyacente() {
        grafo.insertarArista(0, 1, 5);
        grafo.insertarArista(0, 2, 3);

        Assertions.assertEquals(2, grafo.sgteAdyacente(0, 0));
        Assertions.assertEquals(-1, grafo.sgteAdyacente(1, 2));
    }
}

