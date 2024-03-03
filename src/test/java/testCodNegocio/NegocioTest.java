package testCodNegocio;

import static org.junit.Assert.*;
import org.junit.Test;

import CodNegocio.DistanciaGeografica;
import CodNegocio.Negocio;
import Grafo.Coordenadas;
import Grafo.Localidad;

public class NegocioTest {

	
	@Test(expected=IllegalArgumentException.class)
	public void ParametrosNegativosTest() {
		Negocio negocio=new Negocio();
		negocio.setEstablecerValores("0","-10","100");
		
	}
	
	@Test(expected=NumberFormatException.class)
	public void ParametrosNoNumericosTest() {
		Negocio negocio=new Negocio();
		negocio.setEstablecerValores("0","a","100");
		
	}
	
	@Test
	public void CalcularKilometrosTest() {
		Localidad aux=new Localidad("1","buenos aires",new Coordenadas(-36.359746,-60.832528));
		Localidad aux1=new Localidad("2","salta",new Coordenadas(-24.333886,-65.213875));
		
		assertEquals(1401,DistanciaGeografica.DistanciaKm(aux1,aux).intValue());
	}
	@Test
	public void KilometrosCeroTest() {
		Localidad aux=new Localidad("","",new Coordenadas(-24.333886,-65.213875));
		Localidad aux1=new Localidad("","",new Coordenadas(-24.333886,-65.213875));
		
		assertEquals(0,DistanciaGeografica.DistanciaKm(aux1,aux).intValue());
	}
	
	@Test
	public void ValorFijoConexionNegocioTest() {
		
		Negocio negocio=new Negocio();
		negocio.setEstablecerValores("0","10","100");
		Localidad aux1=new Localidad("2","salta",new Coordenadas(-28.333886,-64.213875));
		Localidad aux2=new Localidad("1","buenos aires",new Coordenadas(-38.359746,-61.832528));
		
		assertEquals(110,negocio.calculoConexion(aux1,aux2).intValue());
 
		
	}
	@Test
	public void ValorConexionNegocioPorcentajeTest() {
		
		Negocio negocio=new Negocio();
		negocio.setEstablecerValores("1","0","99");
		Localidad aux1=new Localidad("1","buenos aires",new Coordenadas(-36.359746,-60.832528));
		Localidad aux2=new Localidad("2","salta",new Coordenadas(-24.333886,-65.213875));
		assertEquals(1500,negocio.calculoConexion(aux1,aux2).intValue());
		
	}

	
	
	@Test
	public void NegocioTest() {
		Negocio negocio=new Negocio();
		negocio.setEstablecerValores("00","10","100");
		negocio.agregarLocalidad("asd","salta","-28.333886","-64.213875");
		negocio.agregarLocalidad("dsa","buenos aires","-38.359746","-61.832528");
		negocio.establecer_conexiones();
		
		assertTrue(110.00==Double.parseDouble(negocio.costoMInimoTotal()));
	 
		
	}
	
	
	
	

}
