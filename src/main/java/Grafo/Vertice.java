package Grafo;

import java.util.HashSet;
import java.util.Set;

public class Vertice<T>{
	
	private T etiqueta;
	private HashSet<Vertice<T>> vecinos;

	public Vertice(T etiq) {	
		etiqueta=etiq;
		vecinos=new HashSet<Vertice<T>>();
	}
	
//-------------------------------------Getters-----------------------------------------------	
	public T getEtiqueta() {
		return etiqueta;
	}
	
	public Set<Vertice<T>> getVecinos() {
		return vecinos;
	}

//------------------------------------------------------------------------------------------	
//-------------------------------------Metodos-----------------------------------------------
	public void agregarVecino(Vertice<T> vec) {
		if(!contieneVecino(vec)){
			vecinos.add(vec);
		}
	}	
	
	public boolean contieneVecino(Vertice<T> vec) {
		return vecinos.contains(vec);
	}
	
	public void eliminarVecino(Vertice<T> vec) {
		vecinos.remove(vec);
	}
	
 
	
}
