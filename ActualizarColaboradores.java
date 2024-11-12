
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class ActualizarColaboradores extends JFrame {

    private JTextField txtIdColaborador, txtIdCargo, txtNombre1, txtNombre2, txtApellido1, txtApellido2, txtTelefono;
    private JButton btnRegresar;

    public ActualizarColaboradores() {
        setTitle("Actualizar Colaborador");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Agregar la imagen de fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon("imagenes/fondomenu2.jpg")); // Asegúrate de tener la ruta correcta
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos a actualizar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        backgroundLabel.add(lblTitulo); // Añadir el JLabel al backgroundLabel

        // Configuración de los campos de texto (JTextField) con color de fondo
        JLabel lblIdColaborador = new JLabel("ID Colaborador:");
        lblIdColaborador.setBounds(50, 50, 150, 25);
        backgroundLabel.add(lblIdColaborador);
        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(200, 50, 150, 25);
        txtIdColaborador.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtIdColaborador.setToolTipText("Ingrese el ID del colaborador."); // Tooltip
        backgroundLabel.add(txtIdColaborador);

        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(50, 90, 150, 25);
        backgroundLabel.add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(200, 90, 150, 25);
        txtIdCargo.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtIdCargo.setToolTipText("Ingrese el ID del cargo."); // Tooltip
        backgroundLabel.add(txtIdCargo);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(50, 130, 150, 25);
        backgroundLabel.add(lblNombre1);
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(200, 130, 150, 25);
        txtNombre1.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtNombre1.setToolTipText("Ingrese el primer nombre del colaborador."); // Tooltip
        backgroundLabel.add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(50, 170, 150, 25);
        backgroundLabel.add(lblNombre2);
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(200, 170, 150, 25);
        txtNombre2.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtNombre2.setToolTipText("Ingrese el segundo nombre del colaborador."); // Tooltip
        backgroundLabel.add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(50, 210, 150, 25);
        backgroundLabel.add(lblApellido1);
        txtApellido1 = new JTextField();
        txtApellido1.setBounds(200, 210, 150, 25);
        txtApellido1.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtApellido1.setToolTipText("Ingrese el primer apellido del colaborador."); // Tooltip
        backgroundLabel.add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(50, 250, 150, 25);
        backgroundLabel.add(lblApellido2);
        txtApellido2 = new JTextField();
        txtApellido2.setBounds(200, 250, 150, 25);
        txtApellido2.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtApellido2.setToolTipText("Ingrese el segundo apellido del colaborador."); // Tooltip
        backgroundLabel.add(txtApellido2);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 290, 150, 25);
        backgroundLabel.add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(200, 290, 150, 25);
        txtTelefono.setBackground(new Color(199, 235, 255)); // Cambiar el color de fondo
        txtTelefono.setToolTipText("Ingrese el número de teléfono del colaborador."); // Tooltip
        backgroundLabel.add(txtTelefono);

        JButton btnInsertar = new JButton("Actualizar Colaborador");
        btnInsertar.setBounds(190, 330, 170, 30);
        btnInsertar.setBackground(new Color(203, 236, 255)); 
        btnInsertar.addActionListener(e -> insertarColaborador());
        backgroundLabel.add(btnInsertar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(50, 330, 100, 30);
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(e -> regresarAlMenu());
        backgroundLabel.add(btnRegresar);
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
}
