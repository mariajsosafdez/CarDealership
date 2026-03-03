package modelo.vehiculo;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehiculo {

	
	protected static final String LETRAS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Usamos esto para manejar la codificacion de las placas
	protected static final String NUMEROS = "0123456789"; // "		"
	
	protected String placa;
	protected String marca;
	protected String modelo;
	protected String year;
	protected float precio;
	protected String tipoDeCombustible;
	protected String transmision;
	protected float kilometraje;
	protected String color;
	protected String estado;
	protected float cilindraje;
	protected boolean disponible;
	
	
	public Vehiculo(String placa, String marca, String modelo, String year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje,  String color, String estado, float cilindraje, boolean disponible) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.year = year;
		this.precio = precio;
		this.tipoDeCombustible = tipoDeCombustible;
		this.transmision = transmision;
		this.kilometraje = 0;
		this.color = color;
		this.estado = estado;
		this.cilindraje = cilindraje;
		this.disponible = disponible;
	}


	public String getPlaca() {
		return placa;
	}


	public String getMarca() {
		return marca;
	}


	public String getModelo() {
		return modelo;
	}


	public String getYear() {
		return year;
	}


	public float getPrecio() {
		return precio;
	}


	public String getTipoDeCombustible() {
		return tipoDeCombustible;
	}


	public String getTransmision() {
		return transmision;
	}


	public float getKilometraje() {
		return kilometraje;
	}


	public String getColor() {
		return color;
	}


	public String getEstado() {
		return estado;
	}


	public float getCilindraje() {
		return cilindraje;
	}


	public boolean isDisponible() {
		return disponible;
	}
	
	/*
	 getRandom lo usamos para generar un caracter random
	 Funcionamiento:
	 fuente.lenght devuelve el tamaño del argumento que le pasemos los cuales son LETRAS o NUMEROS que lo usamos para placa
	 nextInt escoje un numero en ese tamaño el cual tiene un valor
	 y charAt devuelve el valor de la posicion que escogio nextInt
	 */
	
	
	protected char getRandom(String fuente) {							
		return fuente.charAt(ThreadLocalRandom.current().nextInt(fuente.length())); 
	}
	


	public abstract void setPlaca(String placa); // Hacemos el metodo abstracto para que los hijos tengan que implementar su forma de cambiar placa


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public void setKilometraje(float kilometraje) {
		this.kilometraje = kilometraje;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
	
	
	
	
	

	
	
}
