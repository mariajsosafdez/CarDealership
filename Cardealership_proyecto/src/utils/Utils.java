package utils;
import java.io.*;
import java.util.Arrays;

import modelo.vehiculo.excepciones.EObjectExiste;
import modelo.vehiculo.excepciones.EObjectInvalido;
import modelo.vehiculo.excepciones.EObjectNull;
import modelo.vehiculo.excepciones.EObjectVoid;

public class Utils {

	public static final String baseDireccion = "src/utils/ficheros/";
    
    // Guarda el objeto en su archivo correspondiente
    public static void guardarObjeto(Object objeto) {
    	
        String ruta = rutaObjeto(objeto);
        if (ruta == null) {
            System.out.println("Tipo no soportado");
            return;
        }
        
        //Crea la carpeta si no existe
        File archivo = new File(ruta);

        if (archivo.getParentFile() != null && !archivo.getParentFile().exists()) {
            archivo.getParentFile().mkdirs();
        }
        
        // Lee los objetos existentes (si los hay)
        Object[] existentes = leerObjetos(ruta);

        Object[] nuevoArray = new Object[existentes.length + 1];

        for (int i = 0; i < existentes.length; i++) {
            nuevoArray[i] = existentes[i];
        }
        
        // Agrega un nuevo objeto
        nuevoArray[existentes.length] = objeto;
        
        // Guarda todo en un array
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(nuevoArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Lee los objetos del archivo
    public static Object[] leerObjetos(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists() || archivo.length() == 0) {
            return new Object[0];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Object[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Object[0];
        }
    }
    
    // Elimina los objeto por identificador (ID, placa, etc...)
    public static void eliminarObjeto(String tipo, String identificador) {

        String ruta = rutaTipoObjeto(tipo);

        Object[] datos = leerObjetos(ruta);

        int cont = 0;

        for (int i = 0; i < datos.length; i++) {
            if (!datos[i].toString().contains(identificador)) {
                cont++;
            }
        }

        Object[] nuevos = new Object[cont];

        int j = 0;

        for (int i = 0; i < datos.length; i++) {
            if (!datos[i].toString().contains(identificador)) {
                nuevos[j] = datos[i];
                j++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(nuevos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Determina la ruta en la que se va a guardar el objeto
    private static String rutaObjeto(Object objeto) {
        return rutaTipoObjeto(objeto.getClass().getSimpleName());
    }
    
  //Guarda el objeto segun si es cliente, empleado, etc...
    private static String rutaTipoObjeto(String tipo) {

        switch (tipo.toLowerCase()) {

            case "cliente":
                return baseDireccion + "clientes.cli";

            case "empleado":
                return baseDireccion + "empleados.emp";

            case "auto":
            case "moto":
                return baseDireccion + "vehiculos.veh";

            case "venta":
                return baseDireccion + "ventas.vta";

            default:
                return null;
        }
    }
    
    
    public static void validarDatosGenerales(String marca, String modelo, int year, float precio, float kilometraje,
			float cilindraje) throws EObjectNull, EObjectInvalido {
		if (marca == null || marca.trim().isEmpty())
			throw new EObjectNull("La marca es obligatoria.");
		if (modelo == null || modelo.trim().isEmpty())
			throw new EObjectNull("El modelo es obligatorio.");

		if (year < 2014 || year > 2027)
			throw new EObjectInvalido("Año fuera de rango (2014-2027).");

		if (precio <= 0)
			throw new EObjectInvalido("El precio debe ser un valor positivo.");
		if (kilometraje < 0)
			throw new EObjectInvalido("El kilometraje no puede ser negativo.");
		if (cilindraje <= 0)
			throw new EObjectInvalido("El cilindraje debe ser un valor positivo.");
	}

	public static String validarCarroceria(String c) throws EObjectNull, EObjectInvalido {
		if (c == null || c.trim().isEmpty())
			throw new EObjectNull("La carrocería es obligatoria.");
		String limpio = c.trim().toUpperCase();
		switch (limpio) {
		case "SEDAN":
		case "HATCHBACK":
		case "SUV":
		case "PICKUP":
		case "COUPE":
		case "CONVERTIBLE":
		case "FURGON":
			return limpio;
		default:
			throw new EObjectInvalido("Carrocería '" + c
					+ "' inválida. Opciones: Sedan, Hatchback, SUV, Pickup, Coupe, Convertible o Furgon.");
		}

	}

	public static String validarCategoria(String categoria) throws EObjectNull, EObjectInvalido {
		if (categoria == null || categoria.trim().isEmpty())
			throw new EObjectNull("La categoría de moto es obligatoria.");
		String limpio = categoria.trim().toUpperCase();
		switch (limpio) {
		case "SCOOTER":
		case "SPORT":
		case "NAKED":
		case "ENDURO":
		case "TOURING":
		case "CRUISER":
		case "CROSS":
			return limpio;
		default:
			throw new EObjectInvalido("Categoría '" + categoria
					+ "' inválida. Opciones: Scooter, Sport, Naked, Enduro, Touring, Cruiser o Cross.");
		}
	}
	
	
	
	
	public  static String validarCombustible(String combustible) throws EObjectNull, EObjectInvalido {
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
	
		// 
	
	
	public static String validarTransmision(String transmision)throws EObjectNull, EObjectInvalido {
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
	
	public  static String validarEstado(String estado) throws EObjectNull, EObjectInvalido{
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
			
			public boolean existeDocumento(String numeroDocumento) {
    
    for (int i = 0; i < clientes.length; i++) {
        if (clientes[i].getNumeroDocumento().equalsIgnoreCase(numeroDocumento)) {
            return true;
        }
    }
    
    for (int i = 0; i < empleados.length; i++) {
        if (empleados[i].getNumeroDocumento().equalsIgnoreCase(numeroDocumento)) {
            return true;
        }
    }
    
    return false;
	}
    
    
    
    
    
    
    
    
    
    
    
    
}
