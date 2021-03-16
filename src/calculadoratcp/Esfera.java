/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.rmi.RemoteException;

public class Esfera implements InterfaceRemota {
    
    private double radio;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() throws RemoteException {
        return 4*Math.PI*(radio*radio);
    }

    @Override
    public double calcularVolumen() throws RemoteException {
        return (4/3)*Math.PI*(radio*radio*radio);
    }
    
    
}
