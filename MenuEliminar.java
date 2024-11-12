import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEliminar extends JFrame {

    ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");
    Image imagenelim = iconoEliminar.getImage();
    Image imagenelimRedimensionada = imagenelim.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoelimRedimensionado = new ImageIcon(imagenelimRedimensionada);

    private Image backgroundImage; // Variable para la imagen de fondo

    public MenuEliminar() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondomenu2.jpg").getImage();

        // Configuración de la ventana
        setTitle("Menú Eliminar");
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
        panelFondo.setBounds(0, 0, 500, 350); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea eliminar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblTitulo); // Añadir el JLabel al panel

        // Crear los botones y establecer sus coordenadas para eliminar
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(150, 80, 200, 40); // Coordenadas para el botón de eliminar cliente
        btnEliminarCliente.setBackground(new Color(203, 236, 255)); 
        btnEliminarCliente.setToolTipText("Haz clic para eliminar un cliente"); // Tooltip
        btnEliminarCliente.setIcon(iconoelimRedimensionado);

        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar cliente
                abrirEliminarCliente();
            }
        });
        panelFondo.add(btnEliminarCliente);

        JButton btnEliminarColaborador = new JButton("Eliminar Colaborador");
        btnEliminarColaborador.setBounds(140, 130, 220, 40); // Coordenadas para el botón de eliminar colaborador
        btnEliminarColaborador.setBackground(new Color(203, 236, 255)); 
        btnEliminarColaborador.setToolTipText("Haz clic para eliminar un colaborador"); // Tooltip
        btnEliminarColaborador.setIcon(iconoelimRedimensionado);

        btnEliminarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar colaborador
                abrirEliminarColaborador();
            }
        });
        panelFondo.add(btnEliminarColaborador);

        JButton btnEliminarCargo = new JButton("Eliminar Cargo");
        btnEliminarCargo.setBounds(150, 180, 200, 40); // Coordenadas para el botón de eliminar cargo
        btnEliminarCargo.setBackground(new Color(203, 236, 255)); 
        btnEliminarCargo.setToolTipText("Haz clic para eliminar un cargo"); // Tooltip
        btnEliminarCargo.setIcon(iconoelimRedimensionado);

        btnEliminarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar cargo
                abrirEliminarCargo();
            }
        });
        panelFondo.add(btnEliminarCargo);

        // Crear el botón "Regresar"
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 250, 100, 30); // Establecer las coordenadas para el botón "Regresar"
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

    // Método para abrir la ventana de EliminarCliente
    private void abrirEliminarCliente() {
        EliminarCliente eliminarCliente = new EliminarCliente();
        eliminarCliente.setVisible(true);
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    // Método para abrir la ventana de EliminarColaborador
    private void abrirEliminarColaborador() {
        EliminarColaborador eliminarColaborador = new EliminarColaborador();
        eliminarColaborador.setVisible(true);
    }

    // Método para abrir la ventana de EliminarCargo
    private void abrirEliminarCargo() {
        EliminarCargo eliminarCargo = new EliminarCargo();
        eliminarCargo.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuEliminar menuEliminar = new MenuEliminar();
            menuEliminar.setVisible(true);
        });
    }
}
