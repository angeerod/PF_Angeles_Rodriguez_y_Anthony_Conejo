import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuIngresar extends JFrame {

    ImageIcon iconoIngresar = new ImageIcon("imagenes/new.png");
    Image imageneing = iconoIngresar.getImage();
    Image imageneingRedimensionada = imageneing.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoeingRedimensionado = new ImageIcon(imageneingRedimensionada);

    private Image backgroundImage; // Variable para la imagen de fondo

    public MenuIngresar() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondomenu6.jpg").getImage();

        // Configuración de la ventana
        setTitle("Menú Agregar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Desactivamos el Layout Manager para posicionar los componentes con coordenadas

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
        panelFondo.setBounds(0, -38, 500, 350); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea agregar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 60, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel

        // Crear los botones y establecer sus coordenadas
        JButton btnInsertarCliente = new JButton("Agregar Cliente");
        btnInsertarCliente.setBounds(150, 120, 200, 40); // Coordenadas para el botón de insertar cliente
        btnInsertarCliente.setToolTipText("Haz clic para agregar un nuevo cliente"); // Tooltip
        btnInsertarCliente.setBackground(new Color(134, 172, 212)); 
        btnInsertarCliente.setIcon(iconoeingRedimensionado);

        btnInsertarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCliente();
            }
        });
        panelFondo.add(btnInsertarCliente); // Añadir el botón al panel

        JButton btnAgregarColaborador = new JButton("Agregar Colaborador");
        btnAgregarColaborador.setBounds(140, 170, 220, 40); // Coordenadas para el botón de agregar colaborador
        btnAgregarColaborador.setToolTipText("Haz clic para agregar un nuevo colaborador"); // Tooltip
        btnAgregarColaborador.setBackground(new Color(134, 172, 212)); 
        btnAgregarColaborador.setIcon(iconoeingRedimensionado);

        btnAgregarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarColaborador();
            }
        });
        panelFondo.add(btnAgregarColaborador); // Añadir el botón al panel

        JButton btnAgregarCargo = new JButton("Agregar Cargo");
        btnAgregarCargo.setBounds(150, 220, 200, 40); // Coordenadas para el botón de agregar cargo
        btnAgregarCargo.setBackground(new Color(134, 172, 212)); 
        btnAgregarCargo.setToolTipText("Haz clic para agregar un nuevo cargo"); // Tooltip
        btnAgregarCargo.setIcon(iconoeingRedimensionado);

        btnAgregarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCargo();
            }
        });
        panelFondo.add(btnAgregarCargo); // Añadir el botón al panel

        // Crear el botón "Regresar"
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(350, 290, 100, 30); // Establecer las coordenadas para el botón "Regresar"
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal"); // Tooltip
        btnRegresar.setBackground(new Color(233, 149, 149)); 
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });
        panelFondo.add(btnRegresar); // Añadir el botón al panel
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    private void abrirVentanaInsertarCliente() {
        InsertarCliente ventanaCliente = new InsertarCliente();
        ventanaCliente.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para abrir la ventana InsertarColaborador
    private void abrirVentanaInsertarColaborador() {
        InsertarColaborador ventanaColaborador = new InsertarColaborador();
        ventanaColaborador.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para abrir la ventana InsertarCargo
    private void abrirVentanaInsertarCargo() {
        InsertarCargo ventanaCargo = new InsertarCargo();
        ventanaCargo.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuIngresar menuIngresar = new MenuIngresar();
            menuIngresar.setVisible(true);
        });
    }
}
