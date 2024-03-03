package Vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Controlador.Controlador;
import Grafo.Localidad;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GUI_Localidades extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnIngreso,btnEliminar,btnSalirLocalidades;
	private JList<Localidad> provincias;
	private JTextField txtLocalidad,txtProvincia,txtLatitud,txtLongitud;
	private JLabel lblLocalidad,lblProvincia,lblLatitud,lblLongitud;
	private LocalidadListModel Listmodel;
	private Controlador controlador;
	private JPanel panelTxt;
	
	/**
	 * Create the dialog.
	 */
	public GUI_Localidades(Controlador controlador) {
		
		setBounds(100, 100, 600, 330);
		getContentPane().setLayout(null);
		this.setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
			
		AgregarComponentes();
		
		this.getBtnIngreso().addActionListener(controlador);
		this.getBtnEliminar().addActionListener(controlador);
		this.getBtnSalirLocalidades().addActionListener(controlador);
		this.getProvincias().addFocusListener(controlador);
		this.getProvincias().addMouseListener(controlador);
		

	}
	
//-------------------------------Getters y Setters----------------------------------------------------------------------	
	public JButton getBtnIngreso() {
		return btnIngreso;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JList<Localidad> getProvincias() {
		return provincias;
	}

	
	
	public LocalidadListModel getModelList() {
		return Listmodel;
	}

	public JButton getBtnSalirLocalidades() {
		return btnSalirLocalidades;
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}

	public JTextField getTxtProvincia() {
		return txtProvincia;
	}

	public JTextField getTxtLatitud() {
		return txtLatitud;
	}

	public JTextField getTxtLongitud() {
		return txtLongitud;
	}
		
	public JLabel getLblLocalidad() {
		return lblLocalidad;
	}

	public JLabel getLblProvincia() {
		return lblProvincia;
	}

	public JLabel getLblLatitud() {
		return lblLatitud;
	}

	public JLabel getLblLongitud() {
		return lblLongitud;
	}
	
	public Controlador getControlador() {
		return controlador;
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

//------------------------------------------------------------------------------------------------------------------------	
//----------------------------------------Metodos Interactivos-----------------------------------------------------------	
	public void agregarLocalidadALista(Localidad loc)
	{
		Listmodel.addLocalidad(loc);
		provincias.setModel(Listmodel);
	}
		
	public void borrarTxt() {
		
		this.getTxtLocalidad().setText("");
		this.getTxtProvincia().setText("");
		this.getTxtLatitud().setText("");
		this.getTxtLongitud().setText("");
				
	}

	public void borrarLbl()
	{
		this.getLblLocalidad().setText("...");
		this.getLblProvincia().setText("...");
		this.getLblLatitud().setText("...");
		this.getLblLongitud().setText("...");
	}

	public void VentanaEmergente(String info) {
		JOptionPane.showMessageDialog(this, info);
	}

//------------------------------------------------------------------------------------------------------------------------	
//-------------------- Auxiliar--------------------------------------------------------------------------------------------

	private void AgregarComponentes() {
				
		
		JLabel lblIngresarLocalidad = new JLabel("Ingresar Localidad:");
		lblIngresarLocalidad.setBounds(20, 15, 153, 15);
		
		JLabel lblLocalidades = new JLabel("Localidades");
		lblLocalidades.setBounds(468, 12, 93, 15);
		
		JLabel lblLocal_1= new JLabel("Localidad");
		lblLocal_1.setBounds(20, 45, 70, 15);
		
		JLabel lblProv_1 = new JLabel("Provincia");
		lblProv_1.setBounds(20, 75, 70, 15);
		
		JLabel lblLat_1 = new JLabel("Latitud");
		lblLat_1.setBounds(20, 105, 70, 15);
		
		JLabel lblLon_1 = new JLabel("Longitud");
		lblLon_1.setBounds(20, 135, 78, 15);
			
		JLabel lblInformacion = new JLabel("Informacion:");
		lblInformacion.setBounds(20, 165, 93, 15);
	
		JLabel lblLocal_2= new JLabel("Localidad");
		lblLocal_2.setBounds(20, 190, 70, 15);
		
		JLabel lblProvicia_1 = new JLabel("Provincia:");
		lblProvicia_1.setBounds(20, 215, 70, 15);
		
		JLabel lblLat_2 = new JLabel("Latitud:");
		lblLat_2.setBounds(20, 240, 70, 15);
		
		JLabel lblLon_2 = new JLabel("Longitud:");
		lblLon_2.setBounds(20, 265, 70, 15);
				
		provincias = new JList<Localidad>();
		provincias.setBounds(449, 39, 129, 249);
			
		Listmodel=new LocalidadListModel();		
				
		lblLocalidad = new JLabel("...");
		lblLocalidad.setBounds(158, 190, 70, 15);
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblProvincia = new JLabel("...");
		lblProvincia.setBounds(158, 215, 70, 15);
		lblProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblLatitud = new JLabel("...");
		lblLatitud.setBounds(158, 240, 70, 15);
		lblLatitud.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblLongitud = new JLabel("...");
		lblLongitud.setBounds(158, 265, 70, 15);
		lblLongitud.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnIngreso = new JButton("Añadir");
		btnIngreso.setBounds(278, 35, 117, 25);
		btnIngreso.setFocusable(false);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(278, 73, 117, 25);
		btnEliminar.setFocusable(false);
				
		btnSalirLocalidades = new JButton("Salir");
		btnSalirLocalidades.setBounds(278, 255, 117, 25);
		btnSalirLocalidades.setFocusable(false);
		getContentPane().setLayout(null);
		getContentPane().add(lblIngresarLocalidad);
		getContentPane().add(lblLocalidades);
		getContentPane().add(lblLocal_1);
		getContentPane().add(lblProv_1);
		getContentPane().add(lblLat_1);
		getContentPane().add(lblLon_1);
		getContentPane().add(lblInformacion);
		getContentPane().add(lblLocal_2);
		getContentPane().add(lblProvicia_1);
		getContentPane().add(lblLat_2);
		getContentPane().add(lblLon_2);
		
		panelTxt = new JPanel();
	
		panelTxt.setBounds(114, 43, 114, 110);
		getContentPane().add(panelTxt);
		panelTxt.setLayout(null);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(0, 0, 114, 20);
		panelTxt.add(txtLocalidad);
			
		txtProvincia = new JTextField();
		txtProvincia.setBounds(0, 30, 114, 20);
		panelTxt.add(txtProvincia);
		
		txtLatitud = new JTextField();
		txtLatitud.setBounds(0, 60, 114, 20);
		panelTxt.add(txtLatitud);
		
		txtLongitud = new JTextField();
		txtLongitud.setBounds(0, 90, 114, 20);
		panelTxt.add(txtLongitud);
		getContentPane().add(lblLocalidad);
		getContentPane().add(lblProvincia);
		getContentPane().add(lblLatitud);
		getContentPane().add(lblLongitud);
		getContentPane().add(btnIngreso);
		getContentPane().add(btnEliminar);
		getContentPane().add(btnSalirLocalidades);
		getContentPane().add(provincias);
	}		

	
	 
}
