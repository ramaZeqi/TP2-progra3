package testGrafo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import Grafo.Arista;
import Grafo.Coordenadas;
import Grafo.Localidad;
import Grafo.RedTelefonica;



public class RedTelefonicaTest {
	@Test
	public void TomarVecinoTest() {
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad(""," ",new Coordenadas(2.51,2.341));
		Arista<Localidad> aux=new Arista<Localidad>(vert,vert2,"21.212");
		assertEquals(aux.getVecinoDe(vert2),vert);
	}
	
	@Test
	public void HashAristastest() {
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,2.341));
		Arista<Localidad> aux=new Arista<Localidad>(vert,vert2,"21.212");
		Arista<Localidad> aux2=new Arista<Localidad>(vert2,vert,"21.212"); 
		assertEquals(aux.hashCode(),aux2.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void LoopTest() {
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,2.41));
		new Arista<Localidad>(vert,vert2,"21.212");
	}
	
	@Test
	public void agregarVerticeRedTelefonicaTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		g.agregarVertice(vert);
		assertTrue(g.contieneVertice(vert));
				
	}	
	
	@Test
	public void agregarVerticeRedTelefonicaTest2() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		g.agregarVertice(vert);
		g.agregarVertice(vert2);
		assertTrue(g.contieneVertice(vert));
				
	}

	@Test
	public void ContieneAristaRedTelefonicaTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		g.agregarArista(vert,vert2,"21.22");
		Arista<Localidad> aux=new Arista<Localidad>(vert2,vert,"21.22");
		
		
		assertTrue(g.contieneArista(aux));
		
	}

	@Test
	public void AgregarAristaRedTelefonicaTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		g.agregarArista(vert,vert2,"21.22");
		
		
		
		assertEquals(g.getAristas().size(),1);
		
	}
	
	@Test
	public void EliminarAristaRedTelefonicaTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		Arista<Localidad> aux=new Arista<Localidad>(vert2,vert,"21.22");
		g.agregarArista(vert,vert2,"21.22");
		g.eliminarArista(aux);
		
		
		assertEquals(g.getAristas().size(),0);
		
	}
	
	@Test
	public void EliminarAristaRedTelefonicaTest1() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		Localidad vert3=new Localidad("","",new Coordenadas(25.51,2.41));
		Arista<Localidad> aux=new Arista<Localidad>(vert3,vert,"21.22");
		g.agregarArista(vert,vert2,"21.22");
		g.eliminarArista(aux);
		
		
		assertEquals(g.getAristas().size(),1);
		
	}	
	
	@Test
	public void EliminarAristaRedTelefonica_vecinoTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(2.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.51,22.41));
		Localidad vert3=new Localidad("","",new Coordenadas(25.51,2.41));
		Arista<Localidad> aux=new Arista<Localidad>(vert3,vert,"21.22");
		
		g.agregarArista(vert,vert2,"21.22");
		g.eliminarArista(aux);
		
		
		assertEquals(1, vert.getVecinos().size());
		
	}	
	
	@Test
	public void EliminarLocalidadRedTelefonica_LocalidadTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(1.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.1,22.41));
		Localidad vert3=new Localidad("","",new Coordenadas(3.51,2.41));
		g.agregarVertice(vert);
		g.agregarVertice(vert2);
		g.agregarVertice(vert3);
		g.agregarArista(vert,vert2,"21.22");		
		g.agregarArista(vert,vert3,"21.22");
		g.agregarArista(vert3,vert2,"21.22");
		
		g.eliminarVertice(vert);
		
		
		assertEquals(2,g.getVertices().size());
		
	}
	
	@Test
	public void EliminarLocalidadRedTelefonica_AristaTest() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(1.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.1,22.41));
		Localidad vert3=new Localidad("","",new Coordenadas(3.51,2.41));
		g.agregarVertice(vert);
		g.agregarVertice(vert2);
		g.agregarVertice(vert3);
		g.agregarArista(vert,vert2,"21.22");		
		g.agregarArista(vert,vert3,"21.22");
		g.agregarArista(vert3,vert2,"21.22");
		
		g.eliminarVertice(vert2);
		
		assertEquals(g.getAristas().size(),1);
		
	}
	
	@Test
	public void EliminarLocalidadRedTelefonica_vecinos1Test() {
		RedTelefonica g=new RedTelefonica();
		Localidad vert=new Localidad("","",new Coordenadas(1.51,2.41));
		Localidad vert2=new Localidad("","",new Coordenadas(2.1,22.41));
		Localidad vert3=new Localidad("","",new Coordenadas(3.51,2.41));
		g.agregarVertice(vert);
		g.agregarVertice(vert2);
		g.agregarVertice(vert3);
		g.agregarArista(vert,vert2,"21.22");		
		g.agregarArista(vert,vert3,"21.22");
		g.agregarArista(vert3,vert2,"21.22");
		
		g.eliminarVertice(vert);
		
		assertEquals(vert3.getVecinos().size(),1);
		
	}
		@Test
		public void EliminarLocalidadRedTelefonica_Identificador1Test() {
			RedTelefonica g=new RedTelefonica();
			Localidad vert=new Localidad("","",new Coordenadas(1.51,2.41));
			Localidad vert2=new Localidad("","",new Coordenadas(2.1,22.41));
			Localidad vert3=new Localidad("","",new Coordenadas(3.51,2.41));
			g.agregarVertice(vert);
			g.agregarVertice(vert2);
			g.agregarVertice(vert3);
			g.agregarArista(vert,vert2,"21.22");		
			g.agregarArista(vert,vert3,"21.22");
			g.agregarArista(vert3,vert2,"21.22");
			
			g.eliminarVertice(vert2);
			
			assertEquals(1,vert3.getIdentificador());
			
		}
	

	@Test
	public void noEsConexonTest() {
		RedTelefonica g=inicializar();
		Localidad aux=new Localidad("","",new Coordenadas(6.1,6.1));
	
		g.agregarVertice(aux);
						
		assertFalse(g.BFS());
					
	}
	
	@Test
	public void esConexonTest() {
		RedTelefonica g=inicializar();
		assertTrue(g.BFS());
	
	}	

	@Test(expected=NullPointerException.class)
	public void BFSvacioTest() {
		RedTelefonica g=new RedTelefonica();		
		g.BFS();
	
	}	
	
	@Test
	public void OrdenamientoTest() {
		RedTelefonica grafo=inicializar();
		Assert.Ordenado(grafo,grafo.ListaAristasOrdenadasPorPeso());
	}
	
	@Test
	public void KruskalTest()
	{
		RedTelefonica g=inicializar();
		
		RedTelefonica arbol_minimo=new RedTelefonica();
		Localidad [] v=new Localidad[6];
		
		v[0]=new Localidad("","",new Coordenadas(0.1,0.1));
		v[1]=new Localidad("","",new Coordenadas(1.1,1.1));
		v[2]=new Localidad("","",new Coordenadas(2.1,2.1));
		v[3]=new Localidad("","",new Coordenadas(3.1,3.1));
		v[4]=new Localidad("","",new Coordenadas(4.1,4.1));
		v[5]=new Localidad("","",new Coordenadas(5.1,5.1));
	
		Arista<Localidad> aris0 = new Arista<Localidad>(v[0],v[1],"4.00");
		Arista<Localidad> aris1=new Arista<Localidad>(v[0],v[2],"8.00");
		Arista<Localidad> aris2=new Arista<Localidad>(v[1],v[3],"8.00");
		Arista<Localidad> aris3=new Arista<Localidad>(v[2],v[4],"6.00");
		Arista<Localidad> aris4=new Arista<Localidad>(v[2],v[5],"1.00");
	
		for(int i=0;i<6;i++) {
			arbol_minimo.agregarVertice(v[i]);
		}
		arbol_minimo.agregarArista(aris0);
		arbol_minimo.agregarArista(aris1);
		arbol_minimo.agregarArista(aris2);
		arbol_minimo.agregarArista(aris3);
		arbol_minimo.agregarArista(aris4);
		
		
		
		
		assertEquals(g.Kruskal(),arbol_minimo);
	}
	
	public void KruskalNoCoicidenteAristaTest()
	{
		RedTelefonica g=inicializar();
		
		RedTelefonica arbol_minimo=new RedTelefonica();
		Localidad [] v=new Localidad[6];
		
		v[0]=new Localidad("","",new Coordenadas(0.1,0.1));
		v[1]=new Localidad("","",new Coordenadas(1.1,1.1));
		v[2]=new Localidad("","",new Coordenadas(2.1,2.1));
		v[3]=new Localidad("","",new Coordenadas(3.1,3.1));
		v[4]=new Localidad("","",new Coordenadas(4.1,4.1));
		v[5]=new Localidad("","",new Coordenadas(5.1,5.1));
	
		Arista<Localidad> aris0 = new Arista<Localidad>(v[0],v[1],"4.00");
		Arista<Localidad> aris1=new Arista<Localidad>(v[0],v[2],"8.00");
		Arista<Localidad> aris2=new Arista<Localidad>(v[1],v[3],"8.00");
		Arista<Localidad> aris3=new Arista<Localidad>(v[2],v[4],"6.00");
		Arista<Localidad> aris4=new Arista<Localidad>(v[2],v[5],"1.00");
	
		for(int i=0;i<6;i++) {
			arbol_minimo.agregarVertice(v[i]);
		}
		
		arbol_minimo.agregarArista(aris0);
		arbol_minimo.agregarArista(aris1);
		arbol_minimo.agregarArista(aris2);
		arbol_minimo.agregarArista(aris3);
		arbol_minimo.agregarArista(aris4);
		
		
		
		assertNotEquals(g.Kruskal(),arbol_minimo);
	}
	
	public void KruskalNoCoicidenteLocalidadTest()
	{
		RedTelefonica g=inicializar();
		
		RedTelefonica arbol_minimo=new RedTelefonica();
		Localidad [] v=new Localidad[6];
		
		v[0]=new Localidad("","",new Coordenadas(0.1,0.1));
		v[1]=new Localidad("","",new Coordenadas(1.1,1.1));
		v[2]=new Localidad("","",new Coordenadas(2.1,2.1));
		v[3]=new Localidad("","",new Coordenadas(3.1,3.1));
		v[4]=new Localidad("","",new Coordenadas(4.1,4.1));
		v[5]=new Localidad("","",new Coordenadas(6.1,6.1));
	
		Arista<Localidad> aris0 = new Arista<Localidad>(v[0],v[1],"4.00");
		Arista<Localidad> aris1=new Arista<Localidad>(v[0],v[2],"8.00");
		Arista<Localidad> aris2=new Arista<Localidad>(v[1],v[3],"8.00");
		Arista<Localidad> aris3=new Arista<Localidad>(v[2],v[4],"6.00");
		Arista<Localidad> aris4=new Arista<Localidad>(v[2],v[5],"1.00");
		
		for(int i=0;i<6;i++) {
			arbol_minimo.agregarVertice(v[i]);
		}
		
		arbol_minimo.agregarArista(aris0);
		arbol_minimo.agregarArista(aris1);
		arbol_minimo.agregarArista(aris2);
		arbol_minimo.agregarArista(aris3);
		arbol_minimo.agregarArista(aris4);
		
		
		
		assertNotEquals(g.Kruskal(),arbol_minimo);
	}

	private RedTelefonica inicializar() {
		RedTelefonica g=new RedTelefonica();
		Localidad [] v=new Localidad[6];
		
		v[0]=new Localidad("","",new Coordenadas(0.1,0.1));
		v[1]=new Localidad("","",new Coordenadas(1.1,1.1));
		v[2]=new Localidad("","",new Coordenadas(2.1,2.1));
		v[3]=new Localidad("","",new Coordenadas(3.1,3.1));
		v[4]=new Localidad("","",new Coordenadas(4.1,4.1));
		v[5]=new Localidad("","",new Coordenadas(5.1,5.1));
		
		Arista<Localidad> aris0 = new Arista<Localidad>(v[0],v[1],"4.00");
		Arista<Localidad> aris1=new Arista<Localidad>(v[0],v[2],"8.00");
		Arista<Localidad> aris2=new Arista<Localidad>(v[1],v[2],"12.0");
		Arista<Localidad> aris3=new Arista<Localidad>(v[1],v[3],"8.00");
		Arista<Localidad> aris4=new Arista<Localidad>(v[2],v[4],"6.00");
		Arista<Localidad> aris5=new Arista<Localidad>(v[2],v[5],"1.00");
	
		for(int i=0;i<6;i++) {
			g.agregarVertice(v[i]);
		}
		g.agregarArista(aris0);
		g.agregarArista(aris1);
		g.agregarArista(aris2);
		g.agregarArista(aris3);
		g.agregarArista(aris4);
		g.agregarArista(aris5);
		
		
		return g;
	}


}
