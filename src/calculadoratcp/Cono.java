/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.rmi.RemoteException;

public class Cono implements InterfaceRemota{
    
    private double radio;
    private double altura;
    private double generatriz;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getGeneratriz() {
        return generatriz;
    }

    public void setGeneratriz(double generatriz) {
        this.generatriz = generatriz;
    }
    
    @Override
    public double calcularArea() throws RemoteException {
        return Math.PI*(radio*radio)+Math.PI*radio+generatriz;
    }

    @Override
    public double calcularVolumen() throws RemoteException {
        return (Math.PI*(radio*radio)*altura)/3;
    }
    
}
