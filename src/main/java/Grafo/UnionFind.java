package Grafo;

public class UnionFind {
	
	private int [] padre;
	private int [] capacidad;
	
	public UnionFind(int vertices) {
		padre=new int[vertices];
		capacidad=new int[vertices];
		for(int i=0;i<vertices;++i) {
			padre[i]=i;
			capacidad[i]=1 ;
			
		}
	}

//---------------Getters---------------------------------------------------------------
	public int[] getPadre() {
		return padre;
	}
	
	public int[] getCapacidad() {
		return capacidad;
	}
//-------------------------------------------------------------------------------------
//---------------------Metodos-----------------------------------------------------	
		
	public int encontrarRaiz(int index) {
		 if (index < 0 || index >= padre.length) {
	            throw new IndexOutOfBoundsException("fuera del rango abarcable");
	        }		
	        
	     int comienzo=index;
	     
	     while(padre[comienzo]!=comienzo)
	    	 {
	    	 	comienzo=padre[comienzo];
	    	 }
	     int next;
	     while(index!=comienzo)
	     {
	    	 next=padre[index];
	    	 padre[index]=comienzo;
	    	 index=next;
	     }
	     return comienzo;
	}

 	public boolean estaConectado(int index1,int index2) {
		return encontrarRaiz(index1)==encontrarRaiz(index2);
	}
	
	public void union(int index1,int index2) {
		int raiz1 = encontrarRaiz(index1);
        int raiz2 = encontrarRaiz(index2);
		if(raiz1!=raiz2)
		{
			    
		        if (capacidad[raiz1] < capacidad[raiz2]) {
		        	padre[raiz1] =raiz2;
		        	capacidad[raiz2] += capacidad[raiz1]; 
		        }
		        	
		        else             
		        {
		        	padre[raiz2] = raiz1; 
		        	capacidad[raiz1] += capacidad[raiz2];
		        }
		}
			
	    }
	
	public String toString()
	{
		StringBuilder aux=new StringBuilder();
		for(int i=0;i<padre.length;++i)
			aux.append(padre[i]+"\t");
		aux.append("\n");
		for(int i=0;i<padre.length;++i)
			aux.append(capacidad[i]+"\t");
		
		return aux.toString();
	}

	
	
	
	

}
