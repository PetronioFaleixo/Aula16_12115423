import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pizza {
    private String sabor;
    private double preco;

    public Pizza(String sabor, double preco) {
        this.sabor = sabor;
        this.preco = preco;
    }

    public String getSabor() {
        return sabor;
    }

    public double getPreco() {
        return preco;
    }
}

class CarrinhoItem {
    private Pizza pizza;
    private int quantidade;

    public CarrinhoItem(Pizza pizza, int quantidade) {
        this.pizza = pizza;
        this.quantidade = quantidade;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularPrecoTotal() {
        return pizza.getPreco() * quantidade;
    }
}

class Carrinho {
    private List<CarrinhoItem> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(CarrinhoItem item) {
        itens.add(item);
    }

    public void removerItem(int index) {
        itens.remove(index);
    }

    public List<CarrinhoItem> getItens() {
        return itens;
    }

    public double calcularPrecoTotal() {
        double precoTotal = 0;
        for (CarrinhoItem item : itens) {
            precoTotal += item.calcularPrecoTotal();
        }
        return precoTotal;
    }
}

class Pedido {
    private Carrinho carrinho;
    private String endereco;
    private String telefone;
    private String metodoPagamento;

    public Pedido(Carrinho carrinho, String endereco, String telefone, String metodoPagamento) {
        this.carrinho = carrinho;
        this.endereco = endereco;
        this.telefone = telefone;
        this.metodoPagamento = metodoPagamento;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }
}

class Pizzaria {
    private List<Pizza> pizzas;

    public Pizzaria() {
        this.pizzas = new ArrayList<>();
        // Adicione os sabores de pizza disponíveis com seus respectivos preços
        pizzas.add(new Pizza("Calabresa", 25.0));
        pizzas.add(new Pizza("Margherita", 30.0));
        pizzas.add(new Pizza("Quatro Queijos", 28.0));
        pizzas.add(new Pizza("Frango com Catupiry", 32.0));
        pizzas.add(new Pizza("Portuguesa", 29.0));
        pizzas.add(new Pizza("Pepperoni", 31.0));
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}

