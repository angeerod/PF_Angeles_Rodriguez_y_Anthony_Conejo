import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;

public class ActualizarCargo extends JFrame {

    private JTextField txtIdCargo, txtNombre, txtSalario;
    private JButton btnRegresar;
    private Image backgroundImage; // Variable para la imagen de fondo

    public ActualizarCargo() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        setTitle("Actualizar Cargo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Crear un panel para manejar el fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen de fondo en el panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Establecer el layout del panel de fondo 120
        panelFondo.setLayout(null);
        panelFondo.setBounds(-20, -25, 450, 300); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título
        JLabel lblTitulo = new JLabel("Inserte los datos a actualizar:", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(70, 30, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel de fondo

        // Etiqueta y campo de texto para el ID del cargo
        JLabel lblIdCargo = new JLabel("ID Cargo a actualizar:");
        lblIdCargo.setFont(new Font("Georgia", Font.BOLD, 10 ));
        lblIdCargo.setBounds(70, 80, 150, 25);
        panelFondo.add(lblIdCargo);

        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(220, 80, 150, 25);
        txtIdCargo.setBackground(new Color(209, 226, 252)); 
        txtIdCargo.setToolTipText("Ingrese el ID del cargo a actualizar.");
        panelFondo.add(txtIdCargo);

        // Etiqueta y campo de texto para el nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(70, 130, 150, 25);
        panelFondo.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(220, 130, 150, 25);
        txtNombre.setBackground(new Color(209, 226, 252)); 
        txtNombre.setToolTipText("Ingrese el nuevo nombre del cargo.");
        panelFondo.add(txtNombre);

        // Etiqueta y campo de texto para el salario
        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(70, 180, 150, 25);
        panelFondo.add(lblSalario);

        txtSalario = new JTextField();
        txtSalario.setBounds(220, 180, 150, 25);
        txtSalario.setBackground(new Color(209, 226, 252)); 
        txtSalario.setToolTipText("Ingrese el nuevo salario del cargo.");
        panelFondo.add(txtSalario);

        // Botón para actualizar el cargo
        JButton btnActualizar = new JButton("Actualizar Cargo");
        btnActualizar.setBounds(220, 230, 150, 30);
        btnActualizar.setBackground(new Color(134, 172, 212)); 
        btnActualizar.setToolTipText("Haz click para actualizar los datos del cargo.");
        btnActualizar.addActionListener(e -> actualizarCargo());
        panelFondo.add(btnActualizar);

        // Botón para regresar al menú
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(70, 230, 100, 30);
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnActualizar.setToolTipText("Haz click para regresar al menu de actualización.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        panelFondo.add(btnRegresar);
    }

    private void actualizarCargo() {
        String idCargo = txtIdCargo.getText();
        String nombre = txtNombre.getText();
        int salario = Integer.parseInt(txtSalario.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL ActualizarCargos(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idCargo);
            statement.setString(2, nombre);
            statement.setInt(3, salario);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cargo actualizado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cargo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdCargo.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
    }

    private void regresarAlMenu() {
        MenuActualizar menuActualizar = new MenuActualizar();
        menuActualizar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarCargo app = new ActualizarCargo();
            app.setVisible(true);
        });
    }
}
