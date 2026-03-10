package modelo.venta;

import java.util.Date;
import java.util.Arrays;

import modelo.persona.Cliente;
import modelo.persona.Vendedor;
import modelo.vehiculo.Vehiculo;

public class Venta {

    private String codigo;
    private Vehiculo[] vehiculos;
    private Cliente cliente;
    private Vendedor vendedor;
    private Date fecha;
    private float total;

    public Venta(String codigo, Cliente cliente, Vendedor vendedor, Date fecha) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.vehiculos = new Vehiculo[0];
        this.total = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public Vehiculo[] listarVehiculo() {
        return Arrays.copyOf(vehiculos, vehiculos.length);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getTotal() {
        return total;
    }

    public void agregarVehiculo(Vehiculo v) throws InvalidVentaException {

        if (v == null)
            throw new InvalidVentaException("Vehículo nulo");

        if (!v.isDisponible())
            throw new InvalidVentaException("Vehículo no disponible");

        if (buscarVehiculo(v.getPlaca()) != null)
            throw new InvalidVentaException("El vehículo ya está en la venta");

        vehiculos = Arrays.copyOf(vehiculos, vehiculos.length + 1);
        vehiculos[vehiculos.length - 1] = v;

        v.setDisponible(false);
        calcularTotal();
    }

    public boolean eliminarVehiculo(String placa) throws InvalidVentaException {

        if (placa == null || placa.isEmpty())
            throw new InvalidVentaException("Placa inválida");

        int i = 0;

        while (i < vehiculos.length) {

            if (vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {

                vehiculos[i].setDisponible(true);

                int j = i;

                while (j < vehiculos.length - 1) {
                    vehiculos[j] = vehiculos[j + 1];
                    j++;
                }

                vehiculos = Arrays.copyOf(vehiculos, vehiculos.length - 1);

                calcularTotal();
                return true;
            }

            i++;
        }

        return false;
    }

    public Vehiculo buscarVehiculo(String placa) throws InvalidVentaException {

        if (placa == null || placa.isEmpty())
            throw new InvalidVentaException("Placa inválida");

        int i = 0;

        while (i < vehiculos.length) {

            if (vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
                return vehiculos[i];
            }

            i++;
        }

        return null;
    }

    public void calcularTotal() {

        total = 0;

        int i = 0;

        while (i < vehiculos.length) {
            total += vehiculos[i].getPrecio();
            i++;
        }
    }

    public Factura generarFactura(String numeroFactura, String metodoPago)
            throws InvalidFacturaException {

        if (vehiculos.length == 0)
            throw new InvalidFacturaException("No se puede facturar una venta vacía");

        return new Factura(numeroFactura, this, metodoPago);
    }

    @Override
    public String toString() {

        String listaVehiculos = "";

        for (Vehiculo v : vehiculos) {
            listaVehiculos += "   - " + v.getPlaca() + " | "
                            + v.getMarca() + " "
                            + v.getModelo() + " | $"
                            + v.getPrecio() + "\n";
        }

        return "\n=========== VENTA " + codigo + " ===========\n"
             + "Fecha: " + fecha + "\n"
             + "\nCliente:\n"
             + "   " + cliente.getNombre() + " " + cliente.getApellido()
             + " (" + cliente.getId() + ")\n"
             + "\nVendedor:\n"
             + "   " + vendedor.getNombre() + " " + vendedor.getApellido()
             + " (" + vendedor.getId() + ")\n"
             + "\nVehículos vendidos:\n"
             + listaVehiculos
             + "\nTOTAL: $" + total
             + "\n=================================\n";
    }
}
