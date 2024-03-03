package Vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import Controlador.Controlador;



public class GuiApp {

	private JFrame frame;
	private JPanel pnlMap,pnlIngreso,pnlProceso;
	private JButton btnCostoMInimo,btnDibujarConexiones,btnEliminarConexiones,btnLocalidad;
	private JLabel lblCostoFijo,lblPorcentajeDeAumento,lblValorPorKm;
	private JTextField txtAum_Prt,txtVal_km,txtCosto_Fij;
	private Controlador controlador;
	private GUI_Localidades vntModalIngreso;
	private JMapViewer mapa;
	private ArrayList< MapPolygonImpl>  Caminos;
	private MapPolygonImpl camino;
	private ArrayList< MapMarkerDot> marcas;

	public GuiApp() {
		try
		{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) { 
			JOptionPane.showMessageDialog(null,e.getMessage());
		} 
	} 
	 
//---------------------------------------Getters y Setters---------------------------------------------------------	
	
	public ArrayList<MapMarkerDot> getMarcas() {
		return marcas;
	}

	public void setMarcas(ArrayList<MapMarkerDot> marcas) {
		this.marcas = marcas;
	}

	public MapPolygonImpl getCamino() {
		return camino;
	}

	public void setCamino(MapPolygonImpl camino) {
		this.camino = camino;
	}

	public JButton getBtnEliminarConexiones() {
		return btnEliminarConexiones;
	}

	public JTextField getTxtAum_Prt() {
		return txtAum_Prt;
	}

	public JTextField getTxtVal_km() {
		return txtVal_km;
	}

	public JTextField getTxtCosto_Fij() {
		return txtCosto_Fij;
	}

	public JMapViewer getMapa() {
		return mapa;
	}

	public ArrayList<MapPolygonImpl> getCaminos() {
		return Caminos;
	}

	public JButton getBtnCostoMInimo() {
		return btnCostoMInimo;
	}

	public JButton getBtnDibujarConexiones() {
		return btnDibujarConexiones;
	}

	public JButton getBtnLocalidad() {
		return btnLocalidad;
	}

	public GUI_Localidades getVntModalIngreso() {
		return vntModalIngreso;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------- Inicializacion ventana principal-------------------------------------------------------------------------------------------------)	
	private void GUI() {
		frame = new JFrame();
		frame.setBounds(200, 200, 750, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));		
	
		
		AgregarPanels();
		AgregarComponentes();
		
		this.getBtnLocalidad().addActionListener(controlador);
		this.getBtnCostoMInimo().addActionListener(controlador);
		this.getBtnDibujarConexiones().addActionListener(controlador);
		this.getBtnEliminarConexiones().addActionListener(controlador);
		
	
			
		frame.setVisible(true);
		
	}

	public void creaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI();
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

	public void MostrarVentanaEmergente(String info) {
		JOptionPane.showMessageDialog(this.getFrame(), info);
	
		
	}
	
//------------------------------- Auxiliares-------------------------------------------------------------------------------------------------)
	
	private void AgregarPanels() {
		pnlMap = new JPanel();
		frame.getContentPane().add(pnlMap, BorderLayout.CENTER);
				
		pnlIngreso = new JPanel();
		frame.getContentPane().add(pnlIngreso, BorderLayout.EAST);
		
		pnlProceso = new JPanel();
		frame.getContentPane().add(pnlProceso, BorderLayout.SOUTH);
		pnlProceso.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
	}

	private void AgregarComponentes() {
		
		btnCostoMInimo = new JButton("Calcular costo minimo");
		pnlProceso.add(btnCostoMInimo);
		
		btnDibujarConexiones = new JButton("Dibujar Conexiones");
		pnlProceso.add(btnDibujarConexiones);
		
		btnEliminarConexiones = new JButton("Eliminar Conexiones");
		pnlProceso.add(btnEliminarConexiones);
				
		mapa = new JMapViewer();
		mapa.setDisplayPosition(new Coordinate(0, 0),1);
		pnlMap.add(mapa);
		
		
		
		lblValorPorKm = new JLabel("Valor por Km");
		lblValorPorKm.setHorizontalAlignment(SwingConstants.LEFT);
			
		lblPorcentajeDeAumento = new JLabel("Porcentaje de aumento");
		lblPorcentajeDeAumento.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblCostoFijo = new JLabel("Costo FIjo");
		lblCostoFijo.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnLocalidad = new JButton("Actualizar Localidades");
		
		txtAum_Prt = new JTextField();
		
		txtCosto_Fij = new JTextField();
		
		txtVal_km = new JTextField();
		GroupLayout gl_pnlIngreso = new GroupLayout(pnlIngreso);
		gl_pnlIngreso.setHorizontalGroup(
			gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlIngreso.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlIngreso.createSequentialGroup()
							.addComponent(lblPorcentajeDeAumento, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_pnlIngreso.createSequentialGroup()
							.addComponent(txtCosto_Fij, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlIngreso.createSequentialGroup()
								.addComponent(txtVal_km, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlIngreso.createSequentialGroup()
									.addComponent(txtAum_Prt, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_pnlIngreso.createSequentialGroup()
									.addGroup(gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCostoFijo, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
										.addComponent(lblValorPorKm, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
									.addContainerGap())))))
				.addGroup(gl_pnlIngreso.createSequentialGroup()
					.addGap(28)
					.addComponent(btnLocalidad)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_pnlIngreso.setVerticalGroup(
			gl_pnlIngreso.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlIngreso.createSequentialGroup()
					.addComponent(lblValorPorKm, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtVal_km, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPorcentajeDeAumento, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtAum_Prt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblCostoFijo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCosto_Fij, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(btnLocalidad)
					.addContainerGap())
		);
		pnlIngreso.setLayout(gl_pnlIngreso);
		
		vntModalIngreso =new GUI_Localidades(this.getControlador());
				
		marcas=new ArrayList<MapMarkerDot>();
		Caminos = new ArrayList<MapPolygonImpl>();
	}




}
