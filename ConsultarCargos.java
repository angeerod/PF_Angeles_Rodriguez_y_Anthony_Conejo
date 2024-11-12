import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarCargos extends JFrame {

    public ConsultarCargos() {
        // Configuración de la ventana
        setTitle("Lista de Cargos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes
        getContentPane().setBackground(new Color(151, 204, 233));

        // Configurar el modelo de la tabla
        String[] columnNames = {"Id Cargo", "Nombre", "Salario"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Añadir la tabla a la ventana

        // Crear el panel para el botón "Regresar"
        JPanel panelBoton = new JPanel();
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setToolTipText("Haz clic para regresar al menú de consultas.");
        btnRegresar.setBackground(new Color(233, 149, 149));
        panelBoton.add(btnRegresar);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón "Regresar" para regresar al MenuVisualizar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuVisualizar(); // Llamar al método que abrirá el MenuVisualizar
            }
        });

        // Cargar los cargos en la tabla
        cargarCargos(tableModel);
    }

    // Método para cargar los cargos en la tabla
    private void cargarCargos(DefaultTableModel tableModel) {
        // Usar try-with-resources para manejar la conexión y los recursos de manera eficiente
        String query = "CALL ConsultarCargos();";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Añadir los resultados a la tabla
            while (resultSet.next()) {
                Object[] row = new Object[3];
                row[0] = resultSet.getInt("Id_Cargo");
                row[1] = resultSet.getString("Nombre");
                row[2] = resultSet.getDouble("Salario");
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los cargos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para regresar al menú Visualizar
    private void regresarAlMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true);
        this.dispose(); // Cerrar la ventana de cargos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarCargos ventana = new ConsultarCargos();
            ventana.setVisible(true);
        });
    }
}
