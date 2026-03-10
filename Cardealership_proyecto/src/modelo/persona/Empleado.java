package modelo.persona;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable{
	private static final long serialVersionUID = 4;
    
    private float salario;

    public Empleado(String tipoDocumento, String numeroDocumento, String nombre, String apellido, String telefono, float salario) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, telefono);
        setSalario(salario); 
    }

   
    public float getSalario() {
        return salario;
    }

   
    public void setSalario(float salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        this.salario = salario;
    }

    // toString
    @Override
    public String toString() {
        return "Empleado{" +
                "tipoDoc='" + tipoDocumento + '\'' +
                ", numDoc='" + numeroDocumento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", salario=" + salario +
                '}';
    }
}