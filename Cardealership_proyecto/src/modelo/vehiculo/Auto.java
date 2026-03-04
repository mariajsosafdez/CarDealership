package modelo.vehiculo;

public class Auto extends Vehiculo {

	private int numeroPuertas;
	private String carroceria;
	
	
	public Auto(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible,String carroceria, int numeroPuertas) {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		
		this.carroceria = carroceria;
		this.numeroPuertas = numeroPuertas;
		
	}
	
	
	@Override
	public String toString() {
		return "Auto [numeroPuertas=" + numeroPuertas + ", carroceria=" + carroceria + ", Placa()=" + getPlaca()
				+ ", Marca()=" + getMarca() + ", Modelo()=" + getModelo() + ", Year()=" + getYear()
				+ ", Precio()=" + getPrecioBase() + ", TipoDeCombustible()=" + getTipoDeCombustible()
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
	public void setPlaca(String placa) {
		if(placa == null || placa.trim().isEmpty()) {
			do {
				char[] buffer = new char[6];					
				for(int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
				for(int i = 3; i < 6; i++) buffer[i] = getRandom(NUMEROS);
				
				placa = new String(buffer);
			} while (placasRegistradas.contains(placa));//Asigna placas aleatorias hasta que no esté repetida
		} 
		
		if (!placa.matches("[A-Z]{3}\\d{3}")) {
	        throw new IllegalArgumentException("Formato inválido para Auto");
		}
		
		registrarPlaca(placa);
		this.placa = placa;
	}
	

}
