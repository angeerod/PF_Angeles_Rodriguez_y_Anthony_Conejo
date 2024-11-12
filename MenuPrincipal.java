import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    ImageIcon iconoIngresar = new ImageIcon("imagenes/new.png");
    Image imageneing = iconoIngresar.getImage();
    Image imageneingRedimensionada = imageneing.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // Cambia 20x20 por el tamaño que desees
    ImageIcon iconoeingRedimensionado = new ImageIcon(imageneingRedimensionada);
    ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");
    Image imagenelim = iconoEliminar.getImage();
    Image imagenelimRedimensionada = imagenelim.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // Cambia 20x20 por el tamaño que desees
    ImageIcon iconoelimRedimensionado = new ImageIcon(imagenelimRedimensionada);
    ImageIcon iconoActualizar = new ImageIcon("imagenes/update.png");
    Image imagenact = iconoActualizar.getImage();
    Image imagenactRedimensionada = imagenact.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // Cambia 20x20 por el tamaño que desees
    ImageIcon iconoactRedimensionado = new ImageIcon(imagenactRedimensionada);

    ImageIcon iconoConsultar = new ImageIcon("imagenes/consulta.png");
    Image imagen = iconoConsultar.getImage();
    Image imagenRedimensionada = imagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Cambia 20x20 por el tamaño que desees
    ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

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
        btnIngresar.setBackground(new Color(203, 236, 255)); 
        btnIngresar.setBounds(240, 120, 140, 40); // Establecer las coordenadas y tamaño del botón
        btnIngresar.setIcon(iconoeingRedimensionado);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuPrincipal();
            }
        });

        // Crear el botón "Eliminar"
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(203, 236, 255)); 
        btnEliminar.setBounds(240, 170, 100, 30); // Establecer las coordenadas y tamaño del botón
        btnEliminar.setToolTipText("Haz clic para eliminar un elemento"); // Tooltip
        btnEliminar.setBounds(240, 170, 140, 40); // Establecer las coordenadas y tamaño del botón
        btnEliminar.setIcon(iconoelimRedimensionado);

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
        btnActualizar.setIcon(iconoactRedimensionado);
        btnActualizar.setBackground(new Color(203, 236, 255)); 
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
        btnVisualizar.setIcon(iconoRedimensionado);
        btnVisualizar.setBackground(new Color(203, 236, 255)); 
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
