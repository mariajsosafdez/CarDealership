package vistaVehiculo;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Concesionario;
import modelo.vehiculo.*;
import modelo.vehiculo.excepciones.EObjectExiste;
import modelo.vehiculo.excepciones.EObjectInvalido;
import modelo.vehiculo.excepciones.EObjectNull;
import modelo.vehiculo.excepciones.EObjectVoid;

public class FormMoto extends JPanel {
	private Concesionario concesionario;
	private DefaultTableModel tablaR = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField txtPlaca;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtAño;
	private JTextField txtPrecio;
	private JTextField txtKilometraje;
	private JTextField txtColor;
	private JTextField txtCilindraje;

	// TODO CARGAR LOS VEHICULOS DESDE EL FICHERO
		public void cargarAutos() {
			for (Vehiculo a : concesionario.listarVehiculos()) {
				if (a instanceof Moto) {
					tablaR.addRow(new Object[] { a.getPlaca(), a.getMarca(), a.getModelo(), a.getYear(), a.getPrecio() });
				}

			}
		}
		
	public FormMoto(Concesionario concesionario, DefaultTableModel tablaR) {
		this.concesionario = concesionario;

		setLayout(new BorderLayout(0, 0));

		JPanel fila2 = new JPanel();
		add(fila2, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		fila2.add(btnRegistrar);
		String[] combustibles = { " ","GASOLINA CORRIENTE", "GASOLINA EXTRA", "DIESEL", "ELECTRICO", "GAS NATURAL" };
		String[] transmisiones = { " ","MANUAL", "AUTOMATICA", "CVT", "DOBLE EMBRAGUE", "MANUAL AUTOMATIZADA", "SECUENCIAL",
				"ELECTRONICA VARIABLE" };
		String[] estados = { " ","Nuevo", "Usado" };
		String[] categorias = {" ", "SCOOTER","SPORT","NAKED","ENDURO","TOURING","CRUISER","CROSS"};

		JPanel fila1 = new JPanel();
		add(fila1, BorderLayout.CENTER);
		fila1.setLayout(new GridLayout(0, 4, 5, 5));

		JLabel lblPlaca = new JLabel("Placa");
		fila1.add(lblPlaca);
		txtPlaca = new JTextField();
		fila1.add(txtPlaca);

		JLabel lblMarca = new JLabel("Marca");
		fila1.add(lblMarca);
		txtMarca = new JTextField();
		fila1.add(txtMarca);

		JLabel lblModelo = new JLabel("Modelo");
		fila1.add(lblModelo);
		txtModelo = new JTextField();
		fila1.add(txtModelo);

		JLabel lblAño = new JLabel("Año");
		fila1.add(lblAño);
		txtAño = new JTextField();
		fila1.add(txtAño);

		JLabel lblPrecio = new JLabel("Precio");
		fila1.add(lblPrecio);
		txtPrecio = new JTextField();
		fila1.add(txtPrecio);

		JLabel lblTipoCombustible = new JLabel("Tipo de Combustible");
		fila1.add(lblTipoCombustible);
		JComboBox tipoCombustible = new JComboBox(combustibles);
		fila1.add(tipoCombustible);

		JLabel lblTransmision = new JLabel("Transmisión");
		fila1.add(lblTransmision);
		JComboBox tipoTransmision = new JComboBox(transmisiones);
		fila1.add(tipoTransmision);

		JLabel lblKilometraje = new JLabel("Kilometraje");
		fila1.add(lblKilometraje);
		txtKilometraje = new JTextField();
		fila1.add(txtKilometraje);

		JLabel lblColor = new JLabel("Color");
		fila1.add(lblColor);
		txtColor = new JTextField();
		fila1.add(txtColor);

		JLabel lblEstado = new JLabel("Estado");
		fila1.add(lblEstado);
		JComboBox tipoEstado = new JComboBox(estados);
		fila1.add(tipoEstado);

		JLabel lblCilindraje = new JLabel("Cilindraje");
		fila1.add(lblCilindraje);
		txtCilindraje = new JTextField();
		fila1.add(txtCilindraje);

		JLabel lblCategoria = new JLabel("Categoria");
		fila1.add(lblCategoria);
		JComboBox tipoCategoria = new JComboBox(categorias);
		fila1.add(tipoCategoria);

		// REGISTRAR MOTO
		btnRegistrar.addActionListener(e -> {

			String placa = txtPlaca.getText();
			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();
			int año = -1;
			float precio = -1;
			String combustible = (String) tipoCombustible.getSelectedItem();
			String transmision = (String) tipoTransmision.getSelectedItem();
			float kilometraje = -1;
			String color = txtColor.getText();
			String estado = (String) tipoEstado.getSelectedItem();
			float cilindraje = -1;
			String categoria = (String) tipoCategoria.getSelectedItem();
			
			try {

				año = Integer.parseInt(txtAño.getText());

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, "Ingrese un número válido");

			}
			try {

				precio = Float.parseFloat(txtPrecio.getText());

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, "Ingrese un número válido");

			}
			try {

				kilometraje = Float.parseFloat(txtKilometraje.getText());

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, "Ingrese un número válido");

			}
			try {

				cilindraje = Float.parseFloat(txtCilindraje.getText());

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, "Ingrese un número válido");

			}

			if (!marca.isBlank() && !modelo.isBlank() && año >= 1885 && precio >= 0 && !combustible.isBlank()
					&& !transmision.isBlank() && kilometraje >= 0 && !color.isBlank() && !estado.isBlank() && cilindraje >= 0 && !categoria.isBlank()) {


				try {
					concesionario.registrarMoto(placa, marca, modelo, año, precio, (String) tipoCombustible.getSelectedItem(), (String) tipoTransmision.getSelectedItem(), kilometraje, color, (String) tipoEstado.getSelectedItem(), cilindraje, true, categoria);
					Vehiculo m = concesionario.buscarVehiculos(placa);
					tablaR.addRow(new Object[] { placa, marca, modelo, año, precio, m });
				} catch (EObjectNull | EObjectInvalido | EObjectVoid | EObjectExiste ex) {
					JOptionPane.showMessageDialog(this, "No se pudo registrar el Auto");
					ex.getMessage();
				}

				txtPlaca.setText("");
				txtMarca.setText("");
				txtModelo.setText("");
				txtAño.setText("");
				txtPrecio.setText("");
				tipoCombustible.setSelectedIndex(0);
				tipoTransmision.setSelectedIndex(0);
				txtKilometraje.setText("");
				txtColor.setText("");
				tipoEstado.setSelectedIndex(0);
				txtCilindraje.setText("");
				tipoCategoria.setSelectedIndex(0);

			} else {
				JOptionPane.showMessageDialog(this, "Complete todos los campos");
			}
		});

	}
}
