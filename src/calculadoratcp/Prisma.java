/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoratcp;

import java.rmi.RemoteException;

public class Prisma implements InterfaceRemota {
    
    private double perimetroBase;
    private double altura;
    private double areaBase;

    public double getPerimetroBase() {
        return perimetroBase;
    }

    public void setPerimetroBase(double perimetroBase) {
        this.perimetroBase = perimetroBase;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getAreaBase() {
        return areaBase;
    }

    public void setAreaBase(double areaBase) {
        this.areaBase = areaBase;
    }

    @Override
    public double calcularArea() throws RemoteException {
        return (perimetroBase*altura) + 2*areaBase;
    }

    @Override
    public double calcularVolumen() throws RemoteException {
        return areaBase * altura;
    }
    
}
