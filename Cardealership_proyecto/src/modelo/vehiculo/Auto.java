package modelo.vehiculo;

public class Auto extends Vehiculo {

	private int numeroPuertas;
	private String carroceria;
	
	
	public Auto(String placa, String marca, String modelo, String year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible,int numeroPuertas, String carroceria) {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		
		this.carroceria = carroceria;
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
	public void setPlaca(String placa) {
		if(placa == null || placa.isEmpty()) {
			char[] buffer = new char[6];					
			for(int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
			for(int i = 3; i < 6; i++) buffer[i] = getRandom(NUMEROS);
			this.placa = new String(buffer);
		} else {
			this.placa = placa;
		}
		
	}
	

}
