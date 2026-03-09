package utils;
import java.io.*;
import java.util.Arrays;

import modelo.vehiculo.excepciones.EObjectExiste;
import modelo.vehiculo.excepciones.EObjectInvalido;
import modelo.vehiculo.excepciones.EObjectNull;
import modelo.vehiculo.excepciones.EObjectVoid;

public class Utils {

    public static final String baseDireccion = "src/ficheros/";

  
    public static void guardarObjeto(Object objeto) {

        String ruta = rutaObjeto(objeto);

        if (ruta == null) {
            System.out.println("Tipo no soportado");
            return;
        }

        try {

            FileWriter writer = new FileWriter(ruta, true);
            writer.write(objeto.toString() + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static String[] leerObjeto(String tipo) {

        String ruta = rutaTipoObjeto(tipo);

        String datos[] = new String[0];

        try {

            BufferedReader reader = new BufferedReader(new FileReader(ruta));

            String linea;

            while ((linea = reader.readLine()) != null) {

                String nuevo[] = new String[datos.length + 1];

                int i = 0;

                while (i < datos.length) {
                    nuevo[i] = datos[i];
                    i++;
                }

                nuevo[datos.length] = linea;

                datos = nuevo;
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return datos;
    }
    

    public static void eliminarObjeto(String tipo, String identificador) {

        String ruta = rutaTipoObjeto(tipo);

        String datos[] = leerObjeto(tipo);

        String nuevos[] = new String[0];

        int i = 0;

        while (i < datos.length) {

            if (!datos[i].contains(identificador)) {

                String temp[] = new String[nuevos.length + 1];

                int j = 0;

                while (j < nuevos.length) {
                    temp[j] = nuevos[j];
                    j++;
                }

                temp[nuevos.length] = datos[i];

                nuevos = temp;
            }

            i++;
        }

        try {

            FileWriter writer = new FileWriter(ruta);

            int k = 0;

            while (k < nuevos.length) {

                writer.write(nuevos[k] + "\n");
                k++;

            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String rutaObjeto(Object objeto) {

        switch (objeto.getClass().getSimpleName()) {

            case "Cliente":
                return baseDireccion + "clientes.cli";

            case "Empleado":
                return baseDireccion + "empleados.emp";

            case "Auto":
            case "Moto":
                return baseDireccion + "vehiculos.veh";

            default:
                return null;
        }
    }

    private static String rutaTipoObjeto(String tipo) {

        switch (tipo.toLowerCase()) {

            case "cliente":
                return baseDireccion + "clientes.cli";

            case "empleado":
                return baseDireccion + "empleados.emp";

            case "vehiculo":
                return baseDireccion + "vehiculos.veh";

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
	}
    
    
    
    
    
    
    
    
    
    
    
    
}