package MVCPrincipal;

import CodNegocio.Negocio;
import Controlador.Controlador;
import Vista.GuiApp;


public class Principal {


	public static void main(String []args) {
		
		GuiApp vista=new GuiApp();
		Negocio modelo=new Negocio();
		Controlador controlador=new Controlador(vista,modelo);
		vista.setControlador(controlador);
		vista.creaGUI();
	}
	
}
