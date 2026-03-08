package modelo.persona;


import modelo.venta.Venta;

public class Vendedor extends Empleado {

    private int vehiculosVendidos;

    public Vendedor(String tipoDocumento, String numeroDocumento, String nombre, String apellido, String telefono, float salario) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, telefono, salario);
        this.vehiculosVendidos = 0;
    }

    public int getVehiculosVendidos() {
        return vehiculosVendidos;
    }

    public void setVehiculosVendidos(int vehiculosVendidos) throws ValidacionException {
        if (vehiculosVendidos < 0) {
            throw new ValidacionException("El número de vehículos vendidos no puede ser negativo.");
        }
        this.vehiculosVendidos = vehiculosVendidos;
    }

    public void registrarVenta(Venta venta) throws ValidacionException {
        if (venta == null) {
            throw new ValidacionException("La venta no puede ser nula.");
        }
        this.vehiculosVendidos++;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", salario=" + getSalario() +
                ", vehiculosVendidos=" + vehiculosVendidos +
                '}';
    }
}