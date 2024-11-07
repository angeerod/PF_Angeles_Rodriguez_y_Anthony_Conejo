import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarCargo extends JFrame {
    private JTextField txtIdCargo;

    public EliminarCargo() {
        // Configuración de la ventana
        setTitle("Eliminar Cargo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta y campo de texto para ingresar el ID del cargo
        JLabel lblIdCargo = new JLabel("ID del Cargo:");
        lblIdCargo.setBounds(50, 40, 100, 30);
        add(lblIdCargo);

        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(150, 40, 200, 30);
        add(txtIdCargo);

        // Botón para eliminar el cargo
        JButton btnEliminar = new JButton("Eliminar Cargo");
        btnEliminar.setBounds(125, 90, 150, 30);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCargo();
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

    // Método para eliminar el cargo llamando al procedimiento almacenado
    private void eliminarCargo() {
        String idCargo = txtIdCargo.getText();

        if (idCargo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del cargo.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarCargo(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, idCargo);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cargo eliminado exitosamente.");

            // Limpiar el campo después de la eliminación
            txtIdCargo.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cargo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            EliminarCargo eliminarCargo = new EliminarCargo();
            eliminarCargo.setVisible(true);
        });
    }
}
 