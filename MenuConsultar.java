import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuConsultar extends JFrame {

    public MenuConsultar() {
        // Configuración de la ventana
        setTitle("Menú Consultar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(null); // Usamos coordenadas absolutas

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea consultar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setBounds(100, 20, 300, 40);
        add(lblTitulo);

        // Botón para visualizar clientes
        JButton btnVisualizarCliente = new JButton("Clientes");
        btnVisualizarCliente.setBounds(150, 80, 200, 40);
        btnVisualizarCliente.setToolTipText("Haz clic para visualizar la lista de clientes");
        add(btnVisualizarCliente);

        // Botón para visualizar colaboradores
        JButton btnVisualizarColaborador = new JButton("Colaboradores");
        btnVisualizarColaborador.setBounds(150, 130, 200, 40);
        btnVisualizarColaborador.setToolTipText("Haz clic para visualizar la lista de colaboradores");
        btnVisualizarColaborador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarColaboradores().setVisible(true);  // Ventana para visualizar colaboradores
                dispose(); // Cierra el menú visualizar
            }
        });
        add(btnVisualizarColaborador);

        // Botón para visualizar cargos
        JButton btnVisualizarCargo = new JButton("Cargos");
        btnVisualizarCargo.setBounds(150, 180, 200, 40);
        btnVisualizarCargo.setToolTipText("Haz clic para visualizar la lista de cargos");
        btnVisualizarCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarCargos().setVisible(true);  // Abrir la ventana para visualizar cargos
                dispose(); // Cierra el menú visualizar
            }
        });
        add(btnVisualizarCargo);

        // Botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 250, 100, 30);
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });
        add(btnRegresar);

        // Acción del botón "Visualizar Clientes"
        btnVisualizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarClientes().setVisible(true); // Abrir la ventana de clientes
                dispose(); // Cierra el menú visualizar
            }
        });
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
