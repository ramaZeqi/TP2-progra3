package testGrafo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import Grafo.UnionFind;


public class UnionFindTest {
	@Test(expected=IndexOutOfBoundsException.class)
	public void FueraDeRangotest() {
		UnionFind aux=new UnionFind(5);
		aux.encontrarRaiz(-1);
		
	}
	
	@Test
	public void encontrarRaizTest() {
		UnionFind aux=new UnionFind(5);
		aux.union(2, 4);
		int a2=aux.encontrarRaiz(4);
			
		assertEquals(2,a2);
	}
	
	@Test
	public void CapacidadEquivocaTest() {
		UnionFind aux=new UnionFind(6);
		aux.union(0,1);
		aux.union(3,4);
		aux.union(2,5);
		aux.union(2,3);
			
		assertEquals(1,aux.getCapacidad()[1]);
	}
	
	@Test
	public void CapacidadTest() {
		UnionFind aux=new UnionFind(6);
		aux.union(0,1);
		aux.union(3,4);
		aux.union(2,5);
		aux.union(2,3);
			
		assertEquals(4,aux.getCapacidad()[2]);
	}
		
	@Test
	public void noConectadoTest() {
		UnionFind aux=new UnionFind(5);
		aux.union(2, 4);
		aux.union(1, 3);
		
		assertFalse(aux.estaConectado(2,3));
	}
	
	@Test
	public void ConectadoTest() {
		UnionFind aux=new UnionFind(6);
		aux.union(0,1);
		aux.union(3,4);
		aux.union(2,5);
		aux.union(2,3);
		assertTrue(aux.estaConectado(2,4));
	}
}
