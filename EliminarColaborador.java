import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarColaborador extends JFrame {

    // Campo de texto para ingresar el ID del colaborador
    private JTextField txtIdColaborador;
    private Image backgroundImage; // Variable para la imagen de fondo

    public EliminarColaborador() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        // Configuración de la ventana
        setTitle("Eliminar Colaborador");
        setSize(370, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Crear un panel para manejar el fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen de fondo en el panel
                g.drawImage(backgroundImage, -20, -70, 380, 300, this);
            }
        };

        // Establecer el layout del panel de fondo
        panelFondo.setLayout(null);
        panelFondo.setBounds(0, 0, 370, 200); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Título
        JLabel lblTitulo = new JLabel("Ingrese el ID del colaborador a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(30, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel de fondo

        // Etiqueta y campo de texto para el ID del colaborador
        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(40, 50, 100, 30);
        panelFondo.add(lblIdColaborador);

        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(160, 50, 160, 30);
        txtIdColaborador.setBackground(new Color(209, 226, 252)); 
        txtIdColaborador.setToolTipText("Ingrese el ID del colaborador que desea eliminar."); // Tooltip
        panelFondo.add(txtIdColaborador);

        // Botón para eliminar el colaborador
        JButton btnEliminar = new JButton("Eliminar Colaborador");
        btnEliminar.setBounds(160, 100, 160, 30);
        btnEliminar.setToolTipText("Haz clic para eliminar el colaborador con el ID ingresado."); // Tooltip
        btnEliminar.setBackground(new Color(134, 172, 212));
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarColaborador();
            }
        });
        panelFondo.add(btnEliminar);

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
        panelFondo.add(btnRegresar);
    }

    // Método para eliminar el colaborador llamando al procedimiento almacenado
    private void eliminarColaborador() {
        String idColaborador = txtIdColaborador.getText();

        if (idColaborador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del colaborador.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

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

    // Método para regresar al menú eliminar
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
