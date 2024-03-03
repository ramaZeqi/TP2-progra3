package Grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class RedTelefonica extends Grafo<Localidad>{

	public RedTelefonica()
	{
		super();
	}

	public RedTelefonica(List<Localidad> vertices, Set<Arista<Localidad>> aristas) {
		super(vertices,aristas);
	}
	
//------------------Metodos(Vertice)-----------------------------------------------------------------------	
	@Override
	public void agregarVertice(Localidad vert) {
		if(!contieneVertice(vert)) {
			vert.setIdentificador(this.getVertices().size());
			this.getVertices().add(vert);
		}
		
	}

	@Override
	public void eliminarVertice(Localidad vert) {
		this.getVertices().remove(vert.getIdentificador());
		
		for(Vertice<Coordenadas> vertice: vert.getVecinos()) {
			vertice.eliminarVecino(vert);			
			this.getAristas().remove(new Arista<Localidad>(vert,(Localidad)vertice));
		}
		int nuevaIndice=0;
		for(Localidad loc:getVertices()) {
			loc.setIdentificador(nuevaIndice);	
			nuevaIndice++;
		}
		
	}

//-------------------------------------------------------------------------------------------------------------
//------------------Metodos(Aristas)-----------------------------------------------------------------------	
	@Override
	public void agregarArista(Arista<Localidad> arista) {
		
		if(!contieneArista(arista))
		{
			this.getAristas().add(arista);
			agregarVertice(arista.getOrigen());
			agregarVertice(arista.getDestino());
	
			this.getVertices().get(arista.getOrigen().getIdentificador()).agregarVecino(arista.getDestino());
			this.getVertices().get(arista.getDestino().getIdentificador()).agregarVecino(arista.getOrigen());
			
		}
		
		
	}

	@Override
	public void agregarArista(Localidad vert1, Localidad vert2, String string) {
		Arista<Localidad> arista=new Arista<Localidad>(vert1,vert2,string);
		if(!contieneArista(arista))
		{
			this.getAristas().add(arista);
			agregarVertice(vert1);
			agregarVertice(vert2);
						
			this.getVertices().get(arista.getOrigen().getIdentificador()).agregarVecino(arista.getDestino());
			this.getVertices().get(arista.getDestino().getIdentificador()).agregarVecino(arista.getOrigen());
			
		}
		
		
	}	
	
	@Override
	public void eliminarArista(Arista<Localidad> arst) {
		this.getAristas().remove(arst);
		arst.getOrigen().eliminarVecino(arst.getDestino());
		arst.getDestino().eliminarVecino(arst.getOrigen());
		
	}

	@Override
	public List<Arista<Localidad>> ListaAristasOrdenadasPorPeso() {
		List<Arista<Localidad>> ordenamiento=new ArrayList<Arista<Localidad>>(this.getAristas());
		ordenamiento.sort(new Comparator<Arista<Localidad>>() {
		    @Override
		    public int compare(Arista<Localidad> m1,Arista<Localidad> m2) {
		        if(m1.getPeso().equals(m2.getPeso())){
		            return 0;
		        }
		        return m1.getPeso().compareTo(m2.getPeso());
		     }

		});
		return ordenamiento;

	}

//-------------------------------------------------------------------------------------------------------------	
//------------------Algorimos sobre grafos----------------------------------------------------------------------		
	
	@Override
	public boolean BFS()
	{
		if(this.getVertices().size()==0)
			throw new NullPointerException("No hay localidades agregadas");
		
		else if(this.getVertices().size()==1)
			return true;
		
		else {
		Queue<Localidad>pendientes=new LinkedList<Localidad>();
		Set<Localidad> procesados=new HashSet<Localidad>();
				
		pendientes.add(this.getVertices().get(0));
				
		
		while(!pendientes.isEmpty()){
			
			Localidad vert=pendientes.peek();
			procesados.add(vert);
			
			for(Vertice<Coordenadas> vec: vert.getVecinos()) {
				if(!procesados.contains(vec)&&!pendientes.contains(vec))
					pendientes.add((Localidad) vec);
				
			}
			pendientes.poll();
			
			
		}
	
		
		return procesados.size()==this.getVertices().size();
		}	
		
		
	}

	@Override
	public Grafo<Localidad> Kruskal(){
		if(this.BFS()) {
			List<Arista<Localidad>> pendientes=this.ListaAristasOrdenadasPorPeso();
			Set<Arista<Localidad>> aristas=new HashSet<Arista<Localidad>>(); 
			UnionFind marcados=new UnionFind(this.getVertices().size());  
			int i=0;
			while(i<this.getVertices().size()-1) {
				
				Arista<Localidad> actual=pendientes.get(0);
		
				while(marcados.estaConectado(actual.getOrigen().getIdentificador(),actual.getDestino().getIdentificador())) {
					pendientes.remove(0);
					actual=pendientes.get(0);
				}
					marcados.union(actual.getOrigen().getIdentificador(),actual.getDestino().getIdentificador());
					pendientes.remove(0);
					aristas.add(actual);	
					i++;
			
				}
				return new RedTelefonica(this.getVertices(),aristas);
			}	
		return null;
	}
//-------------------------------------------------------------------------------------------------------------	
//------------------Otros--------------------------------------------------------------------------------------			
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		else {
			if(obj instanceof Grafo) {
				Grafo<Localidad> aux=(Grafo<Localidad>)obj;
				return aux.getVertices().equals(this.getVertices())&& aux.ListaAristasOrdenadasPorPeso().equals(this.ListaAristasOrdenadasPorPeso());
			}
		}
		return false;
					
	}





	
	
	


	
	
	
	
}
