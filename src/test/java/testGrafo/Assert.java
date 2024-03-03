package testGrafo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Localidad;

public class Assert {

	public static void Ordenado(Grafo<Localidad> grafo, List<Arista<Localidad>> aristas) {
		assertEquals(grafo.getAristas().size(),aristas.size());
		//los bigdecimal de las aristas fueron seteados con 2 digitos en su parte decimal
		assertEquals(new BigDecimal("1.00"),aristas.get(0).getPeso());
		assertEquals(new BigDecimal("4.00"),aristas.get(1).getPeso());
		assertEquals(new BigDecimal("6.00"),aristas.get(2).getPeso());
		assertEquals(new BigDecimal("8.00"),aristas.get(3).getPeso());
		assertEquals(new BigDecimal("8.00"),aristas.get(4).getPeso());
		assertEquals(new BigDecimal("12.00"),aristas.get(5).getPeso());
		
	}

	public static void igualdad(Grafo<Localidad>  kruskal, Grafo<Localidad>  aux) {
		// TODO Auto-generated method stub
		
	}
	
}
