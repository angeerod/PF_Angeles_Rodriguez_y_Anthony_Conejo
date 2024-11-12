import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
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
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Ingrese el ID del cargo a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(20, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Etiqueta y campo de texto para ingresar el ID del cargo
        JLabel lblIdCargo = new JLabel("ID del Cargo:");
        lblIdCargo.setBounds(50, 50, 100, 30);
        add(lblIdCargo);

        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(160, 50, 150, 30);
        txtIdCargo.setBackground(new Color(199, 235, 255)); 
        txtIdCargo.setToolTipText("Ingrese el ID del cargo que desea eliminar."); // Tooltip
        add(txtIdCargo);

        // Botón para eliminar el cargo
        JButton btnEliminar = new JButton("Eliminar Cargo");
        btnEliminar.setBounds(160, 100, 150, 30);
        btnEliminar.setToolTipText("Haz clic para eliminar el cargo con el ID ingresado."); // Tooltip
        btnEliminar.setBackground(new Color(203, 236, 255));
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCargo();
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
                regresarAlMenuEliminar(); // Regresar al menú eliminar
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

    // Método para regresar al menú eliminar
    private void regresarAlMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la instancia del menú Eliminar
        menuEliminar.setVisible(true); // Hacer visible el menú Eliminar
        this.dispose(); // Cerrar la ventana de eliminación
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarCargo eliminarCargo = new EliminarCargo();
            eliminarCargo.setVisible(true);
        });
    }
}
