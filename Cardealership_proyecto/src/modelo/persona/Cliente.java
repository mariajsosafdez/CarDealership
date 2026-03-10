package modelo.persona;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable{
	private static final long serialVersionUID = 3;


    
    private String email;

    
    public Cliente(String tipoDocumento, String numeroDocumento, String nombre, String apellido, String telefono, String email) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, telefono);
        this.email = email;
    }

  
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        this.email = email;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}