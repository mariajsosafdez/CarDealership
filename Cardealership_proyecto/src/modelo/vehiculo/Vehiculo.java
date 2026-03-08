package modelo.vehiculo;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import modelo.vehiculo.excepciones.*;

public abstract class Vehiculo {

	protected static final String LETRAS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Usamos esto para manejar la codificacion de las placas
	protected static final String NUMEROS = "0123456789"; // "		"
	
	protected static String[] placasRegistradas = new String[0]; // static hace que no se cree una lista vacia para cada vez que instanciamos un auto si no que sea la misma lista
	
	protected String placa; //
	protected String marca; //
	protected String modelo; //
	protected String year;  //Juan Jose: Es mejor ponerlo String No necesitamos hacer operaciones con el año (hacer Excepcion) ya la hago.
	protected float precio; //
	protected String tipoDeCombustible; //
	protected String transmision; //
	protected float kilometraje; //
	protected String color; //
	protected String estado; //
	protected float cilindraje; //
	protected boolean disponible; //
	
	
	public Vehiculo(String placa, String marca, String modelo, int year, float precio, String tipoDeCombustible,
			String transmision, float kilometraje,  String color, String estado, float cilindraje, boolean disponible)  {
		super();
		
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.year = String.valueOf(year);
		this.precio = precio;
		this.kilometraje = kilometraje;
		this.color = color;
		this.cilindraje = cilindraje;
		this.disponible = disponible;
		this.tipoDeCombustible = tipoDeCombustible;
		this.transmision = transmision;
		this.estado = estado;
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
	
	
	public char getRandom(String fuente) {							
		return fuente.charAt(ThreadLocalRandom.current().nextInt(fuente.length())); 
	}
	
	
	public abstract void setPlaca(String placa) throws EObjectInvalido,EObjectNull,EObjectExiste, EObjectVoid; // Hacemos el metodo abstracto para que los hijos tengan que implementar su forma de cambiar placa
	
	
	protected boolean existePlaca(String placa) {
	    int i = 0;
	    while (i < placasRegistradas.length) {
	        if (placasRegistradas[i] != null && placasRegistradas[i].equals(placa)) {
	            return true; // Se encontró la placa
	        }
	        i++;
	    }
	    return false; // No se encontro
	}

	
	public void registrarPlaca(String placa)  throws EObjectNull, EObjectVoid, EObjectExiste{ 
	    if (placa == null) 										//.trim() quita espacios o tabs al inicio y al final del String, no afecta espacio entre palabras devuelve el string limpio
	    	throw new EObjectNull("La placa no puede ser nula");
	    
	    if (placa.trim().isEmpty()) {
	    	throw new EObjectVoid("La placa no puede estar vacía");
	    }
	    
	    int i = 0;
	   while( i < placasRegistradas.length) {
		   if(placasRegistradas[i].equalsIgnoreCase(placa)) {
			   throw new EObjectExiste("La placa ya esta registrada");
			   
		   }
		  
		   i++;
	   }
	   
	   placasRegistradas  = Arrays.copyOf(placasRegistradas, placasRegistradas.length + 1);
	   placasRegistradas[placasRegistradas.length-1] = placa;
	   
	   
	}
	
	
	public String validarCombustible(String combustible) throws EObjectNull, EObjectInvalido {
        if (combustible == null) throw new EObjectNull("El combustible no puede ser nulo.");
        
        String limpio = combustible.trim().toUpperCase()
	            .replace("Á", "A").replace("É", "E").replace("Í", "I") 
	            .replace("Ó", "O").replace("Ú", "U");
        
        switch (limpio) {
            case "GASOLINA CORRIENTE":
            case "GASOLINA EXTRA":
            case "DIESEL":
            case "ELECTRICO":
            case "GAS NATURAL":
                return combustible;
            default:
                throw new  EObjectInvalido("Combustible no válido: " + combustible);
        }
    }
	
		// Como estos metodos los vamos a usar desde la clase concesionario deben ser public
	
	
	public String validarTransmision(String transmision)throws EObjectNull, EObjectInvalido {
	    if (transmision == null) throw new EObjectNull("La transmisión no puede ser nula.");

	    
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
	            throw new EObjectInvalido("Transmisión inválida: " + transmision + 
	                ". Opciones: Manual, Automática, CVT, Doble Embrague, Manual Automatizada, Secuencial o Electrónica Variable.");
	    }
	}
	
	public String validarEstado(String estado) throws EObjectNull, EObjectInvalido{
	    if (estado == null || estado.trim().isEmpty()) {
	        throw new EObjectNull("El estado no puede ser nulo");
	    }
	 
	    String limpio = estado.trim().toUpperCase()
	            .replace("Á", "A").replace("É", "E").replace("Í", "I") 
	            .replace("Ó", "O").replace("Ú", "U");
	    
	    if (limpio.equals("NUEVO") || limpio.equals("USADO")) {
	        return limpio;
	    } else {
	        throw new EObjectInvalido("Estado inválido. Use: 'Nuevo' o 'Usado'");
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
