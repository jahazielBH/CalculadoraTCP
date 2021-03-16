/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uv.util.ConexionDB;

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
                //variables para log en postgres
                ConexionDB con;
                PreparedStatement ps;
                String query;
               
                
                switch (operation) {
                    case 1:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();

                        area = 2 * Math.PI * radio * (altura + radio);

                        outputArea.writeDouble(area);

                        query = "INSERT INTO area_cilindro (fecha, ip, puerto, radio, altura, area) VALUES (?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }
                        
                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + ", " + "Altura = " + altura + "\tSalida : " + "Area = " + area);

                        inputRadio.close();
                        inputAltura.close();

                        outputArea.close();
                        outputVolumen.close();

                        break;
                    case 2:
                        radio = inputRadio.readDouble();

                        area = 4 * Math.PI * (radio * radio);

                        outputArea.writeDouble(area);
                        
                        query = "INSERT INTO area_esfera (fecha, ip, puerto, radio, area) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + "\tSalida : " + "Area = " + area);

                        inputRadio.close();

                        outputArea.close();

                        break;
                    case 3:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();
                        generatriz = inputGeneratriz.readDouble();

                        area = Math.PI * (radio * radio) + Math.PI * radio + generatriz;

                        outputArea.writeDouble(area);
                        
                        query = "INSERT INTO area_cono (fecha, ip, puerto, radio, altura, generatriz, area) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(generatriz));
                            ps.setString(7, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + ", " + "Altura = " + altura + ", " + "\tGeneratriz = " + generatriz + "\tSalida : " + "Area = " + area);

                        inputRadio.close();
                        inputAltura.close();
                        inputGeneratriz.close();

                        outputArea.close();

                        break;
                    case 4:
                        arista = inputArista.readDouble();

                        area = 6 * Math.pow(arista, 2);

                        outputArea.writeDouble(area);
                        
                        query = "INSERT INTO area_cubo (fecha, ip, puerto, arista, area) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(arista));
                            ps.setString(5, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Arista = " + arista + "\tSalida : " + "Area = " + area);

                        inputArista.close();

                        outputArea.close();

                        break;
                    case 5:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();

                        area = (perimetroBase * altura) + 2 * areaBase;

                        outputArea.writeDouble(area);
                        
                        query = "INSERT INTO area_prisma (fecha, ip, puerto, perimetro_base, altura, area_base, area) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(perimetroBase));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(areaBase));
                            ps.setString(7, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Perimetro Base = " + perimetroBase + ", " + "Altura = " + altura + ", " + "Area Base = " + areaBase + "\tSalidas : " + "Area = " + area);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();

                        outputArea.close();

                        break;
                    case 6:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();
                        apotema = inputApotema.readDouble();

                        area = (perimetroBase * apotema) / 2 + areaBase;

                        outputArea.writeDouble(area);
                        
                        query = "INSERT INTO area_piramide (fecha, ip, puerto, perimetro_base, altura, area_base, apotema, area) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(perimetroBase));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(areaBase));
                            ps.setString(7, Double.toString(apotema));
                            ps.setString(8, Double.toString(area));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Perimetro Base = " + perimetroBase + ", " + "Altura = " + altura + ", " + "Area Base = " + areaBase + ", " + "Apotema = " + apotema + "\tSalida : " + "Area = " + area);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();
                        inputApotema.close();

                        outputArea.close();

                        break;
                    case 7:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();

                        volumen = Math.PI * (radio * radio) * altura;

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_cilindro (fecha, ip, puerto, radio, altura, volumen) VALUES (?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + ", " + "Altura = " + altura + "\tSalida : " + "volumen = " + volumen);

                        inputRadio.close();
                        inputAltura.close();

                        outputVolumen.close();
                        break;
                    case 8:
                        radio = inputRadio.readDouble();

                        volumen = (4 / 3) * Math.PI * (radio * radio * radio);

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_esfera (fecha, ip, puerto, radio, volumen) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + "\tSalida : " + "Volumen = " + volumen);

                        inputRadio.close();

                        outputVolumen.close();

                        break;
                    case 9:
                        radio = inputRadio.readDouble();
                        altura = inputAltura.readDouble();
                        generatriz = inputGeneratriz.readDouble();

                        volumen = (Math.PI * (radio * radio) * altura) / 3;

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_cono (fecha, ip, puerto, radio, altura, generatriz, volumen) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(radio));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(generatriz));
                            ps.setString(7, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Radio = " + radio + ", " + "Altura = " + altura + ", " + "Generatriz = " + generatriz + "\tSalida : " + "Volumen = " + volumen);

                        inputRadio.close();
                        inputAltura.close();
                        inputGeneratriz.close();

                        outputVolumen.close();

                        break;
                    case 10:
                        arista = inputArista.readDouble();

                        volumen = Math.pow(arista, 3);

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_cubo (fecha, ip, puerto, arista, volumen) VALUES (?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(arista));
                            ps.setString(5, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Arista = " + arista + "\tSalida : " + "Volumen = " + volumen);

                        inputArista.close();

                        outputVolumen.close();

                        break;
                    case 11:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();

                        volumen = areaBase * altura;

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_prisma (fecha, ip, puerto, perimetro_base, altura, area_base, volumen) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(perimetroBase));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(areaBase));
                            ps.setString(7, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Perimetro Base = " + perimetroBase + ", " + "Altura = " + altura + ", " + "Area Base = " + areaBase + "\tSalida : " + "Volumen = " + volumen);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();

                        outputVolumen.close();

                        break;
                    case 12:
                        perimetroBase = inputPerimetroBase.readDouble();
                        altura = inputAltura.readDouble();
                        areaBase = inputAreaBase.readDouble();
                        apotema = inputApotema.readDouble();

                        volumen = (areaBase * altura) / 3;

                        outputVolumen.writeDouble(volumen);
                        
                        query = "INSERT INTO volumen_piramide (fecha, ip, puerto, perimetro_base, altura, area_base, apotema, volumen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        
                        try {
                            con = ConexionDB.getInstace();
                            ps = con.getConnection().prepareStatement(query);
                            ps.setString(1, formatter.format(new Date()));
                            ps.setString(2, ipRemitente.toString());
                            ps.setString(3, String.valueOf(puertoRemitente));
                            ps.setString(4, Double.toString(perimetroBase));
                            ps.setString(5, Double.toString(altura));
                            ps.setString(6, Double.toString(areaBase));
                            ps.setString(7, Double.toString(apotema));
                            ps.setString(8, Double.toString(volumen));
                            ps.execute();
                        } catch (SQLException e) {
                            Logger.getLogger(ServerCalculadora.class.getName()).log(Level.SEVERE, null, e);
                        }

                        System.out.println(formatter.format(new Date()) + "\tCliente = "
                                + ipRemitente + " : " + puertoRemitente + "\tEntrada : "
                                + "Perimetro Base = " + perimetroBase + ", " + "Altura = " + altura + ", " + "Area Base = " + areaBase + ", " + "Apotema = " + apotema + "\tSalida : " + "Volumen = " + volumen);

                        inputPerimetroBase.close();
                        inputAltura.close();
                        inputAreaBase.close();
                        inputApotema.close();

                        outputVolumen.close();

                        break;
                    case 13:
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
