import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuConsultar extends JFrame {

    ImageIcon iconoConsultar = new ImageIcon("imagenes/consulta.png");
    Image imagen = iconoConsultar.getImage();
    Image imagenRedimensionada = imagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

    private Image backgroundImage; // Variable para la imagen de fondo

    public MenuConsultar() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondomenu2.jpg").getImage();

        // Configuración de la ventana
        setTitle("Menú Consultar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos coordenadas absolutas

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
        panelFondo.setBounds(0, 0, 500, 350); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea consultar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setBounds(100, 20, 300, 40);
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel

        // Botón para visualizar clientes
        JButton btnVisualizarCliente = new JButton("Consultar Clientes");
        btnVisualizarCliente.setBounds(150, 80, 200, 40);
        btnVisualizarCliente.setBackground(new Color(203, 236, 255));
        btnVisualizarCliente.setToolTipText("Haz clic para visualizar la lista de clientes");
        btnVisualizarCliente.setIcon(iconoRedimensionado);
        panelFondo.add(btnVisualizarCliente);
        btnVisualizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarClientes().setVisible(true);  // Abrir la ventana para visualizar cargos
                dispose(); // Cierra el menú visualizar
            }
        });
        panelFondo.add(btnVisualizarCliente);

        // Botón para visualizar colaboradores
        JButton btnVisualizarColaborador = new JButton("Consultar Colaborador");
        btnVisualizarColaborador.setBounds(140, 130, 220, 40);
        btnVisualizarColaborador.setBackground(new Color(203, 236, 255));
        btnVisualizarColaborador.setToolTipText("Haz clic para visualizar la lista de colaboradores");
        btnVisualizarColaborador.setIcon(iconoRedimensionado);

        btnVisualizarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarColaboradores().setVisible(true);  // Ventana para visualizar colaboradores
                dispose(); // Cierra el menú visualizar
            }
        });
        panelFondo.add(btnVisualizarColaborador);

        // Botón para visualizar cargos
        JButton btnVisualizarCargo = new JButton("Consultar Cargos");
        btnVisualizarCargo.setBounds(150, 180, 200, 40);
        btnVisualizarCargo.setBackground(new Color(203, 236, 255));
        btnVisualizarCargo.setToolTipText("Haz clic para visualizar la lista de cargos");
        btnVisualizarCargo.setIcon(iconoRedimensionado);

        btnVisualizarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarCargos().setVisible(true);  // Abrir la ventana para visualizar cargos
                dispose(); // Cierra el menú visualizar
            }
        });
        panelFondo.add(btnVisualizarCargo);

        // Botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 250, 100, 30);
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal");
        btnRegresar.setBackground(new Color(233, 149, 149));
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });
        panelFondo.add(btnRegresar);
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuConsultar menuConsultar = new MenuConsultar();
            menuConsultar.setVisible(true);
        });
    }
}
