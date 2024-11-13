import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertarCliente extends JFrame {

    // Campos de texto para ingresar datos del cliente
    private JTextField txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;

    private Image backgroundImage; // Variable para la imagen de fondo

    public InsertarCliente() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        // Configuración de la ventana
        setTitle("Insertar Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

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

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos del cliente:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(120, 35, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel de fondo
        
        // Crear y añadir etiquetas y campos de texto con coordenadas personalizadas
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(120, 80, 150, 25);
        panelFondo.add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(270, 80, 150, 25);
        txtCedula.setBackground(new Color(209, 226, 252)); 
        txtCedula.setToolTipText("Ingrese la cédula del cliente.");
        panelFondo.add(txtCedula);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(120, 120, 150, 25);
        panelFondo.add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(270, 120, 150, 25);
        txtNombre1.setBackground(new Color(209, 226, 252)); 
        txtNombre1.setToolTipText("Ingrese el primer nombre del cliente.");
        panelFondo.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(120, 160, 150, 25);
        panelFondo.add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(270, 160, 150, 25);
        txtNombre2.setBackground(new Color(209, 226, 252)); 
        txtNombre2.setToolTipText("Ingrese el segundo nombre del cliente.");
        panelFondo.add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(120, 200, 150, 25);
        panelFondo.add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(270, 200, 150, 25);
        txtApellido1.setBackground(new Color(209, 226, 252)); 
        txtApellido1.setToolTipText("Ingrese el primer apellido del cliente.");
        panelFondo.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(120, 240, 150, 25);
        panelFondo.add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(270, 240, 150, 25);
        txtApellido2.setBackground(new Color(209, 226, 252)); 
        txtApellido2.setToolTipText("Ingrese el segundo apellido del cliente.");
        panelFondo.add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(120, 280, 150, 25);
        panelFondo.add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(270, 280, 150, 25);
        txtTelefono.setBackground(new Color(209, 226, 252)); 
        txtTelefono.setToolTipText("Ingrese el número de teléfono del cliente.");
        panelFondo.add(txtTelefono);

        // Botón para insertar el cliente
        JButton btnInsertar = new JButton("Agregar Cliente");
        btnInsertar.setBounds(270, 320, 150, 30);
        btnInsertar.setToolTipText("Haz clic para agregar el cliente.");
        btnInsertar.setBackground(new Color(134, 172, 212));
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarCliente();
            }
        });
        panelFondo.add(btnInsertar);

        // Botón de regresar al menú principal
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(120, 320, 100, 30);
        btnRegresar.setToolTipText("Haz clic para regresar al menú de inserción.");
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(e -> regresarAlMenu());
        panelFondo.add(btnRegresar);
    }

    // Método para insertar el cliente llamando al procedimiento almacenado
    private void insertarCliente() {
        String cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();
        String telefono = txtTelefono.getText();

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "angee2701");

            // Llamar al procedimiento almacenado
            String query = "{CALL InsertarCliente(?, ?, ?, ?, ?, ?)}";
            statement = connection.prepareCall(query);

            // Establecer los parámetros
            statement.setString(1, cedula);
            statement.setString(2, nombre1);
            statement.setString(3, nombre2);
            statement.setString(4, apellido1);
            statement.setString(5, apellido2);
            statement.setString(6, telefono);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cliente insertado exitosamente.");

            // Limpiar los campos después de la inserción
            limpiarCampos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtTelefono.setText("");
    }

    // Método para regresar al menú principal
    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertarCliente app = new InsertarCliente();
            app.setVisible(true);
        });
    }
}
