package modelo.vehiculo;

import modelo.vehiculo.excepciones.*;



public class Moto extends Vehiculo{
	
	private String categoria;

	public Moto(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje, String color, String estado, float cilindraje, boolean disponible, String categoria) throws Exception {
		super(placa, marca, modelo, year, precio, tipoDeCombustible, transmision, kilometraje, color, estado, cilindraje,
				disponible);
		this.categoria = categoria; //Excepcion categoria no nula
	}

	
	public String getCategoria() {
		return categoria;
	}
	
	

	@Override
	public void setPlaca(String placa) throws EObjectInvalido, EObjectNull, EObjectExiste, EObjectVoid{
	    // 1. Si mandan null o vacío, generamos una automática
	    if (placa == null || placa.trim().isEmpty()) {
	        do { 
	            char[] buffer = new char[6];
	            for (int i = 0; i < 3; i++) buffer[i] = getRandom(LETRAS);
	            buffer[3] = getRandom(NUMEROS);
	            buffer[4] = getRandom(NUMEROS);
	            buffer[5] = getRandom(LETRAS);
	            placa = new String(buffer);     
	        } while (existePlaca(placa));   
	    }
	    
	    // 2. Validamos el formato de Moto (AAA11A)
	    if (!placa.matches("[A-Z]{3}\\d{2}[A-Z]")) {
	        throw new EObjectInvalido("Formato inválido para Moto");
	    }

	    // 3. LA LÓGICA UNIFICADA:
	    // ¿Es una placa distinta a la que ya tengo? (Esto cubre el caso inicial porque null != "AAA11A")
	    if (!placa.equals(this.placa)) {
	        
	        // ¿Esa placa ya la tiene alguien más en el sistema?
	        if (existePlaca(placa)) {
	            throw new EObjectExiste("La placa: " + placa + " ya está registrada");
	        }
	        
	        // Si llegamos aquí, es una placa nueva o un cambio válido
	        registrarPlaca(placa); // Se agrega al String[]
	        this.placa = placa;    // Se guarda en el objeto
	    }
	    // Si placa == this.placa, el método no hace nada y termina felizmente.
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
