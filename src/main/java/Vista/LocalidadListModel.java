package Vista;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import Grafo.Localidad;

public class LocalidadListModel extends AbstractListModel<Localidad> {
	
		private static final long serialVersionUID = 1L;
		private ArrayList<Localidad> localidades = new ArrayList<Localidad>();

	    @Override
	    public int getSize() {
	        return localidades.size();
	    }

	    @Override
	    public Localidad getElementAt(int index) {
	    	Localidad local = localidades.get(index);
	        return local;
	    }
	    public void addLocalidad(Localidad p){
	    	if(!localidades.contains(p))
	    	{
	    		localidades.add(p);
	    		this.fireIntervalAdded(this, getSize(), getSize()+1);
	    	}
	    }
	    public void eliminarLocalidad(int index0){
	    	localidades.remove(index0);
	        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
	    }
	 
}
