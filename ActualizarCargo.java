import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class ActualizarCargo extends JFrame {

    private JTextField txtIdCargo, txtNombre, txtSalario;
    private JButton btnRegresar;

    @SuppressWarnings("unused")
    public ActualizarCargo() {
        setTitle("Actualizar Cargo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233 ));
        setLocationRelativeTo(null);
        setLayout(null);

         // Crear el título
         JLabel lblTitulo = new JLabel("Inserte los datos a actualizar:", SwingConstants.CENTER);
         lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
         lblTitulo.setForeground(Color.BLACK); // Color del texto
         lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
         add(lblTitulo); // Añadir el JLabel al JFrame

        JLabel lblIdCargo = new JLabel("ID Cargo:");
        lblIdCargo.setBounds(50, 50, 150, 25);
        add(lblIdCargo);
        txtIdCargo = new JTextField();
        txtIdCargo.setBounds(200, 50, 150, 25);
        txtIdCargo.setBackground(new Color(199, 235, 255)); 
        add(txtIdCargo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 100, 150, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(200, 100, 150, 25);
        txtNombre.setBackground(new Color(199, 235, 255)); 
        add(txtNombre);

        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(50, 150, 150, 25);
        add(lblSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(200, 150, 150, 25);
        txtSalario.setBackground(new Color(199, 235, 255)); 
        add(txtSalario);

        JButton btnActualizar = new JButton("Actualizar Cargo");
        btnActualizar.setBounds(200, 200, 150, 30);
        btnActualizar.addActionListener(e -> actualizarCargo());
        add(btnActualizar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(50, 200, 100, 30);
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
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
