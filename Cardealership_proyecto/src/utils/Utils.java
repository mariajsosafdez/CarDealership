package utils;
import java.io.*;

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
}