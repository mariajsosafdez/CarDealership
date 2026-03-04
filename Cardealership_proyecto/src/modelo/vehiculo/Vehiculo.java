package modelo.vehiculo;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;


//protected a los que pueden cambiar(deje placa por el metodo que estaba en el setplaca) y private final a los atributos que se asignan una única vez

public abstract class Vehiculo {

	protected static final String LETRAS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Usamos esto para manejar la codificacion de las placas
	protected static final String NUMEROS = "0123456789"; // "		"
	
	protected static ArrayList<String> placasRegistradas = new ArrayList<>(); //Lista de placas para buscar no repetidas
	
	protected String placa;
	protected String marca;
	protected String modelo;
	protected int year; //int para recibir unicamente el año numérico sin letras
	protected float precio; //las clases no acceden directamente, obliga a utilizarlas a travez del setter
	protected String tipoDeCombustible;
	protected String transmision;
	protected float kilometraje;
	protected String color;
	protected String estado;
	protected float cilindraje;
	protected boolean disponible;
	
	
	public Vehiculo(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje,  String color, String estado, float cilindraje, boolean disponible) throws Exception {
		super();
		
		setPlaca(placa);
		this.marca = validarMarca(marca);
	    this.modelo = validarModelo(modelo);
		this.year = validarYear(year); 
		setPrecio(precio);
		this.tipoDeCombustible = validarCombustible(tipoDeCombustible);
		this.transmision = validarTransmision(transmision);//hasta aquí tienen sus excepciones
		
		this.kilometraje = kilometraje;
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


	public int getYear() {
		return year;
	}
	
	
	protected float getPrecio() {  
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
	
	
	public abstract void setPlaca(String placa) throws Exception; // Hacemos el metodo abstracto para que los hijos tengan que implementar su forma de cambiar placa

	
	protected void registrarPlaca(String placa) throws Exception {
	    if (placa == null) //.trim() quita espacios o tabs al inicio y al final del String, no afecta espacio entre palabras
	    	throw new IllegalArgumentException("La placa no puede ser nula");
	    
	    if (placa.trim().isEmpty()) {
	    	throw new IllegalArgumentException("La placa no puede estar vacía");
	    }
	    
	    if (placasRegistradas.contains(placa)) 
	    	throw new IllegalArgumentException("La placa ya está registrada");
	    
	    placasRegistradas.add(placa);
	}
	
	
	protected String validarMarca(String marca) throws Exception{
		if (marca == null) {
	        throw new IllegalArgumentException("La marca no puede ser null");
	    }
		
	    if (marca.trim().isEmpty()) {
	        throw new IllegalArgumentException("La marca no puede estar vacía");
	    }
	    
	    if (!marca.matches("[a-zA-Z ]+")) {
	        throw new IllegalArgumentException("La marca solo puede contener letras");
	    }
	    
	    if (marca.length() < 2 || marca.length() > 20) {
	        throw new IllegalArgumentException("La marca debe estar en un rango válido entre 2 y 20 caracteres");
	    }
	    
	    return marca;
	}
	
	protected String validarModelo(String modelo) throws Exception {
		if (modelo == null) {
	        throw new IllegalArgumentException("El modelo no puede ser null");
	    }
		
	    if (modelo.trim().isEmpty()) {
	        throw new IllegalArgumentException("El modelo no puede estar vacío");
	    }
	    
	    if (!modelo.matches("[a-zA-Z0-9\\- ]+")) {
	        throw new IllegalArgumentException("El modelo contiene caracteres inválidos");
	    }
	    
	    if (modelo.length() < 1 || modelo.length() > 50) {
	        throw new IllegalArgumentException("El modelo debe tener máximo 50 caracteres");
	    }
	    
	    return modelo;
	}
	
	
	protected int validarYear(int year) throws Exception {
		if (year < 1000 || year > 9999) {
		    throw new IllegalArgumentException("El año debe tener exactamente 4 dígitos");
		}
		
		if (year < 1886) {
	        throw new IllegalArgumentException("El año no puede ser menor al año del primer vehículo registrado (1886)"); //si se va a admitir cualquier vehículo
	    }
		
		/*if (year < 2025) {
	        throw new IllegalArgumentException("El año no puede ser menor al año 2025"); //si se va a admitir solo vehículos nuevos
	    }*/

	    if (year > 2027) {
	        throw new IllegalArgumentException("El año no puede ser mayor al año del último modelo actual (2027)");
	    }

	    return year;
	}
	
	
	public void setPrecio(float precio) throws Exception {
		if (precio <= 0) {
		    throw new IllegalArgumentException("El precio debe ser mayor que 0");
		}
		
		if (Float.isNaN(precio)) {
		    throw new IllegalArgumentException("El precio no puede ser indeterminado");
		}

		if (Float.isInfinite(precio)) {
		    throw new IllegalArgumentException("El precio no puede ser infinito");
		}

		if (precio > 5_000_000_000f) {
		    throw new IllegalArgumentException("El precio es excesivamente alto");
		}
		this.precio = precio;
	}
	
	protected String validarCombustible(String tipoDeCombustible) throws Exception{
	    if (tipoDeCombustible == null) {
	        throw new IllegalArgumentException("El tipo de combustible no puede ser null");
	    }
	    
	    if (tipoDeCombustible.trim().isEmpty()) {
	    	throw new IllegalArgumentException("El tipo de combustible no puede estar vacío");
	    }
	    
	    if (!tipoDeCombustible.matches("[a-zA-Z\\-]+")) {
	        throw new IllegalArgumentException("El tipo de combustible contiene caracteres inválidos");
	    }
	    
	    String limpio = tipoDeCombustible.trim().toUpperCase() //.trim() quita espacios, .toUpperCase() lo convierte todo a mayúscula 
	    		.replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");//se quitan tíldes para el Switch
	    
	    switch(limpio){
	    	case"GASOLINA":
	    	case"DIESEL":
	    	case"GLP":
	    	case"GNV":
	    	case"BIOCOMBUSTIBLE":
	    	case"ELECTRICO":
	    	case"HIDROGENO":
	    	case"E-FUELS":
	    		return limpio;
	    	default:
	    		throw new IllegalArgumentException("El tipo de combustible es inválido. Las opciones disponibles son: Gasolina, Diesel, GLP (gas licuado de petroleo), "
	    				+ "GNV (gas natural vehicular), Biocombustible, Eletrico, Hidrogeno y E-fuels");
	    }
	}
	
	
	protected String validarTransmision(String transmision) throws Exception{
	    if (transmision == null) {
	        throw new IllegalArgumentException("La transmisión no puede ser null");
	    }
	    
	    if (transmision.trim().isEmpty()) {
	    	throw new IllegalArgumentException("La transmisión no puede estar vacía");
	    }
	    
	    if (!transmision.matches("[a-zA-Z\\ ]+")) {
	        throw new IllegalArgumentException("La transmisión contiene caracteres inválidos");
	    }
	    
	    String limpio = transmision.trim().toUpperCase() //.trim() quita espacios, .toUpperCase() lo convierte todo a mayúscula 
	    		.replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");//se quitan tíldes para el Switch
	    
	    switch(limpio){
	    	case"MANUAL":
	    	case"AUTOMATICA":
	    	case"CVT":
	    	case"DOBLE EMBRAGUE":
	    	case"MANUAL AUTOMATIZADA":
	    	case"SECUENCIAL":
	    	case"ELETRONICA VARIABLE":
	    		return limpio;
	    	default:
	    		throw new IllegalArgumentException("La transmisión es inválida. Las opciones disponibles son: Manual, Automatica, CVT (continuamente variable), "
	    				+ "Doble Embrague (DCT/DSG), Manual Automatizada (AMT) y Electronica Variable (EVT)");
	    }
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
