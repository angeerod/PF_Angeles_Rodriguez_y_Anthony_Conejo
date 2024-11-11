import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarCliente extends JFrame {

    // Campos de texto para ingresar datos del cliente
    private JTextField txtCedulaActual, txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;

    public ActualizarCliente() {
        // Configuración de la ventana
        setTitle("Actualizar Cliente");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233 ));
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos a actualizar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Campo de cédula actual
        JLabel lblCedulaActual = new JLabel("Cédula Actual:");
        lblCedulaActual.setBounds(50, 50, 150, 25);
        add(lblCedulaActual);
        txtCedulaActual = new JTextField();
        txtCedulaActual.setBounds(200, 50, 150, 25);
        txtCedulaActual.setBackground(new Color(199, 235, 255));
        add(txtCedulaActual);

        // Crear y añadir etiquetas y campos de texto con coordenadas personalizadas
        JLabel lblCedula = new JLabel("Nueva Cédula:");
        lblCedula.setBounds(50, 90, 150, 25);
        add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(200, 90, 150, 25);
        txtCedula.setBackground(new Color(199, 235, 255));
        add(txtCedula);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(50, 130, 150, 25);
        add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(200, 130, 150, 25);
        txtNombre1.setBackground(new Color(199, 235, 255));
        add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(50, 170, 150, 25);
        add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(200, 170, 150, 25);
        txtNombre2.setBackground(new Color(199, 235, 255));
        add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(50, 210, 150, 25);
        add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(200, 210, 150, 25);
        txtApellido1.setBackground(new Color(199, 235, 255));
        add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(50, 250, 150, 25);
        add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(200, 250, 150, 25);
        txtApellido2.setBackground(new Color(199, 235, 255));
        add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 290, 150, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(200, 290, 150, 25);
        txtTelefono.setBackground(new Color(199, 235, 255));
        add(txtTelefono);

        // Botón para actualizar el cliente
        JButton btnActualizar = new JButton("Actualizar Cliente");
        btnActualizar.setBounds(200, 330, 150, 30);
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });
        add(btnActualizar);

        // Botón de regresar al menú principal
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(50, 330, 100, 30);
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    // Método para actualizar el cliente llamando al procedimiento almacenado
    private void actualizarCliente() {
        String cedulaActual = txtCedulaActual.getText();
        String cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();
        String telefono = txtTelefono.getText();

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL ActualizarCliente(?, ?, ?, ?, ?, ?, ?)}";
            statement = connection.prepareCall(query);

            // Establecer los parámetros
            statement.setString(1, cedulaActual); // Cédula actual para identificar al cliente
            statement.setString(2, cedula);       // Nueva cédula
            statement.setString(3, nombre1);
            statement.setString(4, nombre2);
            statement.setString(5, apellido1);
            statement.setString(6, apellido2);
            statement.setString(7, telefono);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");

            // Limpiar los campos después de la actualización
            limpiarCampos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar los recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtCedulaActual.setText("");
        txtCedula.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtTelefono.setText("");
    }

    // Método para regresar al menú principal
    private void regresarAlMenu() {
        MenuActualizar menuActualizar = new MenuActualizar();
        menuActualizar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarCliente app = new ActualizarCliente();
            app.setVisible(true);
        });
    }
}
