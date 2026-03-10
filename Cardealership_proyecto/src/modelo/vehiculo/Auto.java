package modelo.vehiculo;


import java.io.Serializable;

import modelo.Concesionario;
import modelo.vehiculo.excepciones.EObjectExiste;

import modelo.vehiculo.excepciones.EObjectInvalido;
import modelo.vehiculo.excepciones.EObjectNull;
import modelo.vehiculo.excepciones.EObjectVoid;
import utils.Utils;
import vista.Ventana;

public class Auto extends Vehiculo implements Serializable{
	private static final long serialVersionUID = 1;

	private int numeroPuertas;
	private String carroceria;
	
	
	public Auto(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible,
			String carroceria, int numeroPuertas)  {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		
		this.carroceria = carroceria;	//Falta excepcion de carroceria no puede ser nula, y numero de puertas no puede ser 0
		this.numeroPuertas = numeroPuertas;
		
	}


	public int getNumeroPuertas() {
		return numeroPuertas;
	}


	public String getCarroceria() {
		return carroceria;
	}


	@Override
    public void setPlaca(String placaRecibida) throws EObjectInvalido, EObjectNull, EObjectExiste, EObjectVoid {
        String placaFinal = (placaRecibida == null || placaRecibida.trim().isEmpty()) ? null : placaRecibida;

        // 1. Generación si es necesario
        if (placaFinal == null) {
            do {
                char[] buffer = new char[6];
                for (int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
                for (int i = 3; i < 6; i++) buffer[i] = getRandom(NUMEROS);
                placaFinal = new String(buffer);
            } while (existePlaca(placaFinal)); 
        }

        // 2. Validar formato Auto (AAA111)
        if (!placaFinal.matches("[A-Z]{3}\\d{3}")) {
            throw new EObjectInvalido("Formato inválido para Auto (AAA111)");
        }

        // 3. Registro y Asignación
        // Verificamos si es una placa nueva para este objeto
        if (this.placa == null || !placaFinal.equalsIgnoreCase(this.placa)) {
            registrarPlaca(placaFinal); // Esto lanza EObjectExiste si ya alguien la tiene
            this.placa = placaFinal;    // ¡Crucial! Aquí se llena el dato para la tabla
        }
    }
	
	@Override
	public String toString() {

	    return "\nCARRO" +
	           "\nPlaca: " + getPlaca() +
	           "\nMarca: " + getMarca() +
	           "\nModelo: " + getModelo() +
	           "\nAño: " + getYear() +
	           "\nPrecio: $" + getPrecio() +
	           "\nCarrocería: " + carroceria +
	           "\nPuertas: " + numeroPuertas +
	           "\nCombustible: " + getTipoDeCombustible() +
	           "\nTransmisión: " + getTransmision() +
	           "\nKilometraje: " + getKilometraje() + " km" +
	           "\nColor: " + getColor() +
	           "\nCilindraje: " + getCilindraje() +
	           "\nEstado: " + getEstado() +
	           "\nDisponible: " + (isDisponible() ? "Sí" : "No");
	}
	

}