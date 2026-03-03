package modelo.vehiculo;

public class Moto extends Vehiculo{
	
	private String categoria;

	public Moto(String placa, String marca, String modelo, String year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible, String categoria) {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		this.categoria = categoria;
	}

	
	public String getCategoria() {
		return categoria;
	}
	
	

	@Override
	public void setPlaca(String placa) {
		if(placa == null || placa.isEmpty()) {
			char[] buffer = new char[6];
			for(int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
			buffer[3] = getRandom(NUMEROS);
			buffer[4] = getRandom(NUMEROS);
			buffer[5] = getRandom(LETRAS);
		}
		
	}


	@Override
	public String toString() {
		return "Moto [categoria=" + categoria + ",	Placa()=" + getPlaca() + ", Marca()=" + getMarca()
				+ ", Modelo()=" + getModelo() + ", Year()=" + getYear() + ", Precio()=" + getPrecio()
				+ ", TipoDeCombustible()=" + getTipoDeCombustible() + ", Transmision()=" + getTransmision()
				+ ", Kilometraje()=" + getKilometraje() + ", Color()=" + getColor() + ", Estado()="
				+ getEstado() + ", Cilindraje()=" + getCilindraje() + ", Disponible()=" + isDisponible() + "]";
	}
	
	

}
