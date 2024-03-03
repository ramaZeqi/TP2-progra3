package CodNegocio;
import java.math.BigDecimal;
import Grafo.Localidad;

//clase utilizada para calcular la distancia en Km a partir de coordenadas
public class DistanciaGeografica {
		
	public static BigDecimal DistanciaKm(Localidad p1,Localidad p2) {
	
		if(p1.equals(p2))
			return new BigDecimal("0.00");
		
		double radiotierra=6371; 
		double lat1=p1.getEtiqueta().getLat()* Math.PI/180;
		double lat2=p2.getEtiqueta().getLat()* Math.PI/180;
		
		double latDistancia = (p2.getEtiqueta().getLat()-p1.getEtiqueta().getLat())* Math.PI/180;
		double lonDistancia = (p2.getEtiqueta().getLon()-p1.getEtiqueta().getLon())* Math.PI/180;
		
		double a=Math.sin(latDistancia / 2) * Math.sin(latDistancia / 2)+ Math.cos(lat1) * Math.cos(lat2) *  Math.sin(lonDistancia / 2) * Math.sin(lonDistancia / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return new BigDecimal(Double.toString(radiotierra*c)).setScale(2, BigDecimal.ROUND_CEILING);		
		}
	
}
