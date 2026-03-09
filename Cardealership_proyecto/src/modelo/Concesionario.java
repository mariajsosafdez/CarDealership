package modelo;

import java.util.Arrays;
import java.util.Date;

import modelo.venta.Venta;
import modelo.venta.InvalidVentaException;
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

	// Concesionario
	public Concesionario(String nombre) /*throws ValidacionException*/ {
		if (nombre.isEmpty() || nombre == null) {
//			throw new ValidacionException("Ingrese un nombre válido para el concesionario");
		}
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) /*throws ValidacionException */{
		if (nombre.isEmpty() || nombre == null) {
//			throw new ValidacionException("Ingrese un nombre válido para el concesionario");
		}
		this.nombre = nombre;
	}
	// CRUD CLIENTE

	public void registrarCliente(String tipoDocumento, String numeroDocumento, String nombre, String apellido,
			String telefono, String email) throws ValidacionException {
		if (tipoDocumento == null || tipoDocumento.trim().isEmpty())
			throw new ValidacionException("El tipo de documento no puede estar vacío.");
		if (numeroDocumento == null || numeroDocumento.trim().isEmpty())
			throw new ValidacionException("El número de documento no puede estar vacío.");
		if (buscarCliente(numeroDocumento) != null)
			throw new ValidacionException("Ya existe un cliente con ese número de documento.");
		if (nombre == null || nombre.trim().isEmpty())
			throw new ValidacionException("El nombre no puede estar vacío.");
		if (apellido == null || apellido.trim().isEmpty())
			throw new ValidacionException("El apellido no puede estar vacío.");
		if (telefono == null || telefono.trim().isEmpty())
			throw new ValidacionException("El teléfono no puede estar vacío.");
		if (email == null || email.trim().isEmpty())
			throw new ValidacionException("El email no puede estar vacío.");
		if (!email.contains("@"))
			throw new ValidacionException("El email no tiene un formato válido.");

		Cliente c = new Cliente(tipoDocumento, numeroDocumento, nombre, apellido, telefono, email);
		clientes = Arrays.copyOf(clientes, clientes.length + 1);
		clientes[clientes.length - 1] = c;
	}

	public Cliente buscarCliente(String numeroDocumento) {
		int i = 0;
		while (i < clientes.length && !clientes[i].getId().equals(numeroDocumento)) {
			i++;
		}
		if (i == clientes.length) {
			return null;
		}
		return clientes[i];
	}

	public int buscarClienteIndex(String numeroDocumento) {
		int i = 0;
		while (i < clientes.length && !clientes[i].getId().equals(numeroDocumento)) {
			i++;
		}
		if (i == clientes.length) {
			return -1;
		}
		return i;
	}

	public boolean eliminarCliente(String numeroDocumento) {
		int idx = buscarClienteIndex(numeroDocumento);
		if (idx == -1)
			return false;
		Cliente[] nuevo = new Cliente[clientes.length - 1];
		int i = 0, j = 0;
		while (i < clientes.length) {
			if (i != idx)
				nuevo[j++] = clientes[i];
			i++;
		}
		clientes = nuevo;
		return true;
	}

	public Cliente[] listarClientes() {
		return Arrays.copyOf(clientes, clientes.length);
	}

	// CRUD EMPLEADO

	public void registrarEmpleado(String tipoDocumento, String numeroDocumento, String nombre, String apellido,
			String telefono, float salario) throws ValidacionException {
		if (tipoDocumento == null || tipoDocumento.trim().isEmpty())
			throw new ValidacionException("El tipo de documento no puede estar vacío.");
		if (numeroDocumento == null || numeroDocumento.trim().isEmpty())
			throw new ValidacionException("El número de documento no puede estar vacío.");
		if (buscarEmpleado(numeroDocumento) != null)
			throw new ValidacionException("Ya existe un empleado con ese número de documento.");
		if (nombre == null || nombre.trim().isEmpty())
			throw new ValidacionException("El nombre no puede estar vacío.");
		if (apellido == null || apellido.trim().isEmpty())
			throw new ValidacionException("El apellido no puede estar vacío.");
		if (telefono == null || telefono.trim().isEmpty())
			throw new ValidacionException("El teléfono no puede estar vacío.");
		if (salario < 0)
			throw new ValidacionException("El salario no puede ser negativo.");

		Empleado e = new Empleado(tipoDocumento, numeroDocumento, nombre, apellido, telefono, salario);
		empleados = Arrays.copyOf(empleados, empleados.length + 1);
		empleados[empleados.length - 1] = e;
	}

	public void registrarVendedor(String tipoDocumento, String numeroDocumento, String nombre, String apellido,
			String telefono, float salario) throws ValidacionException {
		if (tipoDocumento == null || tipoDocumento.trim().isEmpty())
			throw new ValidacionException("El tipo de documento no puede estar vacío.");
		if (numeroDocumento == null || numeroDocumento.trim().isEmpty())
			throw new ValidacionException("El número de documento no puede estar vacío.");
		if (buscarEmpleado(numeroDocumento) != null)
			throw new ValidacionException("Ya existe un empleado/vendedor con ese número de documento.");
		if (nombre == null || nombre.trim().isEmpty())
			throw new ValidacionException("El nombre no puede estar vacío.");
		if (apellido == null || apellido.trim().isEmpty())
			throw new ValidacionException("El apellido no puede estar vacío.");
		if (telefono == null || telefono.trim().isEmpty())
			throw new ValidacionException("El teléfono no puede estar vacío.");
		if (salario < 0)
			throw new ValidacionException("El salario no puede ser negativo.");

		Vendedor v = new Vendedor(tipoDocumento, numeroDocumento, nombre, apellido, telefono, salario);
		empleados = Arrays.copyOf(empleados, empleados.length + 1);
		empleados[empleados.length - 1] = v;
	}

	public Empleado buscarEmpleado(String numeroDocumento) {
		int i = 0;
		while (i < empleados.length && !empleados[i].getId().equals(numeroDocumento)) {
			i++;
		}
		if (i == empleados.length) {
			return null;
		}
		return empleados[i];
	}

	public int buscarEmpleadoIndex(String numeroDocumento) {
		int i = 0;
		while (i < empleados.length && !empleados[i].getId().equals(numeroDocumento)) {
			i++;
		}
		if (i == empleados.length) {
			return -1;
		}
		return i;
	}

	public boolean eliminarEmpleado(String numeroDocumento) {
		int idx = buscarEmpleadoIndex(numeroDocumento);
		if (idx == -1)
			return false;
		Empleado[] nuevo = new Empleado[empleados.length - 1];
		int i = 0, j = 0;
		while (i < empleados.length) {
			if (i != idx)
				nuevo[j++] = empleados[i];
			i++;
		}
		empleados = nuevo;
		return true;
	}

	public Empleado[] listarEmpleados() {
		return Arrays.copyOf(empleados, empleados.length);
	}

	// CRUD VENTAS

	public void venderVehiculo(Vehiculo vehiculo, Cliente cliente, Vendedor vendedor) throws InvalidVentaException {

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

		while (i < ventas.length && !ventas[i].getCodigo().equalsIgnoreCase(codigo)) {
			i++;
		}
		if (i == ventas.length) {
			return null;
		}
		return ventas[i];
	}

	public int buscarVentaIndex(String codigo) {

		int i = 0;

		while (i < ventas.length && !ventas[i].getCodigo().equalsIgnoreCase(codigo)) {
			i++;
		}
		if (i == ventas.length) {
			return -1;
		}
		return i;
	}

	public boolean eliminarVenta(String codigo) {

		int index = buscarVentaIndex(codigo);
		if (index == -1)
			return false;
		Venta[] nuevo = new Venta[ventas.length - 1];
		int i = 0, j = 0;
		while (i < ventas.length) {
			if (i != index)
				nuevo[j++] = ventas[i];
			i++;
		}
		ventas = nuevo;
		return true;
	}

	public Venta[] listarVentas() {

		return Arrays.copyOf(ventas, ventas.length);
	}

	// VALIDAR DATOS PARA VEHICULOS
	private void validarDatosGenerales(String marca, String modelo, int year, float precio, float kilometraje,
			float cilindraje) throws EObjectNull, EObjectInvalido {
		if (marca == null || marca.trim().isEmpty())
			throw new EObjectNull("La marca es obligatoria.");
		if (modelo == null || modelo.trim().isEmpty())
			throw new EObjectNull("El modelo es obligatorio.");

		if (year < 2014 || year > 2027)
			throw new EObjectInvalido("Año fuera de rango (2014-2027).");

		if (precio <= 0)
			throw new EObjectInvalido("El precio debe ser un valor positivo.");
		if (kilometraje < 0)
			throw new EObjectInvalido("El kilometraje no puede ser negativo.");
		if (cilindraje <= 0)
			throw new EObjectInvalido("El cilindraje debe ser un valor positivo.");
	}

	private String validarCarroceria(String c) throws EObjectNull, EObjectInvalido {
		if (c == null || c.trim().isEmpty())
			throw new EObjectNull("La carrocería es obligatoria.");
		String limpio = c.trim().toUpperCase();
		switch (limpio) {
		case "SEDAN":
		case "HATCHBACK":
		case "SUV":
		case "PICKUP":
		case "COUPE":
		case "CONVERTIBLE":
		case "FURGON":
			return limpio;
		default:
			throw new EObjectInvalido("Carrocería '" + c
					+ "' inválida. Opciones: Sedan, Hatchback, SUV, Pickup, Coupe, Convertible o Furgon.");
		}

	}

	private String validarCategoria(String categoria) throws EObjectNull, EObjectInvalido {
		if (categoria == null || categoria.trim().isEmpty())
			throw new EObjectNull("La categoría de moto es obligatoria.");
		String limpio = categoria.trim().toUpperCase();
		switch (limpio) {
		case "SCOOTER":
		case "SPORT":
		case "NAKED":
		case "ENDURO":
		case "TOURING":
		case "CRUISER":
		case "CROSS":
			return limpio;
		default:
			throw new EObjectInvalido("Categoría '" + categoria
					+ "' inválida. Opciones: Scooter, Sport, Naked, Enduro, Touring, Cruiser o Cross.");
		}
	}

	// CRUD VEHICULOS

	public void registrarAuto(String placa, String marca, String modelo, int year, float precio,
			String tipoDeCombustible, String transmision, float kilometraje, String color, String estado,
			float cilindraje, boolean disponible, String carroceria, int numeroPuertas)
			throws EObjectNull, EObjectInvalido, EObjectVoid, EObjectExiste {

		validarDatosGenerales(marca, modelo, year, precio, kilometraje, cilindraje);
		String carroceriaValidada = validarCarroceria(carroceria);

		if (numeroPuertas < 2 || numeroPuertas > 6) {
			throw new EObjectInvalido("El numero de puertas " + numeroPuertas + "debe estar entre 2 y 6");
		}

		Auto nuevoAuto = new Auto(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje,
				color, estado, cilindraje, disponible, carroceriaValidada, numeroPuertas);

		nuevoAuto.validarCombustible(tipoDeCombustible);
		nuevoAuto.validarTransmision(transmision);
		nuevoAuto.validarEstado(estado);
		nuevoAuto.setPlaca(placa);

		this.vehiculos = Arrays.copyOf(this.vehiculos, this.vehiculos.length + 1);
		this.vehiculos[this.vehiculos.length - 1] = nuevoAuto;
	}

	public void registrarMoto(String placa, String marca, String modelo, int year, float precio,
			String tipoDeCombustible, String transmision, float kilometraje, String color, String estado,
			float cilindraje, boolean disponible, String categoria) throws Exception {

		validarDatosGenerales(marca, modelo, year, precio, kilometraje, cilindraje);
		String categoriaValidada = validarCategoria(categoria);

		Moto nuevaMoto = new Moto(null, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color,
				estado, cilindraje, disponible, categoriaValidada);

		nuevaMoto.validarCombustible(tipoDeCombustible);
		nuevaMoto.validarTransmision(transmision);
		nuevaMoto.validarEstado(estado);

		nuevaMoto.setPlaca(placa);

		this.vehiculos = Arrays.copyOf(this.vehiculos, this.vehiculos.length + 1);
		this.vehiculos[this.vehiculos.length - 1] = nuevaMoto;
	}

	public Vehiculo[] listarVehiculos() {
		return Arrays.copyOf(vehiculos, vehiculos.length);
	}
	
	public Vehiculo buscarVehiculos(String placa) {
		
		int i = 0;
		
		while(i < vehiculos.length && !vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
			i++;
		}
		if(i == vehiculos.length) {
			return null;
		}
		return vehiculos[i];
	}
	
	
	public int buscarVehiculosIndex(String placa) {
		
		int i = 0;
		
		while (i < vehiculos.length && !vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
			i++;
		}
		if (i == vehiculos.length) {
			return -1;
		}
		return i;

	}
	
	public boolean eliminarVehiculo(String placa) {

		int index = buscarVehiculosIndex(placa);
		if (index == -1)
			return false;
		Vehiculo[] nuevo = new Vehiculo[vehiculos.length - 1];
		int i = 0, j = 0;
		while (i < vehiculos.length) {
			if (i != index)
				nuevo[j++] = vehiculos[i];
			i++;
		}
		vehiculos = nuevo;
		return true;
	}
	

}
