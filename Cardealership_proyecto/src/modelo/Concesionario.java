package modelo;

import modelo.vehiculo.*;
import modelo.persona.*;
import modelo.venta.*;

public class Concesionario {

	private String nombre;
	private Cliente clientes[] = new Cliente[0];
	private Empleado empleados[] = new Empleado[0];
	private Vehiculo vehiculos[] = new Vehiculo[0];
	private Venta ventas[] = new Venta[0];

	public Concesionario(String nombre) {
		if (nombre.isEmpty() || nombre == null) {
			throw new IllegalArgumentException("Ingrese un nombre válido para el concesionario");
		}
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.isEmpty() || nombre == null) {
			throw new IllegalArgumentException("Ingrese un nombre válido para el concesionario");
		}
		this.nombre = nombre;
	}

	// No hacer getter y setters de los arrays, esos llevan otros nombres
	// especificados en el diagrama, además de funcionalidades extra

}
