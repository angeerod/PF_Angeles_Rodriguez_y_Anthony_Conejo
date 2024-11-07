import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public Login() {
        // Configuración de la ventana
        setTitle("Login");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setLayout(null);  // Usar coordenadas absolutas

        // Crear panel personalizado para el fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo
                ImageIcon fondoIcon = new ImageIcon("imagenes\\fondologin.jpg");
                Image imagen = fondoIcon.getImage();
                // Redimensionar la imagen para ajustarla al tamaño del panel
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);  // Usar coordenadas absolutas para el contenido
        panelFondo.setBounds(0, 0, getWidth(), getHeight());  // Cubrir toda la ventana
        add(panelFondo);  // Añadir el panel de fondo a la ventana

        // Crear panel para el cuadro celeste en el medio
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(null);  // Usar coordenadas absolutas en este panel
        panelCentro.setBackground(new Color(61, 159, 205, 100));  // Celeste con 50% de transparencia (alpha = 128)
        panelCentro.setBounds(150, 100, 400, 320);  // Posición y tamaño del panel celeste
        panelFondo.add(panelCentro);  // Agregar el panel celeste sobre la imagen de fondo

        // Cargar el icono y redimensionarlo
        ImageIcon iconoLogin = new ImageIcon("imagenes\\iconlogin.png");
        Image iconoRedimensionado = iconoLogin.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);  // Redimensionar
        JLabel lblIcono = new JLabel(new ImageIcon(iconoRedimensionado));
        
        // Posicionar el icono en la parte superior del panel
        lblIcono.setBounds(170, 15, 70, 70);  // Ajustar posición del icono
        panelCentro.add(lblIcono);  // Añadir el icono al panel

        // Título
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(165, 100, 100, 25);  // Ajustar posición debajo del icono
        lblLogin.setForeground(new Color(8, 68, 102));  
        lblLogin.setFont(new Font("Georgia", Font.BOLD, 24));
        panelCentro.add(lblLogin);

        // Etiqueta de usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(90, 150, 100, 25);
        lblUsuario.setForeground(new Color(8, 68, 102));  
        panelCentro.add(lblUsuario);

        // Campo de texto para el usuario
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 150, 140, 25);
        panelCentro.add(txtUsuario);

        // Etiqueta de contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(90, 200, 100, 25);
        lblContrasena.setForeground(new Color(8, 68, 102));  
        panelCentro.add(lblContrasena);

        // Campo de texto para la contraseña
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(180, 200, 140, 25);
        panelCentro.add(txtContrasena);

        // Botón de autenticación
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(130, 250, 150, 30);
        panelCentro.add(btnLogin);

        // Acción del botón de autenticación
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                char[] contrasena = txtContrasena.getPassword();
                String contrasenaStr = new String(contrasena);

                if (validarUsuario(usuario, contrasenaStr)) {
                    JOptionPane.showMessageDialog(null, "Autenticación exitosa.");
                    // Aquí puedes abrir la siguiente ventana después de la autenticación exitosa.
                    // Por ejemplo: new MenuPrincipal().setVisible(true);
                    // dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });
    }

    // Método para validar el usuario y la contraseña desde la base de datos
    private boolean validarUsuario(String usuario, String contrasena) {
        boolean autenticado = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda", "root", "angee2701");

            // Consulta para verificar el usuario y la contraseña
            String query = "SELECT * FROM usuarios WHERE User = ? AND Contraseña = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, usuario);
            statement.setString(2, contrasena);

            resultSet = statement.executeQuery();

            // Si el usuario existe, se autentica
            if (resultSet.next()) {
                autenticado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        return autenticado;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login app = new Login();
            app.setVisible(true);
        });
    }
}
