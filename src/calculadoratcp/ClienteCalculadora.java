/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteCalculadora {

    private static final int _PUERTO = 1234;
    private static final String _IP = "25.3.213.132";

    public static void main(String[] args) throws IOException {
        InetAddress ipServidor = null;
        boolean res = true;
        Scanner sc = new Scanner(System.in);

        try {
            ipServidor = InetAddress.getByName(_IP);
        } catch (UnknownHostException uhe) {
            System.err.println("Host no encontrado : " + uhe);
            System.exit(-1);
        }

        do {
            Socket socketCliente = null;
            DataInputStream inputArea = null;
            DataInputStream inputVolumen = null;
            DataOutputStream outputOpcion = null;
            DataOutputStream outputRadio = null;
            DataOutputStream outputAltura = null;
            DataOutputStream outputGeneratriz = null;
            DataOutputStream outputArista = null;
            DataOutputStream outputPerimetroBase = null;
            DataOutputStream outputAreaBase = null;
            DataOutputStream outputApotema = null;         
            int operation;
            double radio;
            double altura;
            double generatriz;
            double arista;
            double perimetroBase;
            double areaBase;
            double apotema;
            double area;
            double volumen;
            System.out.println("CALCULAR AREA ELIGA UNA FIGURA GEOMETRICA");
            System.out.println("(1) CILINDRO");
            System.out.println("(2) ESFERA");
            System.out.println("(3) CONO");
            System.out.println("(4) CUBO");
            System.out.println("(5) PRISMA");
            System.out.println("(6) PIRAMIDE");
            System.out.println("CALCULAR VOLUMEN ELIGA UNA FIGURA GEOMETRICA");
            System.out.println("(7) CILINDRO");
            System.out.println("(8) ESFERA");
            System.out.println("(9) CONO");
            System.out.println("(10) CUBO");
            System.out.println("(11) PRISMA");
            System.out.println("(12) PIRAMIDE");
            System.out.println("(13) Salir");
            System.out.println("Ingresar Opcion: ");
            operation = sc.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("Calculando Area de CILINDRO");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();

                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());

                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);
                    outputAltura.writeDouble(altura);

                    area = inputArea.readDouble();

                    System.out.println("Entrada : " + "Radio = " + radio + ", " + "Altura = " + altura +"\tSalida : Area = " + area + "\n");
                    
                    inputArea.close();

                    outputOpcion.close();
                    outputAltura.close();
                    outputRadio.close();
                    break;
                case 2:
                    System.out.println("Calculando Area de ESFERA");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();

                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());

                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);

                    area = inputArea.readDouble();

                    System.out.println("Entrada : " + "Radio = " + radio + "\tSalida : Area = " + area + "\n");
                    
                    inputArea.close();

                    outputOpcion.close();
                    outputRadio.close();
                    break;
                case 3:
                    System.out.println("Calculando Area de CONO");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingresa la generatriz: ");
                    generatriz = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputGeneratriz = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);
                    outputAltura.writeDouble(altura);
                    outputGeneratriz.writeDouble(generatriz);

                    area = inputArea.readDouble();

                    System.out.println("Entrada : " + "Radio = " + radio + ", Altura = " + altura + ", Generatriz = " + generatriz +"\tSalida : Area = " + area + "\n");
                    
                    inputArea.close();

                    outputOpcion.close();
                    outputAltura.close();
                    outputRadio.close();
                    outputGeneratriz.close();
                    break;
                case 4:
                    System.out.println("Calculando Area de CUBO");
                    System.out.println("Ingresa la arista: ");
                    arista = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputArista = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputArista.writeDouble(arista);

                    area = inputArea.readDouble();

                    System.out.println("Entrada : " + "Arista = " + arista +"\tSalida : Area = " + area + "\n");
                    
                    inputArea.close();

                    outputOpcion.close();
                    outputArista.close();
                    break;
                case 5:
                    System.out.println("Calculando Area de PRISMA");
                    System.out.println("Ingrese el perimetro base: ");
                    perimetroBase = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingrese la area base: ");
                    areaBase = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputPerimetroBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputAreaBase = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputPerimetroBase.writeDouble(perimetroBase);
                    outputAltura.writeDouble(altura);
                    outputAreaBase.writeDouble(areaBase);

                    area = inputArea.readDouble();
                    
                    System.out.println("Entrada : " + "Perimetro Base = " + perimetroBase + ", Altura = " + altura + ", Area Base = " + areaBase + "\tSalida : Area = " + area);
                    inputArea.close();

                    outputOpcion.close();
                    outputPerimetroBase.close();
                    outputAltura.close();
                    outputAreaBase.close();
                    break;
                case 6:
                    System.out.println("Calculando Area de PIRAMIDE");
                    System.out.println("Ingrese el perimetro base: ");
                    perimetroBase = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingrese la area base: ");
                    areaBase = sc.nextDouble();
                    System.out.println("Ingrese la apotema: ");
                    apotema = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputArea = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputPerimetroBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputAreaBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputApotema = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputPerimetroBase.writeDouble(perimetroBase);
                    outputAltura.writeDouble(altura);
                    outputAreaBase.writeDouble(areaBase);
                    outputApotema.writeDouble(apotema);
                    
                    area = inputArea.readDouble();
                    
                    System.out.println("Entrada : " + "Perimetro Base = " + perimetroBase + ", Altura = " + altura + ", Area Base = " + areaBase + ", Apotema = " + apotema +"\tSalida : Area = " + area);
                    inputArea.close();

                    outputOpcion.close();
                    outputPerimetroBase.close();
                    outputAltura.close();
                    outputAreaBase.close();
                    outputApotema.close();
                    break;
                case 7:
                    System.out.println("Calculando Volumen del CILINDRO");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();

                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());

                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);
                    outputAltura.writeDouble(altura);

                    volumen = inputVolumen.readDouble();
                    
                    System.out.println("Entrada : " + "Radio = " + radio + ", " + "Altura = " + altura +"\tSalida : Volumen = " + volumen + "\n");

                    inputVolumen.close();

                    outputOpcion.close();
                    outputAltura.close();
                    outputRadio.close();
                    break;
                case 8:
                    System.out.println("Calculando Volumen de ESFERA");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();

                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());

                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);

                    volumen = inputVolumen.readDouble();

                    System.out.println("Entrada : " + "Radio = " + radio + "\tSalida : Volumen = " + volumen + "\n");

                    inputVolumen.close();

                    outputOpcion.close();
                    outputRadio.close();
                    break;
                case 9:
                    System.out.println("Calculando Volumen de CONO");
                    System.out.println("Ingresa el radio: ");
                    radio = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingresa la generatriz: ");
                    generatriz = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputRadio = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputGeneratriz = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputRadio.writeDouble(radio);
                    outputAltura.writeDouble(altura);
                    outputGeneratriz.writeDouble(generatriz);

                    volumen = inputVolumen.readDouble();

                    System.out.println("Entrada : " + "Radio = " + radio + ", Altura = " + altura + ", Generatriz = " + generatriz +"\tSalida : Volumen = " + volumen + "\n");
                    
                    inputVolumen.close();

                    outputOpcion.close();
                    outputAltura.close();
                    outputRadio.close();
                    outputGeneratriz.close();
                    break;
                case 10:
                    System.out.println("Calculando Volumen de CUBO");
                    System.out.println("Ingresa la arista: ");
                    arista = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputArista = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputArista.writeDouble(arista);

                    volumen = inputVolumen.readDouble();

                    System.out.println("Entrada : " + "Arista = " + arista + "\tSalida : " + "Volumen = "+ volumen);
                    
                    inputVolumen.close();

                    outputOpcion.close();
                    outputArista.close();
                    break;
                case 11:
                    System.out.println("Calculando Volumen de PRISMA");
                    System.out.println("Ingrese el perimetro base: ");
                    perimetroBase = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingrese la area base: ");
                    areaBase = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);
                    
                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputPerimetroBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputAreaBase = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputPerimetroBase.writeDouble(perimetroBase);
                    outputAltura.writeDouble(altura);
                    outputAreaBase.writeDouble(areaBase);

                    volumen = inputVolumen.readDouble();

                    System.out.println("Entrada : " + "Perimetro Base = " + perimetroBase + ", Altura = " + altura + ", Area Base = " + areaBase + "\tSalida : Volumen = " + volumen);

                    inputVolumen.close();

                    outputOpcion.close();
                    outputPerimetroBase.close();
                    outputAltura.close();
                    outputAreaBase.close();
                    break;
                case 12:
                    System.out.println("Calculando Volumen de PIRAMIDE");
                    System.out.println("Ingrese el perimetro base: ");
                    perimetroBase = sc.nextDouble();
                    System.out.println("Ingresa la altura: ");
                    altura = sc.nextDouble();
                    System.out.println("Ingrese la area base: ");
                    areaBase = sc.nextDouble();
                    System.out.println("Ingrese la apotema: ");
                    apotema = sc.nextDouble();
                    
                    socketCliente = new Socket(ipServidor, _PUERTO);

                    inputVolumen = new DataInputStream(socketCliente.getInputStream());

                    outputOpcion = new DataOutputStream(socketCliente.getOutputStream());
                    outputPerimetroBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputAltura = new DataOutputStream(socketCliente.getOutputStream());
                    outputAreaBase = new DataOutputStream(socketCliente.getOutputStream());
                    outputApotema = new DataOutputStream(socketCliente.getOutputStream());
                    
                    outputOpcion.writeInt(operation);
                    outputPerimetroBase.writeDouble(perimetroBase);
                    outputAltura.writeDouble(altura);
                    outputAreaBase.writeDouble(areaBase);
                    outputApotema.writeDouble(apotema);
                    
                    volumen = inputVolumen.readDouble();

                    System.out.println("Entrada : " + "Perimetro Base = " + perimetroBase + ", Altura = " + altura + ", Area Base = " + areaBase + ", Apotema = " + apotema +"\tSalida : Volumen = " + volumen);

                    inputVolumen.close();

                    outputOpcion.close();
                    outputPerimetroBase.close();
                    outputAltura.close();
                    outputAreaBase.close();
                    outputApotema.close();
                    break;
                case 13:
                    res = false;                      
            }
        } while (res == true);

    }
}
