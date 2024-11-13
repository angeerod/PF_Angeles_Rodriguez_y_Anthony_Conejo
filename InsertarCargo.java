import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class InsertarCargo extends JFrame {

    private JTextField txtIdCargo, txtNombre, txtSalario;
    private JButton btnRegresar;
    private Image backgroundImage; // Variable para la imagen de fondo

    public InsertarCargo() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        setTitle("Insertar Cargo");
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
                g.drawImage(backgroundImage, 0, 0, 400, 300, this);
            }
        };

        // Establecer el layout del panel de fondo
        panelFondo.setLayout(null);
        panelFondo.setBounds(-10, -40, 400, 300); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos del cargo:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(60, 45, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel de fondo

        // Configuración de los campos de texto (JTextField) con color de fondo
        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(50, 90, 150, 25);
        panelFondo.add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(210, 90, 150, 25);
        txtIdCargo.setBackground(new Color(209, 226, 252)); 
        txtIdCargo.setToolTipText("Ingrese el ID del cargo.");
        panelFondo.add(txtIdCargo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(60, 140, 150, 25);
        panelFondo.add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(210, 140, 150, 25);
        txtNombre.setBackground(new Color(209, 226, 252)); 
        txtNombre.setToolTipText("Ingrese el nombre del cargo.");
        panelFondo.add(txtNombre);

        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(60, 190, 150, 25);
        panelFondo.add(lblSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(210, 190, 150, 25);
        txtSalario.setBackground(new Color(209, 226, 252)); 
        txtSalario.setToolTipText("Ingrese el salario del cargo.");
        panelFondo.add(txtSalario);

        JButton btnInsertar = new JButton("Agregar Cargo");
        btnInsertar.setBounds(210, 240, 150, 30);
        btnInsertar.setToolTipText("Haz clic para Agregar el cargo.");
        btnInsertar.setBackground(new Color(134, 172, 212));
        btnInsertar.addActionListener(e -> insertarCargo());
        panelFondo.add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(60, 240, 100, 30);
        btnRegresar.setToolTipText("Haz clic para regresar al menú de inserción.");
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(e -> regresarAlMenu());
        panelFondo.add(btnRegresar);
    }

    private void insertarCargo() {
        String idCargo = txtIdCargo.getText();
        String nombre = txtNombre.getText();
        int salario = Integer.parseInt(txtSalario.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL InsertarCargo(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idCargo);
            statement.setString(2, nombre);
            statement.setInt(3, salario);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Cargo insertado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el cargo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdCargo.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}
