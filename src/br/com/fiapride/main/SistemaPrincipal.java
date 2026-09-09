package br.com.fiapride.main;

import br.com.fiapride.model.Veiculo;

public class SistemaPrincipal {

    public static void main(String[] args) {

        Veiculo v1 = new Veiculo("Kaue", "POO-1234");

        v1.abastecer(50);
        System.out.println("Gasolina após abastecer: " + v1.getGasolina());

        v1.gastar(100);
        System.out.println("Gasolina após tentativa de gastar 100: " + v1.getGasolina());

        v1.gastar(30); 
        System.out.println("Gasolina após gastar 30: " + v1.getGasolina());

        System.out.println("Proprietário: " + v1.getProprietario() + " | Placa: " + v1.getPlaca() + " | Gasolina: " + v1.getGasolina());
    }
}