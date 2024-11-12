import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
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
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Ingrese la cédula del cliente a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(20, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Etiqueta y campo de texto para ingresar la cédula del cliente
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(60, 50, 100, 30);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(160, 50, 150, 30);
        txtCedula.setBackground(new Color(199, 235, 255)); 
        txtCedula.setToolTipText("Ingrese la cédula del cliente que desea eliminar."); // Tooltip
        add(txtCedula);

        // Botón para eliminar el cliente
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBounds(160, 100, 150, 30);
        btnEliminar.setToolTipText("Haz clic para eliminar al cliente con la cédula ingresada."); // Tooltip
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
        add(btnEliminar);

        // Botón para regresar al menú eliminar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 100, 100, 30);
        btnRegresar.setToolTipText("Haz clic para regresar al menú de eliminación."); // Tooltip
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuEliminar(); // Llamar al método para regresar
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

    private void regresarAlMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la instancia del menú Eliminar
        menuEliminar.setVisible(true); // Hacer visible el menú Eliminar
        this.dispose(); // Cerrar la ventana de eliminación
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarCliente eliminarCliente = new EliminarCliente();
            eliminarCliente.setVisible(true);
        });
    }
}
