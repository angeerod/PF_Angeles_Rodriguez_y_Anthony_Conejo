import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuVisualizar extends JFrame {

    public MenuVisualizar() {
        // Configuración de la ventana
        setTitle("Menú Visualizar");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos setLayout(null) para usar coordenadas absolutas

        // Crear el JLabel que contendrá la imagen de fondo (opcional)
        JLabel lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 600, 400); // Establecer el tamaño y la posición de la imagen de fondo
        add(lblFondo); // Añadir el JLabel con la imagen de fondo al JFrame

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿Qué desea visualizar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK); // Color del texto
        lblTitulo.setBounds(100, 20, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear los botones y establecer sus coordenadas para visualizar
        JButton btnVisualizarCliente = new JButton("Visualizar Clientes");
        btnVisualizarCliente.setBounds(150, 80, 200, 40); // Coordenadas para el botón de visualizar cliente
        add(btnVisualizarCliente); // Añadir el botón al JFrame

        JButton btnVisualizarColaborador = new JButton("Visualizar Colaboradores");
        btnVisualizarColaborador.setBounds(150, 130, 200, 40); // Coordenadas para el botón de visualizar colaborador
        add(btnVisualizarColaborador); // Añadir el botón al JFrame

        JButton btnVisualizarCargo = new JButton("Visualizar Cargos");
        btnVisualizarCargo.setBounds(150, 180, 200, 40); // Coordenadas para el botón de visualizar cargo
        add(btnVisualizarCargo); // Añadir el botón al JFrame

      
    // Crear el botón "Regresar"
    JButton btnRegresar = new JButton("Regresar");
    btnRegresar.setBounds(30, 250, 100, 30); // Establecer las coordenadas para el botón "Regresar"
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuVisualizar menuVisualizar = new MenuVisualizar();
            menuVisualizar.setVisible(true);
        });
    }
}
