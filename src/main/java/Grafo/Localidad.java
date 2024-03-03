package Grafo;



public class Localidad extends Vertice<Coordenadas>{
		
	private int identificador;
	private String nombre,provincia;
	
	public Localidad(String nom,String prov,Coordenadas coord) {
		super(coord);
		this.nombre=nom;
		this.provincia=prov;
		identificador=0;
	}
	
	public Localidad(int numIdent,String nom,String prov,Coordenadas coord) {
		super(coord);
		this.nombre=nom;
		this.provincia=prov;
		identificador=numIdent;
		
	}	
 
//----------------Getters y Setters----------------------------------------------------------------	
	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}
	
	public int getIdentificador() {
		return this.identificador;
	}
	
	public void setIdentificador(int index) {
		this.identificador=index;
	}
//------------------------------------------------------------------------------------------------
//----------------Metodos-------------------------------------------------------------------------
	public int hashCode()
	{
		return this.getEtiqueta().toString().hashCode();
	}
	
	public boolean equals(Object vertice2)
    {
    	if(vertice2==null)
    		return false;
    	else{
    		if(vertice2 instanceof Vertice){
    			Localidad v = (Localidad) vertice2;
    			return this.getEtiqueta().equals(v.getEtiqueta());
    		}
    	}
	    return false;	
    }
		
    public String toString()
    {
    	return this.getNombre()+"/"+this.getProvincia()+"/"+ this.getEtiqueta().toString();
    
    }



	



}
