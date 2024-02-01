//Importaciones necesarias

import java.io.*;
import java.net.*;
import java.util.Scanner;


void main() {

    //declaracion de arreglo con la ip del servidor Echo de internet
    byte[] byteIp = {52, 43, 121, 77};

    // numero de puerto del servidor
    int puerto = 9002;

    //objeto de tipo InetAddress que representa la ip del servidor
    InetAddress ip = null;

    //objeto de tipo Socket que va a permitir la conexión con el servidor
    Socket socket = null;

    //objeto de tipo Buffered reader que permite leer la respuesta del servidor
    BufferedReader sockInput = null;

    //objeto de tipo PrintWritter que va a permitir enviar informacion al servidor
    PrintWriter socketOuput = null;

    //manejar la posible excepcion que arroja el metodo getByAddress
    try {
        // obteniendo un objeto de tipo InetByAddress (representacion de la ip del servidor)
        ip = InetAddress.getByAddress(byteIp);
    } catch (UnknownHostException e) {
        e.printStackTrace(System.out);
    }

    //manejar la posble excepcion que arroja el constructor Socket
    try {
        // creando un socket a partir de la direccion ip y el puerto, permite hacer la concexión
        // de este socket cliente al socket del servidor
        socket = new Socket(ip, puerto);

    } catch (IOException e) {
        e.printStackTrace(System.out);
    }

    //manejar las posibles excepciones que arrojan los metodos Input y Output Stream
    try {
        //objeto que permite leer la respuesta del servidor
        //Se utiliza para leer la respuesta del servidor desde el flujo de entrada del socket.
        sockInput = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        //objeto que permite enviar algo al servidor
        //Se utiliza para enviar mensajes al servidor a través del flujo de salida del socket.
        //El parámetro true habilita la autoflush, que garantiza que los datos se envíen inmediatamente.
        socketOuput = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
        e.printStackTrace(System.out);
    }

    //objeto Scanner para leer la consola
    Scanner consola = new Scanner(System.in);

    String lecturaDeConsola;
    System.out.println("Escribe lo que sea! ");
    boolean saludo = true;
    String x;
    while (true) {

        //mostrar la respuesta del servidor
        try {
            // si es el saludo inicial del servidor muestra solo el saludo
            // si no es así, a la hora le agrega el texto "La hora es: "
            x = (saludo) ? sockInput.readLine() : STR."La hora es: \{sockInput.readLine()}";

            saludo = false;

            System.out.println(x);

            lecturaDeConsola = consola.nextLine();

            if (lecturaDeConsola.isBlank()) {
                System.out.print("Ya no escribiste nada, ejercicio finalizado!");
                break;
            }
            socketOuput.println(lecturaDeConsola);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}