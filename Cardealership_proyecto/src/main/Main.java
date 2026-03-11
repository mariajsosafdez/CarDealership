package main;

import javax.swing.SwingUtilities;

import modelo.Concesionario;
import vista.*;
import utils.Utils;
import modelo.persona.Cliente;
import modelo.persona.Empleado;
import modelo.persona.Vendedor;
import modelo.vehiculo.Vehiculo;
import modelo.vehiculo.Auto;
import modelo.vehiculo.Moto;
import modelo.venta.Venta;

public class Main {

	public static void main(String[] args) {
		Concesionario concesionario = new Concesionario("CarDelership");

		// Clientes - Es mucho más corto

		Object[] clientesArchivo = Utils.leerObjetos(Utils.baseDireccion + "clientes.cli");

		for (int i = 0; i < clientesArchivo.length; i++) {
			Cliente c = (Cliente) clientesArchivo[i];
			concesionario.agregarCliente(c);
		}

		// Empleado
		Object[] empleadosArchivo = Utils.leerObjetos(Utils.baseDireccion + "empleados.emp");

		for (int i = 0; i < empleadosArchivo.length; i++) {
			Empleado e = (Empleado) empleadosArchivo[i];
			concesionario.agregarEmpleado(e);
		}
		
		//Vehiculo

		Object[] vehiculosArchivo = Utils.leerObjetos(Utils.baseDireccion + "vehiculos.veh");

		for (int i = 0; i < vehiculosArchivo.length; i++) {
			Vehiculo v = (Vehiculo) vehiculosArchivo[i];
			concesionario.agregarVehiculo(v);
			
		}
		
		// Venta
		
		Object[] ventasArchivo = Utils.leerObjetos(Utils.baseDireccion + "ventas.vta");
		for (int i = 0; i < ventasArchivo.length; i++) {
			Venta v = (Venta) ventasArchivo[i];
			concesionario.agregarVenta(v);
		}
		
		//INTERFAZ 
		SwingUtilities.invokeLater(() -> {
			new Ventana(concesionario);
		});
	}

}