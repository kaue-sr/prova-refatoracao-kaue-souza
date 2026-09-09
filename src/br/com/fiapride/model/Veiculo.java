package br.com.fiapride.model;

public class Veiculo {

    private String proprietario;
    private String placa;
    private double gasolina;
    
    public Veiculo (String proprietario, String placa) {
        this.proprietario = proprietario;
        this.placa = placa;
        this.gasolina = 0;

    }

    public void abastecer(double litros) {
        if (litros > 0) {
            gasolina = gasolina + litros;
        } else {
            System.out.println("A quantidade de litros deve ser maior que zero.");
        }
    }

    public void gastar (double litros) {
        if (litros > 0 && litros <= gasolina) {
            gasolina = gasolina - litros;    
        } else {
            System.out.println("Quantidade inválida ou maior que a gasolina disponível.");
        }
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getGasolina() {
        return gasolina;
    }
 }