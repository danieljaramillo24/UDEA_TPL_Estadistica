import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame; // Modulo para ventanas y formularios.
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

// Extendemos el modulo.
public class FrmEstadistica extends JFrame {
    // Método constructor
    public FrmEstadistica() {
        setSize(500, 300); // Tamaño ventana prixeles

        setTitle("Estadistica");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Cerrar el programa al cerrar la vetana

        setLayout(null); // Anular la distribución automatica de la ventana.

        // Objetos
        // Instancia label
        JLabel lblDato = new JLabel("Dato");
        // Dimensiones label + distribución en la pantalla
        lblDato.setBounds(10, 10, 100, 25);
        // Agregarlo a la pantalla
        getContentPane().add(lblDato);

        // Caja de texto vacio
        JTextField txtDato = new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);

        // Botones Agregar y Quitar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(110, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(110, 70, 100, 25);
        getContentPane().add(btnQuitar);

        JLabel lblMuestra = new JLabel("Muestra:");
        lblMuestra.setBounds(220, 10, 100, 25);
        lblMuestra.setHorizontalAlignment(SwingConstants.CENTER);
        // Centra el contenido dentro del label.
        getContentPane().add(lblMuestra);

        // Tomamos una lista tipo de dato contenedor
        JList lstMuestra = new JList(); // Ignorar por el momento el warnig aqui
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(220, 40, 100, 150);
        getContentPane().add(spMuestra);
        // Hacemos los Bounds y el .add del ScrollPane ya no del Jlist
        // ScrollPane Agrega marco y barra de desplazamiento al Jlist

        JButton btnEstadistica = new JButton("Estadistica");
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        // JComboBox permite seleccionar un elemento de una lista de opciones.
        JComboBox cbmEstadistica = new JComboBox();

        // Definimos las opciones disponibles en el JComboBox.
        // Se declara un array de Strings con las opciones.
        String[] opciones = new String[]{"Sumatoria", "Promedio", "Desviación estándar", "Máximo", "Mínimo", "Moda"};

        // Creamos un modelo de datos (DefaultComboBoxModel) basado en el array de opciones.
        // Esto permite asignar el conjunto de datos al JComboBox.
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(opciones);
        cbmEstadistica.setModel(dcm); // Asociamos el modelo al JComboBox.

        // Configuramos la posición y el tamaño del JComboBox dentro del contenedor.
        cbmEstadistica.setBounds(110, 200, 100, 25);

        // Agregamos el JComboBox al contenido del contenedor principal.
        getContentPane().add(cbmEstadistica);

        // Esta es una caja de texto de salida.
        JTextField txtEstadistica = new JTextField();
        txtEstadistica.setBounds(220, 200, 100, 25);
        
        // Deshabilita la habilidad de digitar en una caja de texto. 
        txtEstadistica.setEnabled(false);
        getContentPane().add(txtDato);
    }
}
