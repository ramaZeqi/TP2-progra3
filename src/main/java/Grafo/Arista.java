package Grafo;

import java.math.BigDecimal;
import java.util.Comparator;



public class Arista<T> implements Comparator<Arista<T>>{
	private  T origen,destino;
	private BigDecimal peso;
	
	public Arista(T orig,T dest) {
		if(orig.equals(dest)) {
			throw new IllegalArgumentException("no se periten loops");
		}
			origen=orig;
			destino=dest;
			peso=new BigDecimal("0.00");
			peso=peso.setScale(2,BigDecimal.ROUND_FLOOR);
		}
	
	public Arista(T orig,T dest,String weight) {
		if(orig.equals(dest)) {
			throw new IllegalArgumentException("no se periten loops");
		}
			origen=orig;
			destino=dest;
			peso=new BigDecimal(weight);
			peso=peso.setScale(2,BigDecimal.ROUND_FLOOR);
		
		
	}

//-----------------Getters y Setters----------------------------------------------------------	
	public T getDestino() {
		return destino;
	}

	public T getOrigen() {
		return origen;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public T getVecinoDe(T actual) {
		
		if(actual.equals(this.getDestino()))	
			return this.getOrigen();
		else if(actual.equals(this.getOrigen()))
			return this.getDestino();
		else 
			return null;
		
	}

//-------------------------Metodos-----------------------------------------------------------------	
	public int hashCode()
	{
		return this.getOrigen().hashCode() + this.getDestino().hashCode();
	}
		    
	public boolean equals(Object obj)
    {
	   if(obj==null)
		   return false;
	   else {
		   if(obj instanceof Arista) {
			   	Arista<T> arista2 = (Arista<T>) obj;
			   	
			   	
			   	return  (this.getOrigen().equals(arista2.getOrigen())||this.getOrigen().equals(arista2.getDestino())) && (this.getDestino().equals(arista2.getOrigen())||this.getDestino().equals(arista2.getDestino()));

		   }   
	   }
	   
		return false;
    }

   @Override
   	public int compare(Arista<T> arst1, Arista<T> arst2) {
	   
	return arst1.getPeso().compareTo(arst2.getPeso());
   }

   	public String toString()
   {
	   return "({" + this.getOrigen().toString()+ ", " + this.getDestino().toString()  + "}, "+ this.peso  +")\n";
   }

	
	
}
