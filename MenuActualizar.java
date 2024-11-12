import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActualizar extends JFrame {

    ImageIcon iconoActualizar = new ImageIcon("imagenes/update.png");
    Image imagenact = iconoActualizar.getImage();
    Image imagenactRedimensionada = imagenact.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoactRedimensionado = new ImageIcon(imagenactRedimensionada);

    private Image backgroundImage; // Variable para la imagen de fondo

    public MenuActualizar() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondomenu6.jpg").getImage();

        // Configuración de la ventana
        setTitle("Menú Actualizar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

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
        JLabel lblTitulo = new JLabel("¿Qué desea actualizar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 60, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel de fondo

        // Crear los botones y establecer sus coordenadas para actualizar
        JButton btnActualizarCliente = new JButton("Actualizar Cliente");
        btnActualizarCliente.setBounds(150, 120, 200, 40); // Coordenadas para el botón de actualizar cliente
        btnActualizarCliente.setToolTipText("Haz clic para actualizar los datos de un cliente"); // Tooltip
        btnActualizarCliente.setIcon(iconoactRedimensionado);
        btnActualizarCliente.setBackground(new Color(134, 172, 212));
        btnActualizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarCliente2();
            }
        });

        panelFondo.add(btnActualizarCliente); // Añadir el botón al panel de fondo

        JButton btnActualizarColaborador = new JButton("Actualizar Colaborador");
        btnActualizarColaborador.setBounds(140, 170, 220, 40); // Coordenadas para el botón de actualizar colaborador
        btnActualizarColaborador.setBackground(new Color(134, 172, 212));
        btnActualizarColaborador.setToolTipText("Haz clic para actualizar los datos de un colaborador"); // Tooltip
        btnActualizarColaborador.setIcon(iconoactRedimensionado);
        btnActualizarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarColaborador();
            }
        });

        panelFondo.add(btnActualizarColaborador); // Añadir el botón al panel de fondo

        JButton btnActualizarCargo = new JButton("Actualizar Cargo");
        btnActualizarCargo.setBounds(150, 220, 200, 40); // Coordenadas para el botón de actualizar cargo
        btnActualizarCargo.setToolTipText("Haz clic para actualizar los datos de un cargo"); // Tooltip
        btnActualizarCargo.setBackground(new Color(134, 172, 212));
        btnActualizarCargo.setIcon(iconoactRedimensionado);
        btnActualizarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarCargo();
            }
        });

        panelFondo.add(btnActualizarCargo); // Añadir el botón al panel de fondo

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

        panelFondo.add(btnRegresar); // Añadir el botón al panel de fondo
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    private void abrirVentanaActualizarColaborador() {
        ActualizarColaboradores ventanaColaborador = new ActualizarColaboradores();
        ventanaColaborador.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    private void abrirVentanaActualizarCliente2() {
        ActualizarCliente ventanaCliente = new ActualizarCliente();
        ventanaCliente.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para abrir la ventana ActualizarCargo
    private void abrirVentanaActualizarCargo() {
        ActualizarCargo ventanaCargo = new ActualizarCargo();
        ventanaCargo.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuActualizar menuActualizar = new MenuActualizar();
            menuActualizar.setVisible(true);
        });
    }
}
