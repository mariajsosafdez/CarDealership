package modelo.persona;

public abstract class Persona {

    protected String tipoDocumento;
    protected String numeroDocumento;
    protected String nombre;
    protected String apellido;
    protected String telefono;

   
    public Persona(String tipoDocumento, String numeroDocumento, String nombre, String apellido, String telefono) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) throws ValidacionException {
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            throw new ValidacionException("El tipo de documento no puede estar vacío.");
        }
        this.tipoDocumento = tipoDocumento;
    }

    public String getId() {
        return numeroDocumento;
    }

    public void setId(String id) throws ValidacionException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidacionException("El número de documento no puede estar vacío.");
        }
        try {
            long num = Long.parseLong(id.trim());
            if (num <= 0) {
                throw new ValidacionException("El número de documento debe ser un número positivo.");
            }
        } catch (NumberFormatException e) {
            throw new ValidacionException("El número de documento debe contener solo dígitos.");
        }
        this.numeroDocumento = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ValidacionException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws ValidacionException {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ValidacionException("El apellido no puede estar vacío.");
        }
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ValidacionException {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new ValidacionException("El teléfono no puede estar vacío.");
        }

    @Override
    public String toString() {
        return "Persona{" +
                "tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}