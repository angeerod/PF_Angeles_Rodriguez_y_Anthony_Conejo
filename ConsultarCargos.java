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

        // Configurar el modelo de la tabla
        String[] columnNames = {"Id Cargo", "Nombre", "Salario"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Añadir la tabla a la ventana

       // Crear el botón "Regresar"
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

        // Cargar los cargos en la tabla
        cargarCargos(tableModel);
    }

    // Método para cargar los cargos en la tabla
    private void cargarCargos(DefaultTableModel tableModel) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "CALL ConsultarCargos();";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

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
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
