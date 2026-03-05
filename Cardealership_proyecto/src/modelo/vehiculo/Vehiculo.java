package modelo.vehiculo;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.lang.*;



public abstract class Vehiculo {

	protected static final String LETRAS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Usamos esto para manejar la codificacion de las placas
	protected static final String NUMEROS = "0123456789"; // "		"
	
	protected static ArrayList<String> placasRegistradas = new ArrayList<>(); //Lista de placas para buscar no repetidas // static hace que no se cree una lista vacia para cada vez que instanciamos un auto si no que sea la misma lista
	
	protected String placa;
	protected String marca;
	protected String modelo;
	protected String year;  //Juan Jose: Es mejor ponerlo String No necesitamos hacer operaciones con el año (hacer Excepcion) ya la hago.
	protected float precio; 
	protected String tipoDeCombustible;
	protected String transmision;
	protected float kilometraje;
	protected String color;
	protected String estado;
	protected float cilindraje;
	protected boolean disponible;
	
	
	public Vehiculo(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje,  String color, String estado, float cilindraje, boolean disponible)  {
		super();
		
		setPlaca(placa);																						
		
		if(marca == null||marca.trim().isEmpty()) throw new IllegalArgumentException("Error: La marca no puede ser nula o estar vacia");	// IllegalArgumentException cierra el la creacion del objeto inmediatamente una validacion no se cumple
		
		if(modelo == null || modelo.trim().isEmpty()) throw new IllegalArgumentException("Error: La marca no puede ser nula ni estar vacia");
		
		if(year < 2015 || year > 2027) throw new IllegalArgumentException("Error el año del vehiculo debe ser mayor al 2015 y menor al 2027");
		
		if(precio <= 0) throw new IllegalArgumentException("El precio no puede ser 0");
		
		if(kilometraje < 0 ) throw new IllegalArgumentException("El kilometraje no puede ser negativo");
		
		this.tipoDeCombustible = validarCombustible(tipoDeCombustible);
		
		this.transmision = validarTransmision(transmision); 	
		
		if(cilindraje < 50) throw new IllegalArgumentException("Cilindraje minimo 50cc");
		
		
		//Faltaria hacer el metodode IsDisponible
		// Despues de que pase todas las validaciones asignamos los valores a las variables es mejor manejar las validaciones asi para que si algo pasa instanciando el objeto no se instancie.
		//Psdt:Vale no hay problema que manejes IllegalArgumentException sin Throws, ya consulte y de hecho es mas eficiente usarlo sin el Throws 
		
		this.marca = marca;
		this.modelo = modelo;
		this.year = String.valueOf(year);
		this.precio = precio;
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


	public String getYear() {
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
	
	
	public char getRandom(String fuente) {							
		return fuente.charAt(ThreadLocalRandom.current().nextInt(fuente.length())); 
	}
	
	
	public abstract void setPlaca(String placa); // Hacemos el metodo abstracto para que los hijos tengan que implementar su forma de cambiar placa

	
	public void registrarPlaca(String placa)  {
	    if (placa == null) 														//.trim() quita espacios o tabs al inicio y al final del String, no afecta espacio entre palabras devuelve el string limpio
	    	throw new IllegalArgumentException("La placa no puede ser nula");
	    
	    if (placa.trim().isEmpty()) {
	    	throw new IllegalArgumentException("La placa no puede estar vacía");
	    }
	    
	    if (placasRegistradas.contains(placa)) 
	    	throw new IllegalArgumentException("La placa ya está registrada");
	    
	    placasRegistradas.add(placa);
	}
	
	
	
	
	private String validarCombustible(String combustible) {
        if (combustible == null) throw new IllegalArgumentException("El combustible no puede ser nulo.");
        switch (combustible) {
            case "Gasolina Corriente":
            case "Gasolina Extra":
            case "Diesel":
            case "Electrico":
            case "Gas Natural":
                return combustible;
            default:
                throw new IllegalArgumentException("Combustible no válido: " + combustible);
        }
    }
	
	
	
				// Estos metodos se usan dentro del super en la clase padre, por lo tanto nos beneficia dejarlos private puesto que no necesitamos que los hijos accedan
	
	
	private String validarTransmision(String transmision) {
	    if (transmision == null) throw new IllegalArgumentException("La transmisión no puede ser nula.");

	    
	    String limpio = transmision.trim().toUpperCase()
	            .replace("Á", "A").replace("É", "E").replace("Í", "I") //Usamos replaces para que si en el Switch entra una tilde, la cambie por la letra 
	            .replace("Ó", "O").replace("Ú", "U");					//Si usaramos .equalsignoreCase se repetiria muchas veces por lo cual seria muy ineficiente

	    
	    switch (limpio) {
	        case "MANUAL":
	        case "AUTOMATICA":
	        case "CVT":
	        case "DOBLE EMBRAGUE":
	        case "MANUAL AUTOMATIZADA":
	        case "SECUENCIAL":
	        case "ELECTRONICA VARIABLE": 
	            return limpio; 
	        default:
	            throw new IllegalArgumentException("Transmisión inválida: " + transmision + 
	                ". Opciones: Manual, Automática, CVT, Doble Embrague, Manual Automatizada, Secuencial o Electrónica Variable.");
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
