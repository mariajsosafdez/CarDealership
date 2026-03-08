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

	public Cliente[] listarClientes() {
		return clientes;
	}
	public Empleado[] listarEmpleados() {
		return empleados;
	}
	// No hacer getter y setters de los arrays, esos llevan otros nombres
	// especificados en el diagrama, además de funcionalidades extra

	public void venderVehiculo(Vehiculo vehiculo, Cliente cliente, Vendedor vendedor)
	        throws InvalidVentaException {

	    if (vehiculo == null)
	        throw new InvalidVentaException("Vehiculo invalido");

	    if (cliente == null)
	        throw new InvalidVentaException("Cliente invalido");

	    if (vendedor == null)
	        throw new InvalidVentaException("Vendedor invalido");

	    if (!vehiculo.isDisponible())
	        throw new InvalidVentaException("Vehiculo no disponible");

	    String codigoVenta = "V" + (ventas.length + 1);

	    Venta venta = new Venta(codigoVenta, cliente, vendedor, new Date());

	    venta.agregarVehiculo(vehiculo);

	    ventas = Arrays.copyOf(ventas, ventas.length + 1);
	    ventas[ventas.length - 1] = venta;
	}
	
	public Venta buscarVenta(String codigo) {

	    int i = 0;

	    while (i < ventas.length) {

	        if (ventas[i].getCodigo().equalsIgnoreCase(codigo)) {
	            return ventas[i];
	        }

	        i++;
	    }

	    return null;
	}
	
	public int buscarVentaIndex(String codigo) {

	    int i = 0;

	    while (i < ventas.length) {

	        if (ventas[i].getCodigo().equalsIgnoreCase(codigo)) {
	            return i;
	        }

	        i++;
	    }

	    return -1;
	}
	
	public boolean eliminarVenta(int index) {

	    if (index < 0 || index >= ventas.length)
	        return false;

	    int i = index;

	    while (i < ventas.length - 1) {
	        ventas[i] = ventas[i + 1];
	        i++;
	    }

	    ventas = Arrays.copyOf(ventas, ventas.length - 1);

	    return true;
	}
	
	public Venta[] listarVentas() {

	    return Arrays.copyOf(ventas, ventas.length);
	}
}


