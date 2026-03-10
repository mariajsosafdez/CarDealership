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
		
		// Clientes
	    Object[] clientesArchivo = Utils.leerObjetos(Utils.baseDireccion + "clientes.cli");
	    for (int i = 0; i < clientesArchivo.length; i++) {
	        Cliente c = (Cliente) clientesArchivo[i];
	        try {
	            concesionario.registrarCliente(
	                c.getTipoDocumento(),
	                c.getId(),
	                c.getNombre(),
	                c.getApellido(),
	                c.getTelefono(),
	                c.getEmail()
	            );
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Empleado
	    Object[] empleadosArchivo = Utils.leerObjetos(Utils.baseDireccion + "empleados.emp");

	    for (int i = 0; i < empleadosArchivo.length; i++) {

	        if (empleadosArchivo[i] instanceof Vendedor) {

	            Vendedor v = (Vendedor) empleadosArchivo[i];

	            try {
	                concesionario.registrarVendedor(
	                    v.getTipoDocumento(),
	                    v.getId(),
	                    v.getNombre(),
	                    v.getApellido(),
	                    v.getTelefono(),
	                    v.getSalario()
	                );
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        } else if (empleadosArchivo[i] instanceof Empleado) {

	            Empleado e = (Empleado) empleadosArchivo[i];

	            try {
	                concesionario.registrarEmpleado(
	                    e.getTipoDocumento(),
	                    e.getId(),
	                    e.getNombre(),
	                    e.getApellido(),
	                    e.getTelefono(),
	                    e.getSalario()
	                );
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }

	    // Vehiculo
	    Object[] vehiculosArchivo = Utils.leerObjetos(Utils.baseDireccion + "vehiculos.veh");

	    for (int i = 0; i < vehiculosArchivo.length; i++) {

	        if (vehiculosArchivo[i] instanceof Auto) {

	            Auto a = (Auto) vehiculosArchivo[i];

	            try {
	                concesionario.registrarAuto(
	                    a.getPlaca(),
	                    a.getMarca(),
	                    a.getModelo(),
	                    a.getYear(),
	                    a.getPrecio(),
	                    a.getTipoDeCombustible(),
	                    a.getTransmision(),
	                    a.getKilometraje(),
	                    a.getColor(),
	                    a.getEstado(),
	                    a.getCilindraje(),
	                    a.isDisponible(),
	                    a.getCarroceria(),
	                    a.getNumeroPuertas()
	                );
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        } else if (vehiculosArchivo[i] instanceof Moto) {

	            Moto m = (Moto) vehiculosArchivo[i];

	            try {
	                concesionario.registrarMoto(
	                    m.getPlaca(),
	                    m.getMarca(),
	                    m.getModelo(),
	                    m.getYear(),
	                    m.getPrecio(),
	                    m.getTipoDeCombustible(),
	                    m.getTransmision(),
	                    m.getKilometraje(),
	                    m.getColor(),
	                    m.getEstado(),
	                    m.getCilindraje(),
	                    m.isDisponible(),
	                    m.getCategoria()
	                );
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // Venta
	    Object[] ventasArchivo = Utils.leerObjetos(Utils.baseDireccion + "ventas.vta");
	    for (int i = 0; i < ventasArchivo.length; i++) {
	        Venta v = (Venta) ventasArchivo[i];
	        concesionario.agregarVenta(v);
	    }
	    SwingUtilities.invokeLater(() -> {
	        new Ventana(concesionario);
	    });
	}
	
	

}