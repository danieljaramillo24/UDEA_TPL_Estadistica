import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame; // Modulo para ventanas y formularios.
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

// Extendemos el modulo.
public class FrmEstadistica extends JFrame {

    // Declaración de la variables de forma global fuera del constructor.
    private JTextField txtDato;
    private JList lstMuestra;
    private JComboBox cmbEstadistica;
    private JTextField txtEstadistica;

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
        // Declarada globalmente en la parte superior.
        txtDato = new JTextField();
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
        lstMuestra = new JList(); // Ignorar por el momento el warnig aqui
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(220, 40, 100, 150);
        getContentPane().add(spMuestra);
        // Hacemos los Bounds y el .add del ScrollPane ya no del Jlist
        // ScrollPane Agrega marco y barra de desplazamiento al Jlist

        JButton btnEstadistica = new JButton("Estadistica");
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        // JComboBox permite seleccionar un elemento de una lista de opciones.
        cmbEstadistica = new JComboBox();

        // Definimos las opciones disponibles en el JComboBox.
        // Se declara un array de Strings con las opciones.
        String[] opciones = new String[] { "Sumatoria", "Promedio", "Desviación estándar", "Máximo", "Mínimo", "Moda" };

        // Creamos un modelo de datos (DefaultComboBoxModel) basado en el array de
        // opciones.
        // Esto permite asignar el conjunto de datos al JComboBox.
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(dcm); // Asociamos el modelo al JComboBox.

        // Configuramos la posición y el tamaño del JComboBox dentro del contenedor.
        cmbEstadistica.setBounds(110, 200, 100, 25);

        // Agregamos el JComboBox al contenido del contenedor principal.
        getContentPane().add(cmbEstadistica);

        // Esta es una caja de texto de salida.
        txtEstadistica = new JTextField();
        txtEstadistica.setBounds(220, 200, 100, 25);

        // Deshabilita la habilidad de digitar en una caja de texto.
        // set editable es otra forma diferente de, se puede editar la caja y mejora al
        // go el aspecto
        txtEstadistica.setEditable(false);
        getContentPane().add(txtEstadistica);

        // Crear eventos de los botones.
        // addActionListener => Se crea a la espera de que suceda un evento.
        btnAgregar.addActionListener(new ActionListener() {

            // Se requiere el método abstrato actionPerformed para poner una acción
            // ActionEvent
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se debe implementar la funcionalidad del botón "Agregar".
                // Puede incluir operaciones como agregar datos a una lista, actualizar la UI,
                // etc.

                // Instanciamos el método agregarDato
                agregarDato();
            }

        });

        btnQuitar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDato();
            }
        });

        btnEstadistica.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadistica();
            }

        });

    }

    // Ya creamos las acciones al presionar los botones. Ahora enlacemolo con sus
    // funcionalidades creemos métodos para estoy se los pasamos.

    // Declaración de variables Globales
    private int MAXIMO_DATOS = 1000;

    // La siguiente varaible nos dice, estamos declarando un vector que contiene
    // datos tipo Real (double)
    private double[] muestra = new double[MAXIMO_DATOS];
    private int totalDatos = 0;

    private void mostrarDatos() {
        // Declaramos otro vector de string, al que queremos ir agregando lo que ya
        // tenemos en muestra.
        String[] strMuestra = new String[totalDatos];
        for (int i = 0; i < totalDatos; i++) {
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        // Los JList tiene el método setListData para que le permitan agregar datos.
        // Se actualiza el contenido de la lista "lstMuestra" con los datos almacenados
        // en el arreglo "strMuestra".
        lstMuestra.setListData(strMuestra);

    }

    private void agregarDato() {
        if (totalDatos < MAXIMO_DATOS) {
            // Agregamos el dato que digitamos en la cada de texto para esto, al vector de
            // reales muestra y aumentamos el contador de los datos para actualizar la
            // posición.
            // Utilizamos para obtener ese texto de la caja el método getText
            muestra[totalDatos] = Double.parseDouble(txtDato.getText());
            totalDatos++;
            // Instanciamos el método para mostar los datos
            mostrarDatos();
        } else {
            // Crea un cuadro de dialogo con un mensaje.
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas datos.");

        }
    }

    private void quitarDato() {
        // getSelectedIndex sirve para leer listas debe tener elementos.
        // Al tocar en elemento de lstMuestra en pantalla nos retorna el Index de ese
        // elemento en la lista si no se selecciono nada dispara el else.

        // Nota. Eliminar consiste en recorrer el elemento con base a su indice,
        // eliminarlo y eliminar una posición de la lista. Las posiciones se desplazan a
        // la izquierda.
        if (lstMuestra.getSelectedIndex() >= 0) {
            // Recorre los elementos trayendo el indice hasta la íltima posición
            // Con paso de uno.
            // Vamos hasta la penultima para poder que no salgamos del vector.
            for (int i = lstMuestra.getSelectedIndex(); i < totalDatos - 1; i++) {
                // Muevo el elemento siguiente a la posición anterior en el array
                muestra[i] = muestra[i + 1];
            }
            totalDatos--; // Como vamos a eliminar una posición debemos reducir el tamaño del vector.
            // Volvemos a mostrar el array actualizado.
            mostrarDatos();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el objeto a retirar.");
        }
    }

    private double sumatoria() {
        // Definimos una variable acumuladora
        double suma = 0;
        for (int i = 0; i < totalDatos; i++) {
            suma += muestra[i];
        }
        return suma;
    }

    private double desviacionEstandar() {
        // método para la desviación estandar
        // Guardamos el promedio que es constante
        if (totalDatos > 1) {
            double promedioActual = promedio();
            double suma = 0;
            for (int i = 0; i < totalDatos; i++) {
                suma += Math.abs(promedioActual - muestra[i]);
            }
            return suma / (totalDatos - 1);
        } else {
            return 0;
        }
    }

    // Como hallar el maximo de un conjunto de datos.
    // Tomamos un dato y decimos que es el mayor y lo comparamos con el siguiente si
    // resulta ser mayor, volvemos a este el mayor, luego pasamos al siguiente hasta
    // todos los datos.
    private double maximo() {
        if (totalDatos > 0) {
            double actualMaximo = muestra[0];
            for (int i = 1; i < totalDatos; i++) {
                if (muestra[i] > actualMaximo) {
                    actualMaximo = muestra[i];
                }
            }
            return actualMaximo;
        } else {
            return 0;
        }
    }

    private double minimo() {
        {
            if (totalDatos > 0) {
                double actualMinimo = muestra[0];
                for (int i = 1; i < totalDatos; i++) {
                    if (muestra[i] < actualMinimo) {
                        actualMinimo = muestra[i];
                    }
                }
                return actualMinimo;
            } else {
                return 0;
            }
        }
    }

    private double moda() {
        if (totalDatos == 0) {
            return 0;
        }
    
        double moda = muestra[0];
        int maxFrecuencia = 1;
    
        for (int i = 0; i < totalDatos; i++) {
            int frecuencia = 1;
            for (int j = i + 1; j < totalDatos; j++) {
                if (muestra[i] == muestra[j]) {
                    frecuencia++;
                }
            }
    
            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                moda = muestra[i];
            }
        }
    
        return moda;
    }
    
    private double promedio() {
        return totalDatos > 0 ? sumatoria() / totalDatos : 0;
        // Recordemos se lee, si total datos es mayor que 0 (? == entonces) sumatoria()
        // / totalDatos esto es el promedio, :0 == Sino retorne 0
    }

    private void calcularEstadistica() {
        switch (cmbEstadistica.getSelectedIndex()) {
            case 0:
                // A la caja de texto le vamos a setear asinar, el valor de la sumatoria.
                // Convertimos el tipo de dato la caja de texto solo recibe string.
                txtEstadistica.setText(String.valueOf(sumatoria()));
                break;
            case 1:
                txtEstadistica.setText(String.valueOf(promedio()));
                break;
            case 2:
                txtEstadistica.setText(String.valueOf(desviacionEstandar()));
                break;
            case 3:
                txtEstadistica.setText(String.valueOf(maximo()));
                break;
            case 4:
                txtEstadistica.setText(String.valueOf(minimo()));
                break;
            case 5:
                txtEstadistica.setText(String.valueOf(moda()));
                break;


        }
    }
}
