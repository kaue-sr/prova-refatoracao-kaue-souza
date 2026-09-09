# FiapRide - Módulo de Frota

Prática de Programação Orientada a Objetos — refatoração de código legado aplicando Clean Code e Encapsulamento.

---

## Sobre o Projeto

### Contexto

Um estagiário criou o sistema de cadastro de carros do FiapRide. O código funcionava, mas tinha problemas graves de segurança, nomenclatura e arquitetura. O objetivo desse projeto foi analisar, corrigir e blindar esse código legado.

---

## Código Original

### Classe `veiculos`

```java
package br.com.fiapride.model;

public class veiculos {

    public String individuo;
    public String pl;
    public int gas;

    public void adicionar(int v) {
        gas = gas + v;
    }

    public void gasta(double v) {
        gas = gas - v;
    }
}
```

### Classe `principal`

```java
package br.com.fiapride.main;

import br.com.fiapride.model.veiculos;

public class principal {
    public static void main(String[] args) {

        veiculos v1 = new veiculos();
        v1.individuo = "Carlos";
        v1.pl = "ABC-1234";
        v1.gas = -10;
        v1.adicionar(50);
        v1.gasta(100);

        System.out.println("Dono: " + v1.individuo + " | Placa: " + v1.pl + " | Gasolina: " + v1.gas);
    }
}
```

### Problemas Identificados

| # | Problema | Motivo |
|---|---|---|
| 1 | `class veiculos` | Nome deveria ser PascalCase e singular |
| 2 | Atributos `public` | Quebra o encapsulamento |
| 3 | Nomes `individuo`, `pl`, `gas` | Não comunicam o que representam |
| 4 | `gas` do tipo `int` | Combustível deveria ser `double` |
| 5 | Sem validação | Permite gasolina negativa e gastos acima do disponível |

---

## Código Refatorado

### Classe `Veiculo`

```java
package br.com.fiapride.model;

public class Veiculo {

    private String proprietario;
    private String placa;
    private double gasolina;

    public Veiculo(String proprietario, String placa) {
        this.proprietario = proprietario;
        this.placa = placa;
        this.gasolina = 0;
    }

    public void abastecer(double litros) {
        if (litros > 0) {
            gasolina = gasolina + litros;
        } else {
            System.out.println("ERRO: A quantidade de litros deve ser maior que zero.");
        }
    }

    public void gastar(double litros) {
        if (litros > 0 && litros <= gasolina) {
            gasolina = gasolina - litros;
        } else {
            System.out.println("ERRO: Quantidade inválida ou maior que a gasolina disponível.");
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
```

### Classe `SistemaPrincipal`

```java
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
```

---

## O que foi corrigido

- Classes renomeadas para PascalCase e singular
- Atributos convertidos para `private`
- Nomes reescritos de forma clara (`proprietario`, `placa`, `gasolina`, `abastecer`, `gastar`)
- `gasolina` padronizada como `double`
- Construtor exigindo `proprietario` e `placa` na criação
- Validação em `abastecer` e `gastar`
- Sem `setGasolina`: o combustível só muda através dos métodos de negócio

---

## Estrutura do projeto

```
src/
└── br/com/fiapride/
    ├── model/
    │   └── Veiculo.java
    └── main/
        └── SistemaPrincipal.java
diagrama-veiculo-refatorado.png
```

---


### Saída esperada

```
Gasolina após abastecer: 50.0
ERRO: Quantidade inválida ou maior que a gasolina disponível.
Gasolina após tentativa de gastar 100: 50.0
Gasolina após gastar 30: 20.0
Proprietário: Kaue | Placa: POO-1234 | Gasolina: 20.0
```

