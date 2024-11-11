import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuIngresar extends JFrame {

    ImageIcon iconoIngresar = new ImageIcon("imagenes/new.png");

    public MenuIngresar() {
        // Configuración de la ventana
        setTitle("Menú Agregar");
        setSize(500, 350);
        getContentPane().setBackground(new Color(151, 204, 233));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

        // Crear el JLabel que contendrá la imagen de fondo
        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 600, 400); // Establecer el tamaño y la posición de la imagen de fondo
        add(lblFondo); // Añadir el JLabel con la imagen de fondo al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea agregar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear los botones y establecer sus coordenadas
        JButton btnInsertarCliente = new JButton("Agregar Cliente");
        btnInsertarCliente.setBounds(150, 80, 200, 40); // Coordenadas para el botón de insertar cliente

        btnInsertarCliente.setToolTipText("Haz clic para agregar un nuevo cliente"); // Tooltip

        btnInsertarCliente.setIcon(iconoIngresar);

        btnInsertarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCliente();
            }
        });
        add(btnInsertarCliente); // Añadir el botón al JFrame

        JButton btnAgregarColaborador = new JButton("Agregar Colaborador");
        btnAgregarColaborador.setBounds(140, 130, 220, 40); // Coordenadas para el botón de agregar colaborador

        btnAgregarColaborador.setToolTipText("Haz clic para agregar un nuevo colaborador"); // Tooltip

        btnAgregarColaborador.setIcon(iconoIngresar);

        btnAgregarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarColaborador();
            }
        });
        add(btnAgregarColaborador); // Añadir el botón al JFrame

        JButton btnAgregarCargo = new JButton("Agregar Cargo");
        btnAgregarCargo.setBounds(150, 180, 200, 40); // Coordenadas para el botón de agregar cargo

        btnAgregarCargo.setToolTipText("Haz clic para agregar un nuevo cargo"); // Tooltip

        btnAgregarCargo.setIcon(iconoIngresar);

        btnAgregarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarCargo();
            }
        });
        add(btnAgregarCargo); // Añadir el botón al JFrame

        // Crear el botón "Regresar"
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 250, 100, 30); // Establecer las coordenadas para el botón "Regresar"
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal"); // Tooltip
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });
        add(btnRegresar); // Añadir el botón al JFrame

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
