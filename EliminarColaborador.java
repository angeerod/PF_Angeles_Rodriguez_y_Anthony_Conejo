import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EliminarColaborador extends JFrame {

    // Campo de texto para ingresar el ID del colaborador
    private JTextField txtIdColaborador;

    public EliminarColaborador() {
        // Configuración de la ventana
        setTitle("Eliminar Colaborador");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta y campo de texto para el ID del colaborador
        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(50, 50, 120, 30);
        add(lblIdColaborador);

        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(180, 50, 150, 30);
        add(txtIdColaborador);

        // Botón para eliminar el colaborador
        JButton btnEliminar = new JButton("Eliminar Colaborador");
        btnEliminar.setBounds(100, 120, 200, 30);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarColaborador();
            }
        });
        add(btnEliminar);

        // Botón para regresar al menú de eliminar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(150, 170, 100, 30);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuEliminar();
            }
        });
        add(btnRegresar);
    }

    // Método para eliminar el colaborador llamando al procedimiento almacenado
    private void eliminarColaborador() {
        String idColaborador = txtIdColaborador.getText();

        if (idColaborador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del colaborador.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "tu_contraseña");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarColaborador(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro del ID del colaborador
            statement.setString(1, idColaborador);

            // Ejecutar el procedimiento almacenado
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Colaborador eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un colaborador con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Limpiar el campo de texto
            txtIdColaborador.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el colaborador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Método para regresar al menú de eliminar
    private void regresarAlMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar();
        menuEliminar.setVisible(true);
        this.setVisible(false); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarColaborador app = new EliminarColaborador();
            app.setVisible(true);
        });
    }
}
