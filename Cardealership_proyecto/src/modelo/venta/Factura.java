package modelo.venta;

import modelo.persona.Cliente;

public class Factura {

    private String numeroFactura;
    private Venta venta;
    private String metodoPago;

    public Factura(String numeroFactura, Venta venta, String metodoPago)
            throws InvalidFacturaException {

        if (numeroFactura == null || numeroFactura.isEmpty())
            throw new InvalidFacturaException("Número de factura inválido");

        if (venta == null)
            throw new InvalidFacturaException("Venta inválida");

        if (metodoPago == null || metodoPago.isEmpty())
            throw new InvalidFacturaException("Método de pago inválido");

        if (venta.getTotal() <= 0)
            throw new InvalidFacturaException("La venta no tiene un total válido");

        this.numeroFactura = numeroFactura;
        this.venta = venta;
        this.metodoPago = metodoPago;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public Venta getVenta() {
        return venta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String generarFactura() {

        Cliente cliente = venta.getCliente();

        return "Factura N° " + numeroFactura +
                "\nCliente: " + cliente +
                "\nTotal: $" + venta.getTotal() +
                "\nMétodo de pago: " + metodoPago;
    }

    public boolean procesarPago() {

        return venta.getTotal() > 0;
    }

    @Override
    public String toString() {

        return "Factura " + numeroFactura +
                " - Total: $" + venta.getTotal();
    }
}

