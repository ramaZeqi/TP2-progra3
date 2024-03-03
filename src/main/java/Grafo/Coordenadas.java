package Grafo;

public class Coordenadas {

	double latitud;
	double longitud;
	
	public Coordenadas(double lat,double lon) {
		latitud=lat;
		longitud=lon;
	}

//----------------Gettes y setters-----------------------------------------------------	
	public double getLat() {
		return this.latitud;
	}
	
	public double getLon() {
		return this.longitud;
	}

	public void setLatitud(double latitud2) {
		this.latitud=latitud2;
		
	}

	public void setLongitud(double longitud2) {
		this.longitud=longitud2;
		
	}
	
	public void setValores(double latitud,double longitud) {
		setLatitud(latitud);
		setLongitud(longitud);
	}

//------------------------------------------------------------------------------------	
//--------------------------Metodos----------------------------------------------------
	@Override
	public int hashCode() {
		int hash1=  Double.hashCode( getLat() );
		int hash2=  Double.hashCode( getLat() );
		return hash1+hash2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj==null)
			return false;
		else {
			if (obj instanceof Coordenadas) {
				Coordenadas aux=(Coordenadas)obj;
				return aux.getLat()==this.getLat() &&  aux.getLon()==this.getLon();
			}
			return false;
		}
	}
 	
	public String toString() {
	        return "Coordenadas[" + getLat() + ", " + getLon() + "]";
	    }

	
	
	
}
