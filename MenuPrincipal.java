import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    ImageIcon iconoIngresar = new ImageIcon("imagenes/new.png");
    Image imageneing = iconoIngresar.getImage();
    Image imageneingRedimensionada = imageneing.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoeingRedimensionado = new ImageIcon(imageneingRedimensionada);
    ImageIcon iconoEliminar = new ImageIcon("imagenes/delete.png");
    Image imagenelim = iconoEliminar.getImage();
    Image imagenelimRedimensionada = imagenelim.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoelimRedimensionado = new ImageIcon(imagenelimRedimensionada);
    ImageIcon iconoActualizar = new ImageIcon("imagenes/update.png");
    Image imagenact = iconoActualizar.getImage();
    Image imagenactRedimensionada = imagenact.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon iconoactRedimensionado = new ImageIcon(imagenactRedimensionada);

    ImageIcon iconoConsultar = new ImageIcon("imagenes/consulta.png");
    Image imagen = iconoConsultar.getImage();
    Image imagenRedimensionada = imagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

    private Image backgroundImage; // Variable para la imagen de fondo

    public MenuPrincipal() {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("imagenes/fondomenu6.jpg").getImage();

        // Configuración de la ventana
        setTitle("Inicio");
        setSize(600, 435);
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
        panelFondo.setBounds(0, 0, 600, 400); // Tamaño del panel debe ser el mismo que la ventana
        add(panelFondo); // Agregar el panel al JFrame

        // Crear el título de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenidos a nuestra tienda", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Georgia", Font.BOLD, 24));
        lblBienvenida.setForeground(Color.BLACK); // Color del texto
        lblBienvenida.setBounds(50, 20, 500, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblBienvenida);

        JLabel lblSeleccion = new JLabel("Seleccione la opción que desea realizar:", SwingConstants.CENTER);
        lblSeleccion.setFont(new Font("Georgia", Font.BOLD, 14));
        lblSeleccion.setForeground(Color.BLACK); // Color del texto
        lblSeleccion.setBounds(50, 60, 500, 40); // Establecer las coordenadas y tamaño del título
        panelFondo.add(lblSeleccion);

        // Crear el botón "Agregar"
        JButton btnIngresar = new JButton("Agregar");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 12));
        btnIngresar.setBounds(240, 120, 140, 40);
        btnIngresar.setBackground(new Color(134, 172, 212));
        btnIngresar.setIcon(iconoeingRedimensionado);
        btnIngresar.setFocusable(false);  // Importante para que no interfiera en el foco
        panelFondo.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuPrincipal();
            }
        });

        // Crear el botón "Eliminar"
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(134, 172, 212));
        btnEliminar.setBounds(240, 170, 140, 40);
        btnEliminar.setIcon(iconoelimRedimensionado);
        btnEliminar.setFocusable(false);  // Evita interferencia del foco
        panelFondo.add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuEliminar(); // Abrir el menú de eliminación
            }
        });

        // Crear el botón "Actualizar"
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnActualizar.setToolTipText("Haz clic para actualizar un elemento");
        btnActualizar.setBounds(235, 220, 150, 40);
        btnActualizar.setIcon(iconoactRedimensionado);
        btnActualizar.setBackground(new Color(134, 172, 212));
        btnActualizar.setFocusable(false); // Evita interferencia de foco
        panelFondo.add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuActualizar(); // Abrir el menú de actualización
            }
        });

        // Crear el botón "Visualizar"
        JButton btnVisualizar = new JButton("Consultar");
        btnVisualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVisualizar.setToolTipText("Haz clic para visualizar los elementos");
        btnVisualizar.setBounds(235, 270, 150, 40);
        btnVisualizar.setIcon(iconoRedimensionado);
        btnVisualizar.setBackground(new Color(134, 172, 212));
        btnVisualizar.setFocusable(false); // Evita interferencia de foco
        panelFondo.add(btnVisualizar);

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuVisualizar(); // Abrir el menú de visualización
            }
        });
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

