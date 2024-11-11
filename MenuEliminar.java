import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEliminar extends JFrame {

    ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");

    public MenuEliminar() {
        // Configuración de la ventana
        setTitle("Menú Eliminar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

        // Crear el JLabel que contendrá la imagen de fondo (opcional)
        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 600, 400); // Establecer el tamaño y la posición de la imagen de fondo
        add(lblFondo); // Añadir el JLabel con la imagen de fondo al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea eliminar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear los botones y establecer sus coordenadas para eliminar
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.setBounds(150, 80, 200, 40); // Coordenadas para el botón de eliminar cliente

        btnEliminarCliente.setToolTipText("Haz clic para eliminar un cliente"); // Tooltip

        btnEliminarCliente.setIcon(iconoEliminar);

        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar cliente
                abrirEliminarCliente();
            }
        });
        add(btnEliminarCliente);

        JButton btnEliminarColaborador = new JButton("Eliminar Colaborador");
        btnEliminarColaborador.setBounds(150, 130, 200, 40); // Coordenadas para el botón de eliminar colaborador

        btnEliminarColaborador.setToolTipText("Haz clic para eliminar un colaborador"); // Tooltip

        btnEliminarColaborador.setIcon(iconoEliminar);

        btnEliminarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar colaborador
                abrirEliminarColaborador();
            }
        });
        add(btnEliminarColaborador);

        JButton btnEliminarCargo = new JButton("Eliminar Cargo");
        btnEliminarCargo.setBounds(150, 180, 200, 40); // Coordenadas para el botón de eliminar cargo

        btnEliminarCargo.setToolTipText("Haz clic para eliminar un cargo"); // Tooltip

        btnEliminarCargo.setIcon(iconoEliminar);

        btnEliminarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Al hacer clic, abrir la ventana de eliminar cargo
                abrirEliminarCargo();
            }
        });
        add(btnEliminarCargo);

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
