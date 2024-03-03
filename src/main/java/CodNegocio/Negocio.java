package CodNegocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import Grafo.Arista;
import Grafo.Coordenadas;
import Grafo.Grafo;
import Grafo.Localidad;
import Grafo.RedTelefonica;


public class Negocio{
	private RedTelefonica grafo;
	private BigDecimal valor_km;
	private BigDecimal porcentaje_aumento;
	private BigDecimal coste_fijo;
	
	public Negocio() {
		this.grafo=new RedTelefonica();
		this.valor_km=new BigDecimal("0.00");
		this.porcentaje_aumento=new BigDecimal("0.00");
		this.coste_fijo=new BigDecimal("0.00");
		
	} 	

  
	
	public RedTelefonica getGrafo() {
		return grafo;
	}

//-------------------------------------SETTERS Y GETTERS----------------------------------------------------

	public BigDecimal getValor_km() {
		return valor_km;
	}

	public BigDecimal getPorcentaje_aumento() {
		return porcentaje_aumento;
	}

	public BigDecimal getCoste_fijo() {
		return coste_fijo;
	}
	
//----------------------------------------------------------------------------------------------------------
//-------------------------------------Ingreso localidad (junto a codigo de seguiridad)-----------------------

	public void agregarLocalidad(String loc,String prov,String lat,String lon) {
		vertificarEntradaLocalidadNoVacio(loc,prov,lat,lon);
		verificarPalabra(loc);
		verificarPalabra(prov);
		verificarIngresoNumerico_Lat_Long(lat, lon);
		double latitud=Double.parseDouble(lat);
		double longitud=Double.parseDouble(lon);
		vertificarLatLongRango(latitud,longitud);	
		Localidad localdad_Ingresada=new Localidad(loc.toLowerCase(),prov.toLowerCase(),new Coordenadas(latitud,longitud));
		verificarCoordenadasIguales(localdad_Ingresada);
		this.getGrafo().agregarVertice(localdad_Ingresada);
   }

	private void verificarCoordenadasIguales(Localidad localdad_Ingresada) {
	if(this.getGrafo().contieneVertice(localdad_Ingresada))
		throw new IllegalArgumentException("coordenadas ya ingresadas");
}

	private void verificarPalabra(String nomb) {
		nomb.toLowerCase();
		char c;
		for(int caracter=0;caracter<nomb.length();++caracter)
		{
			if((nomb.charAt(caracter)<'a' || nomb.charAt(caracter)>'z') && nomb.charAt(caracter)!=' ')
					throw new IllegalArgumentException("No se admiten numeros ni simbolos en las casillas de Localidad y Provincia");
		}
	
	}

	private void vertificarEntradaLocalidadNoVacio(String nomb, String prov, String lat, String lon) {
		if(nomb.isEmpty()||prov.isEmpty()||lat.isEmpty()||lon.isEmpty())
			throw new NumberFormatException("Complete el ingreso de valores");
		
	}

	private void verificarIngresoNumerico_Lat_Long(String lat,String lon) {
		try {
			Double.parseDouble(lat);
			Double.parseDouble(lon);
			
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException ("Latitud y Longitud solo aceptan valores numericos");
		}
		
		
	}
	
	private void vertificarLatLongRango(double lat, double lon) {
	if(lat>90 ||lat<-90 ||lon>180||lon<-180)
		throw new IllegalArgumentException("Fuera de rango de coordenada.\nRangos:\n                  Latitud[-90,90]\n                  Longitud[-180,180]") ;
}


//----------------------------------------------------------------------------------------------------------	
//----------------------------------Ingreso de costos (junto a code de seguiridad)--------------------------	

	public void setEstablecerValores(String v_km,String p_aum,String c_fij) {
		
		vertificacionEntradaCostosNoVacia(v_km, p_aum, c_fij);
		VerificacionValoresPositivos(v_km, p_aum, c_fij);
		
		
		Double porcentaje=Double.parseDouble(p_aum)/100;
		this.valor_km=new BigDecimal(v_km);
		this.porcentaje_aumento=new BigDecimal(porcentaje).abs().setScale(2, BigDecimal.ROUND_FLOOR);
		this.coste_fijo=new BigDecimal(c_fij);
		
	}

	private void vertificacionEntradaCostosNoVacia(String v_km, String p_aum, String c_fij) {
		if(v_km.isEmpty()||p_aum.isEmpty()||c_fij.isEmpty())
			throw new NumberFormatException("Complete el ingreso de los valores");
	}

	private void VerificacionValoresPositivos(String v_km, String p_aum, String c_fij) {
		try {
		if(Double.parseDouble(v_km)<0||Double.parseDouble(p_aum)<0||Double.parseDouble(c_fij)<0)
			throw new IllegalArgumentException("No se aceptan valores negativos");
		}
		catch(NumberFormatException e) {
			
			throw new NumberFormatException("Ingrese valores numericos");
		}
	}
	
//----------------------------------------------------------------------------------------------------------	
//----------------------------------Otros Metodos Utiles----------------------------------------------------		
	
	public void quitarLocalidad(Localidad loc) {
		this.getGrafo().eliminarVertice(loc);
	}
	
	public void establecer_conexiones() {
		//limpio aristas por si se elimina alguna localidad y tengo que generar una grafo nuevo
		this.getGrafo().getAristas().clear();
		if(this.getGrafo().getVertices().size()>1) {
			BigDecimal costo;			
			for(Localidad vert:this.getGrafo().getVertices()) {
				for(Localidad vert2:this.getGrafo().getVertices()) {
					if(!vert.equals(vert2)) {
						costo=calculoConexion(vert,vert2);
						this.getGrafo().agregarArista(new Arista<Localidad>(vert,vert2,costo.toString()));}
				}
			}
		}
	}

	public BigDecimal calculoConexion(Localidad vert, Localidad vert2) {

		BigDecimal costo=DistanciaGeografica.DistanciaKm(vert, vert2);
		BigDecimal porcentaje=new BigDecimal("0");
		
		if(costo.doubleValue()>300) {
			if(!vert.getProvincia().equals(vert2.getProvincia())) {
				
				costo=costo.multiply(this.getValor_km());	
				
				costo=costo.add(this.getCoste_fijo());
				
				porcentaje=costo.multiply(this.getPorcentaje_aumento());
				
				costo=costo.add(porcentaje);
				
			}
			else {
				
				costo=costo.multiply(valor_km);	
				
				costo=costo.add(coste_fijo);
			}
		}
		else {
			if(!vert.getProvincia().equals(vert2.getProvincia())) {
				
				costo=costo.multiply(valor_km);					
				
				costo=costo.add(coste_fijo);
			}
			else {
					costo=costo.multiply(valor_km);	
			}
		
			
		}
		return costo;
	}

	public List<Arista<Localidad>>camino_minimo(){
		return this.getGrafo().Kruskal().ListaAristasOrdenadasPorPeso();
	}

	public String costoMInimoTotal() {
		//excepcion que se encarga de manejar el caso de que un grafo no tenga vertices 
		try {
			BigDecimal suma=new BigDecimal("0.00");
			for(Arista<Localidad> arista:this.camino_minimo()) {
				suma=suma.add(arista.getPeso());
			}	
			return suma.toString();
		}
		catch(NullPointerException e) {
			return "Ingrese Localidades";
		}
	
		
	}

}	