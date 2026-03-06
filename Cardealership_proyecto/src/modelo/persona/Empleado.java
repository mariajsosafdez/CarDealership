package modelo.persona;

public class Empleado extends Persona {
    
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
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", salario=" + salario +
                '}';
    }
}