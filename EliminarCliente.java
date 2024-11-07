import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarCliente extends JFrame {
    private JTextField txtCedula;

    public EliminarCliente() {
        // Configuración de la ventana
        setTitle("Eliminar Cliente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta y campo de texto para ingresar la cédula del cliente
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 40, 100, 30);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(150, 40, 200, 30);
        add(txtCedula);

        // Botón para eliminar el cliente
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBounds(125, 90, 150, 30);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
        add(btnEliminar);

        // Botón para regresar al menú eliminar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(125, 130, 150, 30);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana y vuelve al menú anterior
            }
        });
        add(btnRegresar);
    }

    // Método para eliminar el cliente llamando al procedimiento almacenado
    private void eliminarCliente() {
        String cedula = txtCedula.getText();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la cédula del cliente.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarCliente(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, cedula);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");

            // Limpiar el campo después de la eliminación
            txtCedula.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarCliente eliminarCliente = new EliminarCliente();
            eliminarCliente.setVisible(true);
        });
    }
}
