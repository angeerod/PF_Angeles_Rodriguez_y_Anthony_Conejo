import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    ImageIcon iconoIngresar = new ImageIcon("imagenes/new.png");
    ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");
    ImageIcon iconoConsultar = new ImageIcon("imagenes/consulta.png");
    ImageIcon iconoActualizar = new ImageIcon("imagenes/update.png");

    public MenuPrincipal() {
        // Configuración de la ventana
        setTitle("Inicio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(151, 204, 233)); // Fondo celeste
        setLayout(null); // Desactivamos el Layout Manager para posicionar los componentes con coordenadas

        // Crear el título de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenidos a nuestra tienda", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Georgia", Font.BOLD, 24));
        lblBienvenida.setForeground(Color.BLACK); // Color del texto
        lblBienvenida.setBounds(50, 20, 500, 40); // Establecer las coordenadas y tamaño del título
        lblBienvenida.setOpaque(false); // Asegúrate de que el JLabel es transparente

        JLabel lblSeleccion = new JLabel("Seleccione la opción que desea realizar:", SwingConstants.CENTER);
        lblSeleccion.setFont(new Font("Georgia", Font.BOLD, 14));
        lblSeleccion.setForeground(Color.BLACK); // Color del texto
        lblSeleccion.setBounds(50, 60, 500, 40); // Establecer las coordenadas y tamaño del título
        lblSeleccion.setOpaque(false); // Asegúrate de que el JLabel es transparente

        // Crear el botón "Agregar"
        JButton btnIngresar = new JButton("Agregar");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 12));

        btnIngresar.setBounds(240, 120, 100, 30); // Establecer las coordenadas y tamaño del botón
        btnIngresar.setToolTipText("Haz clic para agregar un nuevo elemento"); // Tooltip

        btnIngresar.setBounds(240, 120, 140, 40); // Establecer las coordenadas y tamaño del botón
        btnIngresar.setIcon(iconoIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuPrincipal();
            }
        });

        // Crear el botón "Eliminar"
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));

        btnEliminar.setBounds(240, 170, 100, 30); // Establecer las coordenadas y tamaño del botón
        btnEliminar.setToolTipText("Haz clic para eliminar un elemento"); // Tooltip

        btnEliminar.setBounds(240, 170, 140, 40); // Establecer las coordenadas y tamaño del botón
        btnEliminar.setIcon(iconoEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuEliminar(); // Abrir el menú de eliminación
            }
        });

        // Crear el botón "Actualizar"
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setToolTipText("Haz clic para actualizar un elemento"); // Tooltip
        btnActualizar.setBounds(235, 220, 150, 40); // Establecer las coordenadas y tamaño del botón
        btnActualizar.setIcon(iconoActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuActualizar(); // Abrir el menú de actualización
            }
        });

        // Crear el botón "Visualizar"
        JButton btnVisualizar = new JButton("Consultar");
        btnVisualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVisualizar.setToolTipText("Haz clic para visualizar los elementos"); // Tooltip
        btnVisualizar.setBounds(235, 270, 150, 40); // Establecer las coordenadas y tamaño del botón
        btnVisualizar.setIcon(iconoConsultar);

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuVisualizar(); // Abrir el menú de visualización
            }
        });

        // Añadir el título y los botones al JFrame
        add(lblBienvenida);
        add(lblSeleccion);
        add(btnIngresar);
        add(btnEliminar);
        add(btnActualizar);
        add(btnVisualizar); // Añadir el botón de visualización
    }

    private void abrirMenuPrincipal() {
        MenuIngresar menuPrincipal = new MenuIngresar();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la ventana de eliminar
        menuEliminar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuActualizar() {
        MenuActualizar menuActualizar = new MenuActualizar(); // Crear la ventana de actualizar
        menuActualizar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar(); // Crear la ventana de visualizar
        menuConsultar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal inicio = new MenuPrincipal();
            inicio.setVisible(true);
        });
    }
}
