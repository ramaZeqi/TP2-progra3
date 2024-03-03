package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import CodNegocio.Negocio;
import Grafo.Localidad;
import Vista.GuiApp;

public class Controlador implements ActionListener,MouseListener,FocusListener{

	private GuiApp vista;
	private Negocio modelo;
	private boolean HabilitarDibujar;	
	
	public Controlador(GuiApp view, Negocio logica) {
		this.vista=view;
		this.modelo=logica;
		this.HabilitarDibujar=true;
	}

	public Controlador() {
		// TODO Auto-generated constructor stub
	}
//---------------------------------------------Getters y setters---------------------------------------------

	public GuiApp getVista() {
		return vista;
	}

	public void setVista(GuiApp vista) {
		this.vista = vista;
	}
	
	public Negocio getModelo() {
		return modelo;
	}

	public void setModelo(Negocio model) {
		this.modelo = model;
	}

	public boolean isHabilitarDibujar() {
		return HabilitarDibujar;
	}

	public void setHabilitarDibujar(boolean habilitarDibujar) {
		HabilitarDibujar = habilitarDibujar;
	}
//----------------------------------------------------------------------------------------------------------------	
//---------------------------------------------Listeners---------------------------------------------------------	
	
	@Override
	public void actionPerformed(ActionEvent listener) {
		//muestra en un JOptionPane, ligado al frame principal, el costo de la minima conexion.
		if(listener.getSource()==getVista().getBtnCostoMInimo()) {
			getVista().MostrarVentanaEmergente(pesoCostoMinimo());
		}
		else if(listener.getSource()==getVista().getBtnDibujarConexiones()) {
			dibujarConexiones();
		}
		else if(listener.getSource()==getVista().getBtnEliminarConexiones()) {
			eliminarConexiones();
		}
		//ingreso a la ventana modal
		else if(listener.getSource()==getVista().getBtnLocalidad()) {
			eliminarConexiones();
			getVista().getVntModalIngreso().setVisible(true);
		}
		//ingreso de localidad
		else if(listener.getSource()==getVista().getVntModalIngreso().getBtnIngreso()) {
			agregarLocalidad();
		}
		//elimina localidad ingresada
		else if(listener.getSource()==getVista().getVntModalIngreso().getBtnEliminar()) {
			eliminarLocalidad();
		}
		else if(listener.getSource()==getVista().getVntModalIngreso().getBtnSalirLocalidades()) {
			salirVntModal();
		}
	
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {	
		getVista().getVntModalIngreso().borrarLbl();
		getVista().getVntModalIngreso().getProvincias().clearSelection();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Localidad item = (Localidad) this.getVista().getVntModalIngreso().getProvincias().getSelectedValue();
		this.getVista().getVntModalIngreso().getLblLocalidad().setText(item.getNombre());
		this.getVista().getVntModalIngreso().getLblProvincia().setText(item.getProvincia());
		this.getVista().getVntModalIngreso().getLblLatitud().setText(Double.toString(item.getEtiqueta().getLat()));
		this.getVista().getVntModalIngreso().getLblLongitud().setText(Double.toString(item.getEtiqueta().getLon()));
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
//----------------------------------------------------------------------------------------------------------------
//---------------------------------------------Auxiliares---------------------------------------------------------



	private String pesoCostoMinimo() {
		 try {
			this.getModelo().setEstablecerValores(getVista().getTxtVal_km().getText(),getVista().getTxtAum_Prt().getText() ,getVista().getTxtCosto_Fij().getText());
			this.getModelo().establecer_conexiones();
			return getModelo().costoMInimoTotal();
		 }
		 catch(NumberFormatException e) {
			 return e.getMessage();
		 }
		 catch(IllegalArgumentException e) {
			 return e.getMessage();
		 }
	 
	}

	private void dibujarConexiones() {
		if(isHabilitarDibujar()) {
			try {
			
				if(this.getModelo().getGrafo().getVertices().size()==1) {
				
					Localidad aux=getVista().getVntModalIngreso().getModelList().getElementAt(0);
					getVista().getMarcas().add((new MapMarkerDot(aux.getNombre(),new Coordinate(aux.getEtiqueta().getLat(),aux.getEtiqueta().getLon()))));
					getVista().getMapa().addMapMarker(getVista().getMarcas().get(0));
					this.setHabilitarDibujar(false);
				}
				else {
					
					getVista().getMarcas().clear();
					
					for(int indice=0;indice<getVista().getVntModalIngreso().getModelList().getSize();indice++){
						
						Localidad aux=getVista().getVntModalIngreso().getModelList().getElementAt(indice);
						getVista().getMarcas().add((new MapMarkerDot(aux.getNombre(),new Coordinate(aux.getEtiqueta().getLat(),aux.getEtiqueta().getLon()))));
					}	
				
					this.getModelo().setEstablecerValores(this.getVista().getTxtVal_km().getText(),getVista().getTxtAum_Prt().getText() ,getVista().getTxtCosto_Fij().getText());
					this.getModelo().establecer_conexiones();
					
					for(int i=0;i<getModelo().getGrafo().getVertices().size();i++){
						
						getVista().getMapa().addMapMarker(getVista().getMarcas().get(i));
					}
					
					getVista().getCaminos().clear();
					
					for (int i=0; i<getModelo().camino_minimo().size(); i++){
						
						Coordinate origin=new Coordinate(getModelo().camino_minimo().get(i).getOrigen().getEtiqueta().getLat(),getModelo().camino_minimo().get(i).getOrigen().getEtiqueta().getLon());
						Coordinate dest=new Coordinate(getModelo().camino_minimo().get(i).getDestino().getEtiqueta().getLat() , getModelo().camino_minimo().get(i).getDestino().getEtiqueta().getLon());
						getVista().setCamino(new MapPolygonImpl(origin,dest,origin));
						getVista().getCaminos().add(getVista().getCamino());
						getVista().getMapa().addMapPolygon(getVista().getCamino());
					}
					
					this.setHabilitarDibujar(false);
				}
			}
			catch(NullPointerException e) {
				this.getVista().getVntModalIngreso().VentanaEmergente(e.getMessage());
			}
			catch(IllegalArgumentException e) {
				this.getVista().getVntModalIngreso().VentanaEmergente(e.getMessage());
			}
		}
	}

	private void eliminarConexiones() {
		if(!isHabilitarDibujar()) {
			
			for (MapMarkerDot marca: getVista().getMarcas())
				getVista().getMapa().removeMapMarker(marca);
			
			for (MapPolygonImpl camino: getVista().getCaminos())
				getVista().getMapa().removeMapPolygon(camino);
			
			this.getVista().getCaminos().clear();
			this.setHabilitarDibujar(true);
			
		}
	}
		
	private void agregarLocalidad() {
		try {
			
			String loc = getVista().getVntModalIngreso().getTxtLocalidad().getText().toString().toLowerCase();
			String prov =getVista().getVntModalIngreso().getTxtProvincia().getText().toString().toLowerCase();
	 		String lat = getVista().getVntModalIngreso().getTxtLatitud().getText().toString();
			String lon = getVista().getVntModalIngreso().getTxtLongitud().getText().toString();
			getModelo().agregarLocalidad(loc,prov,lat,lon);
			
			
			getVista().getVntModalIngreso().agregarLocalidadALista(getModelo().getGrafo().getVertices().get(getModelo().getGrafo().getVertices().size()-1));
			getVista().getVntModalIngreso().borrarTxt();
			
						 
		}
		catch(NumberFormatException e) {
			this.getVista().getVntModalIngreso().VentanaEmergente(e.getMessage());
		}		
		catch(IllegalArgumentException e) {
			this.getVista().getVntModalIngreso().VentanaEmergente(e.getMessage());
		}
	
	 
		
	}

	private void eliminarLocalidad() {
		try {
		int index=getVista().getVntModalIngreso().getProvincias().getSelectedIndex();
		Localidad borrar=getVista().getVntModalIngreso().getModelList().getElementAt(index);
		getModelo().quitarLocalidad(borrar);
		getVista().getVntModalIngreso().getModelList().eliminarLocalidad(index);
		getVista().getVntModalIngreso().borrarLbl();
		}
		catch(IndexOutOfBoundsException e) {
			getVista().getVntModalIngreso().VentanaEmergente("Seleccione un elemento de la lista");
		}
	
 		
	}
	
	private void salirVntModal() {
		
		this.getVista().getVntModalIngreso().borrarLbl();
		getVista().getVntModalIngreso().dispose();
	}

	
	

















}
