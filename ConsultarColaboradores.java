import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarColaboradores extends JFrame {

    public ConsultarColaboradores() {
        // Configuración de la ventana
        setTitle("Lista de Colaboradores");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes

        // Configurar el modelo de la tabla
        String[] columnNames = {"Id Colaborador", "Id Cargo", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2", "Teléfono"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Añadir la tabla a la ventana

        // Crear el botón "Regresar"
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

        // Cargar los colaboradores en la tabla
        cargarColaboradores(tableModel);
    }

    // Método para cargar los colaboradores en la tabla
    private void cargarColaboradores(DefaultTableModel tableModel) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "CALL ConsultarColaboradores();";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Añadir los resultados a la tabla
            while (resultSet.next()) {
                Object[] row = new Object[7];
                row[0] = resultSet.getInt("Id_Colaborador");
                row[1] = resultSet.getInt("Id_Cargo");
                row[2] = resultSet.getString("Nombre_1");
                row[3] = resultSet.getString("Nombre_2");
                row[4] = resultSet.getString("Apellido_1");
                row[5] = resultSet.getString("Apellido_2");
                row[6] = resultSet.getString("Telefono");
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los colaboradores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        this.dispose(); // Cerrar la ventana de colaboradores
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarColaboradores ventana = new ConsultarColaboradores();
            ventana.setVisible(true);
        });
    }
}
