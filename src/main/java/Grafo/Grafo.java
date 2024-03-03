package Grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Grafo<T> {
	
	private ArrayList<T> vertices;
	private HashSet<Arista<T>> aristas;
			
	public Grafo() {
		aristas=new HashSet<Arista<T>> ();
		vertices=new ArrayList<T>();
	}	
	public Grafo(List<T> vertices, Set<Arista<T>> aristas) {
		this.vertices=(ArrayList<T>) vertices;
		this.aristas=(HashSet<Arista<T>>) aristas;
	}
	
//-------------Getters y Setters-------------------------------------------------------------
	public List<T> getVertices() {
		return vertices;
	}

	public Set<Arista<T>>getAristas() {
		return aristas;
	}
//--------------------------------------------------------------------------------------------	
//-------------Metodos-----------------------------------------------------------------------
	public boolean contieneVertice(T vert) {
		return this.getVertices().contains(vert);
	}
	
	public boolean contieneArista(Arista<T> arst) {
		return aristas.contains(arst);
	}
//--------------------------------------------------------------------------------------------	
//------------Metodos abstractos--------------------------------------------------------------
	public abstract void agregarVertice(T vert);
	public abstract void agregarArista(Arista<T> arista);	
	public abstract void agregarArista(T vert1, T vert2, String string);
	public abstract void eliminarArista(Arista<T> arst);
	public abstract void eliminarVertice(T vert);
	public abstract List<Arista<T>> ListaAristasOrdenadasPorPeso();
	public abstract boolean BFS();
	public abstract Grafo<T> Kruskal();
}