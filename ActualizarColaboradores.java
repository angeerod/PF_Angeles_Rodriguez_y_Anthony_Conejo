import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class ActualizarColaboradores extends JFrame {

    private JTextField txtIdColaborador, txtIdCargo, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;
    private Image backgroundImage; // Variable para la imagen de fondo

    public ActualizarColaboradores() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondoacc2.png").getImage();

        setTitle("Actualizar Colaborador");
        setSize(400, 420);
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
        panelFondo.setBounds(-80, -20, 550, 400);
        add(panelFondo); // Agregar el panel al JFrame

        JLabel lblTitulo = new JLabel("Ingrese los datos a actualizar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(130, 25, 300, 40);
        panelFondo.add(lblTitulo);

        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(130, 70, 150, 25);
        panelFondo.add(lblIdColaborador);
        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(280, 70, 150, 25);
        txtIdColaborador.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtIdColaborador);

        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(130, 110, 150, 25);
        panelFondo.add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(280, 110, 150, 25);
        txtIdCargo.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtIdCargo);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(120, 150, 150, 25);
        panelFondo.add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(280, 150, 150, 25);
        txtNombre1.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(120, 190, 150, 25);
        panelFondo.add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(280, 190, 150, 25);
        txtNombre2.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(120, 230, 150, 25);
        panelFondo.add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(280, 230, 150, 25);
        txtApellido1.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(120, 270, 150, 25);
        panelFondo.add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(280, 270, 150, 25);
        txtApellido2.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(120, 310, 150, 25);
        panelFondo.add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(280, 310, 150, 25);
        txtTelefono.setBackground(new Color(199, 235, 255));
        panelFondo.add(txtTelefono);

        JButton btnInsertar = new JButton("Actualizar Colaborador");
        btnInsertar.setBounds(270, 350, 170, 30);
        btnInsertar.setBackground(new Color(203, 236, 255));
        btnInsertar.addActionListener(e -> insertarColaborador());
        panelFondo.add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(120, 350, 100, 30);
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(e -> regresarAlMenu());
        panelFondo.add(btnRegresar);
    }

    private void insertarColaborador() {
        String idColaborador = txtIdColaborador.getText();
        String idCargo = txtIdCargo.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();
        String telefono = txtTelefono.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701")) {
            String query = "{CALL ActualizarColaborador(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idColaborador);
            statement.setString(2, idCargo);
            statement.setString(3, nombre1);
            statement.setString(4, nombre2);
            statement.setString(5, apellido1);
            statement.setString(6, apellido2);
            statement.setString(7, telefono);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Colaborador actualizado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el colaborador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdColaborador.setText("");
        txtIdCargo.setText("");
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
            ActualizarColaboradores app = new ActualizarColaboradores();
            app.setVisible(true);
        });
    }
}
