package modelo.vehiculo;

import modelo.vehiculo.excepciones.EObjectExiste;
import modelo.vehiculo.excepciones.EObjectInvalido;
import modelo.vehiculo.excepciones.EObjectNull;
import modelo.vehiculo.excepciones.EObjectVoid;

public class Auto extends Vehiculo {

	private int numeroPuertas;
	private String carroceria;
	
	
	public Auto(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible,
			String carroceria, int numeroPuertas) throws Exception {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		
		this.carroceria = carroceria;	//Falta excepcion de carroceria no puede ser nula, y numero de puertas no puede ser 0
		this.numeroPuertas = numeroPuertas;
		
	}
	
	
	@Override
	public String toString() {
		return "Auto [numeroPuertas=" + numeroPuertas + ", carroceria=" + carroceria + ", Placa()=" + getPlaca()
				+ ", Marca()=" + getMarca() + ", Modelo()=" + getModelo() + ", Year()=" + getYear()
				+ ", Precio()=" + getPrecio() + ", TipoDeCombustible()=" + getTipoDeCombustible()
				+ ", Transmision()=" + getTransmision() + ", Kilometraje()=" + getKilometraje() + ", Color()="
				+ getColor() + ",	()=" + getEstado() + ", Cilindraje()=" + getCilindraje()
				+ ", isDisponible()=" + isDisponible() + "]";
	}


	public int getNumeroPuertas() {
		return numeroPuertas;
	}


	public String getCarroceria() {
		return carroceria;
	}


	@Override
	public void setPlaca(String placa)  throws EObjectInvalido,EObjectNull,EObjectExiste, EObjectVoid {
	    // 1. Generación aleatoria
	    if (placa == null || placa.trim().isEmpty()) {
	        do {
	            char[] buffer = new char[6];
	            for (int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
	            for (int i = 3; i < 6; i++) buffer[i] = getRandom(NUMEROS);
	            placa = new String(buffer);
	        } while (existePlaca(placa));
	    }

	    // 2. Validación de formato
	    if (!placa.matches("[A-Z]{3}\\d{3}")) {
	        throw new EObjectInvalido("Formato inválido para Auto");
	    }

	    // 3. Lógica de registro UNIFICADA
	    if (!placa.equals(this.placa)) {
	        if (existePlaca(placa)) {
	            throw new EObjectExiste("La placa: " + placa + " ya está registrada");
	        }
	        
	        // ESTO ES LO QUE CAMBIA: Solo registramos si es diferente
	        registrarPlaca(placa);
	        this.placa = placa;
	    }
	    // Si son iguales, el método termina aquí sin hacer nada, lo cual es correcto.
	}
	

}
