/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerCalculadora {

    private static final String _IP = "25.3.213.132";
    private static final int _PORT = 1234;
    private static final int _BACKLOG = 50;

    public static void main(String[] args) throws UnknownHostException {

        ServerSocket server = null;
        Socket socket = null;
        DataInputStream inputOpcion, inputArista, inputRadio, inputAltura, inputGeneratriz, inputPerimetroBase, inputAreaBase, inputApotema;
        DataOutputStream outputArea, outputVolumen;

        InetAddress ip = InetAddress.getByName(_IP);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            System.out.println("IP de LocalHost = " + InetAddress.getLocalHost().toString());
            System.out.println("\nEscuhando en : ");
            System.out.println("IP Host = " + ip.getHostAddress());
            System.out.println("Puerto = " + _PORT);
        } catch (UnknownHostException uhe) {
            System.err.println("No puedo saber la direccion IP local : " + uhe);
        }

        try {
            server = new ServerSocket(_PORT, _BACKLOG, ip);
        } catch (IOException ioe) {
            System.err.println("Error al abrir el socket de servidor : " + ioe);
            System.exit(-1);
        }

        while (true) {
            try {
                socket = server.accept();

                inputOpcion = new DataInputStream(socket.getInputStream());
                inputArista = new DataInputStream(socket.getInputStream());
                inputRadio = new DataInputStream(socket.getInputStream());
                inputAltura = new DataInputStream(socket.getInputStream());
                inputGeneratriz = new DataInputStream(socket.getInputStream());
                inputPerimetroBase = new DataInputStream(socket.getInputStream());
                inputAreaBase = new DataInputStream(socket.getInputStream());
                inputApotema = new DataInputStream(socket.getInputStream());

                outputArea = new DataOutputStream(socket.getOutputStream());
                outputVolumen = new DataOutputStream(socket.getOutputStream());

                int puertoRemitente = socket.getPort();

                InetAddress ipRemitente = socket.getInetAddress();

                double radio;
                double altura;
                double generatriz;
                double arista;
                double perimetroBase;
                double areaBase;
                double apotema;
                double area;
                double volumen;
                boolean res = true;

                int operation = inputOpcion.readInt();

                switch (operation) {
                    case 1:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();

                        area = 2 * Math.PI * radio * (altura + radio);
                        volumen = Math.PI * (radio * radio) * altura;

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Radio = " + radio + "," + "\tAltura = " + altura + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputRadio.close();
                        inputAltura.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 2:
                        radio = inputRadio.readDouble();

                        area = 4 * Math.PI * (radio * radio);
                        volumen = (4 / 3) * Math.PI * (radio * radio * radio);

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Radio = " + radio + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputRadio.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 3:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();
                        generatriz = inputGeneratriz.readDouble();

                        area = Math.PI * (radio * radio) + Math.PI * radio + generatriz;
                        volumen = (Math.PI * (radio * radio) * altura) / 3;

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Radio = " + radio + "," + "\tAltura = " + altura + "\tGeneratriz = " + generatriz + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputRadio.close();
                        inputAltura.close();
                        inputGeneratriz.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 4:
                        arista = inputArista.readDouble();

                        area = 6 * Math.pow(arista, 2);
                        volumen = Math.pow(arista, 3);

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Arista = " + arista + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputArista.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 5:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();

                        area = (perimetroBase * altura) + 2 * areaBase;
                        volumen = areaBase * altura;

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Perimetro Base = " + perimetroBase + "," + "\tAltura = " + altura + "\tArea Base = " + areaBase + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 6:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();
                        apotema = inputApotema.readDouble();

                        area = (perimetroBase * apotema) / 2 + areaBase;
                        volumen = (areaBase * altura) / 3;

                        outputArea.writeDouble(area);
                        outputVolumen.writeDouble(volumen);

                        System.out.println(formatter.format(new Date()) + "Cliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntradas : "
                                + "Perimetro Base = " + perimetroBase + "," + "\tAltura = " + altura + "\tArea Base = " + areaBase + "\tApotema = " + apotema + "\tSalidas : " + "Area = " + area + "\tVolumen = " + volumen);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();
                        inputApotema.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 7:
                        res = false;
                }

                inputOpcion.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Se ha producido la excepcion : " + e);
            }
        }
    }
}
