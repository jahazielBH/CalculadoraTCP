/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.rmi.RemoteException;

public class Cubo implements InterfaceRemota {

    private double arista;

    public double getArista() {
        return arista;
    }

    public void setArista(double arista) {
        this.arista = arista;
    }
    
    @Override
    public double calcularArea() throws RemoteException {
        return 6*Math.pow(arista,2);
    }

    @Override
    public double calcularVolumen() throws RemoteException {
        return Math.pow(arista, 3);
    }
    
}
