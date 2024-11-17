import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class ActualizarCliente extends JFrame {
    
    private JTextField txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;

    private Image backgroundImage; // Variable para la imagen de fondo

    public ActualizarCliente() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        setTitle("Actualizar Cliente");
        setSize(400, 400);
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

        // Establecer el layout del panel de fondo
        panelFondo.setLayout(null);
        panelFondo.setBounds(-70, -30, 550, 400); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        JLabel lblTitulo = new JLabel("Ingrese los datos a actualizar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(120, 35, 300, 40);
        panelFondo.add(lblTitulo);
        
        JLabel lblCedula = new JLabel("Cédula a actualizar:");
        lblCedula.setBounds(120, 80, 150, 25);
        panelFondo.add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(270, 80, 150, 25);
        txtCedula.setBackground(new Color(209, 226, 252)); 
        txtCedula.setToolTipText("Ingrese la cédula del cliente que desea actualizar");
        panelFondo.add(txtCedula);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(120, 120, 150, 25);
        panelFondo.add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(270, 120, 150, 25);
        txtNombre1.setBackground(new Color(209, 226, 252)); 
        txtNombre1.setToolTipText("Ingrese el primer nombre del cliente");
        panelFondo.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(120, 160, 150, 25);
        panelFondo.add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(270, 160, 150, 25);
        txtNombre2.setBackground(new Color(209, 226, 252)); 
        txtNombre2.setToolTipText("Ingrese el segundo nombre del cliente (opcional)");
        panelFondo.add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(120, 200, 150, 25);
        panelFondo.add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(270, 200, 150, 25);
        txtApellido1.setBackground(new Color(209, 226, 252)); 
        txtApellido1.setToolTipText("Ingrese el primer apellido del cliente");
        panelFondo.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(120, 240, 150, 25);
        panelFondo.add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(270, 240, 150, 25);
        txtApellido2.setBackground(new Color(209, 226, 252)); 
        txtApellido2.setToolTipText("Ingrese el segundo apellido del cliente");
        panelFondo.add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(120, 280, 150, 25);
        panelFondo.add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(270, 280, 150, 25);
        txtTelefono.setBackground(new Color(209, 226, 252)); 
        txtTelefono.setToolTipText("Ingrese el número de teléfono del cliente");
        panelFondo.add(txtTelefono);

        JButton btnActualizar = new JButton("Actualizar Cliente");
        btnActualizar.setBackground(new Color(134, 172, 212)); 
        btnActualizar.setBounds(270, 320, 150, 30);
        btnActualizar.setToolTipText("Haz click para actualizar los datos del cliente");
        btnActualizar.addActionListener(e -> actualizarCliente());
        panelFondo.add(btnActualizar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(120, 320, 100, 30);
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.setToolTipText("Haz click para regresar al menú de actualización.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        panelFondo.add(btnRegresar);
    }

    private void actualizarCliente() {
        String cedula = txtCedula.getText();
        String nuevoNombre1 = txtNombre1.getText();
        String nuevoNombre2 = txtNombre2.getText();
        String nuevoApellido1 = txtApellido1.getText();
        String nuevoApellido2 = txtApellido2.getText();
        String nuevoTelefono = txtTelefono.getText();
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701")) {
            String query = "{CALL ActualizarClientes(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);

            statement.setString(1, cedula);
            statement.setString(2, nuevoNombre1);
            statement.setString(3, nuevoNombre2);
            statement.setString(4, nuevoApellido1);
            statement.setString(5, nuevoApellido2);
            statement.setString(6, nuevoTelefono);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtTelefono.setText("");
    }

    private void regresarAlMenu() {
        MenuActualizar menuActualizar = new MenuActualizar();
        menuActualizar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarCliente app = new ActualizarCliente();
            app.setVisible(true);
        });
    }
}
