package modelo;
import java.util.Arrays;
import java.util.Date;

import modelo.venta.Venta;
import modelo.venta.InvalidVentaException;

import modelo.persona.Cliente;
import modelo.persona.Vendedor;

import modelo.vehiculo.Vehiculo;
import modelo.vehiculo.*;
import modelo.persona.*;
import modelo.venta.*;

import java.util.Arrays;
import modelo.vehiculo.*;
import modelo.vehiculo.excepciones.*;

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
	
	
	public void registrarAuto(String placa, String marca, String modelo, int year, float precio, 
            String tipoDeCombustible, String transmision, float kilometraje, 
            String color, String estado, float cilindraje, boolean disponible, 
            String carroceria, int numeroPuertas) throws EObjectNull,EObjectInvalido,EObjectVoid,EObjectExiste {
		
		validarDatosGenerales(marca,modelo,year,precio,kilometraje,cilindraje);
		String carroceriaValidada = validarCarroceria(carroceria);
		
		if (numeroPuertas < 2 || numeroPuertas > 6) {
			throw new EObjectInvalido("El numero de puertas " + numeroPuertas + "debe estar entre 2 y 6");
		}
		
		Auto nuevoAuto = new Auto(placa,marca,modelo,year,precio,tipoDeCombustible,transmision,kilometraje,color,estado,cilindraje,disponible,carroceriaValidada,numeroPuertas);
		
		nuevoAuto.validarCombustible(tipoDeCombustible);
		nuevoAuto.validarTransmision(transmision);
        nuevoAuto.validarEstado(estado);
        
        nuevoAuto.setPlaca(placa);
        
        
        this.vehiculos = Arrays.copyOf(this.vehiculos, this.vehiculos.length + 1);
        this.vehiculos[this.vehiculos.length - 1 ]= nuevoAuto;
	    }	

	
	private void validarDatosGenerales(String marca, String modelo, int year, float precio, float kilometraje, float cilindraje) throws EObjectNull,EObjectInvalido {
        if (marca == null || marca.trim().isEmpty()) throw new EObjectNull("La marca es obligatoria.");
        if (modelo == null || modelo.trim().isEmpty()) throw new EObjectNull("El modelo es obligatorio.");
        
 
        if (year < 2014 || year > 2027) throw new EObjectInvalido("Año fuera de rango (2014-2027).");
        
        if (precio <= 0) throw new EObjectInvalido("El precio debe ser un valor positivo.");
        if (kilometraje < 0) throw new EObjectInvalido("El kilometraje no puede ser negativo.");
        if (cilindraje <= 0) throw new EObjectInvalido("El cilindraje debe ser un valor positivo.");
    }
	
	private String validarCarroceria(String c) throws EObjectNull, EObjectInvalido {
        if (c == null || c.trim().isEmpty()) throw new EObjectNull("La carrocería es obligatoria.");
        String limpio = c.trim().toUpperCase();
        switch (limpio) {
            case "SEDAN": case "HATCHBACK": case "SUV": case "PICKUP": 
            case "COUPE": case "CONVERTIBLE": case "FURGON": 
                return limpio;
            default: 
                throw new EObjectInvalido("Carrocería '" + c + "' inválida. Opciones: Sedan, Hatchback, SUV, Pickup, Coupe, Convertible o Furgon.");
        }
          
    }

	
	public void registrarMoto(String placa, String marca, String modelo, int year, float precio, 
            String tipoDeCombustible, String transmision, float kilometraje, 
            String color, String estado, float cilindraje, boolean disponible, 
            String categoria) throws Exception {


validarDatosGenerales(marca, modelo, year, precio, kilometraje, cilindraje);
String categoriaValidada = validarCategoria(categoria);


Moto nuevaMoto = new Moto(null, marca, modelo, year, precio, tipoDeCombustible, 
                transmision, kilometraje, color, estado, cilindraje, 
                disponible, categoriaValidada);


nuevaMoto.validarCombustible(tipoDeCombustible);
nuevaMoto.validarTransmision(transmision);
nuevaMoto.validarEstado(estado);


nuevaMoto.setPlaca(placa);

this.vehiculos = Arrays.copyOf(this.vehiculos, this.vehiculos.length + 1);
this.vehiculos[this.vehiculos.length - 1] = nuevaMoto;
}
	
	private String validarCategoria(String categoria) throws EObjectNull, EObjectInvalido {
        if (categoria == null || categoria.trim().isEmpty()) throw new EObjectNull("La categoría de moto es obligatoria.");
        String limpio = categoria.trim().toUpperCase();
        switch (limpio) {
            case "SCOOTER": case "SPORT": case "NAKED": case "ENDURO": 
            case "TOURING": case "CRUISER": case "CROSS": 
                return limpio;
            default: 
                throw new EObjectInvalido("Categoría '" + categoria + "' inválida. Opciones: Scooter, Sport, Naked, Enduro, Touring, Cruiser o Cross.");
        }
    }
	
	
	
	
	
	
	
	
	
	

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


