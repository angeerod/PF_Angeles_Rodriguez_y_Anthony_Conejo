import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarClientes extends JFrame {

    public ConsultarClientes() {
        // Configuración de la ventana
        setTitle("Lista de Clientes");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(151, 204, 233 ));

        // Configurar el modelo de la tabla
        String[] columnNames = {"Cédula", "Nombre1", "Nombre2", "Apellido1", "Apellido2", "Teléfono"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        JButton btnRegresar = new JButton("Regresar");
        panelBoton.add(btnRegresar);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón "Regresar" para regresar al MenuVisualizar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuVisualizar(); // Llamar al método que abrirá el MenuVisualizar
            }
        });

        // Conexión y ejecución del procedimiento almacenado
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701");
             CallableStatement statement = connection.prepareCall("{CALL ConsultarClientes()}")) {

            ResultSet resultSet = statement.executeQuery();

            // Añadir cada registro al modelo de la tabla
            while (resultSet.next()) {
                String cedula = resultSet.getString("Cedula");
                String nombre1 = resultSet.getString("Nombre1");
                String nombre2 = resultSet.getString("Nombre2");
                String apellido1 = resultSet.getString("Apellido1");
                String apellido2 = resultSet.getString("Apellido2");
                String telefono = resultSet.getString("Telefono");

                tableModel.addRow(new Object[]{cedula, nombre1, nombre2, apellido1, apellido2, telefono});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     // Método para regresar al MenuVisualizar
     private void regresarAlMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true); // Mostrar MenuVisualizar
        this.dispose(); // Cerrar la ventana actual (VentanaClientes)
    }
}
