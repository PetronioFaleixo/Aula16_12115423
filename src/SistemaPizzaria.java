import java.util.List;
import java.util.Scanner;

public class SistemaPizzaria {
    private Pizzaria pizzaria;
    private Carrinho carrinho;

    public SistemaPizzaria() {
        this.pizzaria = new Pizzaria();
        this.carrinho = new Carrinho();
    }

    public void exibirPaginaInicial() {
        System.out.println("=== Bem-vindo à Pizzaria ===");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Sair");
    }

    public void exibirPaginaPedidos() {
        System
                .out.println("=== Sabores de Pizza ===");
        List<Pizza> pizzas = pizzaria.getPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            System.out.println((i + 1) + ". " + pizza.getSabor() + " - R$" + pizza.getPreco());
        }
        System.out.println("0. Voltar");
    }

    public void fazerPedido(int saborIndex, int quantidade) {
        List<Pizza> pizzas = pizzaria.getPizzas();
        if (saborIndex >= 0 && saborIndex < pizzas.size()) {
            Pizza pizzaSelecionada = pizzas.get(saborIndex);
            CarrinhoItem item = new CarrinhoItem(pizzaSelecionada, quantidade);
            carrinho.adicionarItem(item);
            System.out.println("Pedido adicionado ao carrinho!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void exibirCarrinho() {
        List<CarrinhoItem> itens = carrinho.getItens();
        System.out.println("=== Carrinho de Compras ===");
        for (int i = 0; i < itens.size(); i++) {
            CarrinhoItem item = itens.get(i);
            System.out.println((i + 1) + ". " + item.getPizza().getSabor() + " - "
                    + item.getQuantidade() + " x R$" + item.getPizza().getPreco()
                    + " = R$" + item.calcularPrecoTotal());
        }
        System.out.println("Total: R$" + carrinho.calcularPrecoTotal());
        System.out.println("1. Remover item");
        System.out.println("2. Atualizar quantidade");
        System.out.println("3. Finalizar pedido");
        System.out.println("0. Voltar");
    }

    public void removerItemCarrinho(int index) {
        List<CarrinhoItem> itens = carrinho.getItens();
        if (index >= 0 && index < itens.size()) {
            carrinho.removerItem(index);
            System.out.println("Item removido do carrinho!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void atualizarQuantidadeItemCarrinho(int index, int novaQuantidade) {
        List<CarrinhoItem> itens = carrinho.getItens();
        if (index >= 0 && index < itens.size()) {
            CarrinhoItem item = itens.get(index);
            item = new CarrinhoItem(item.getPizza(), novaQuantidade);
            itens.set(index, item);
            System.out.println("Quantidade do item atualizada!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void exibirPaginaCheckout() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Checkout ===");
        System.out.println("Por favor, forneça as informações de entrega:");
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Selecione o método de pagamento:");
        System.out.println("1. Dinheiro");
        System.out.println("2. Cartão de crédito");
        int opcaoPagamento = scanner.nextInt();
        String metodoPagamento = opcaoPagamento == 1 ? "Dinheiro" : "Cartão de crédito";

        Pedido pedido = new Pedido(carrinho, endereco, telefone, metodoPagamento);
        System.out.println("Pedido finalizado!");
        System
                .out.println("Endereço de entrega: " + pedido.getEndereco());
        System.out.println("Telefone de contato: " + pedido.getTelefone());
        System.out.println("Método de pagamento: " + pedido.getMetodoPagamento());
        System.out.println("Total do pedido: R$" + carrinho.calcularPrecoTotal());
        // Limpar carrinho após finalizar o pedido
        carrinho = new Carrinho();
    }

    public void executar() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            exibirPaginaInicial();
            int opcaoInicial = scanner.nextInt();

            switch (opcaoInicial) {
                case 1:
                    boolean voltar = false;
                    while (!voltar) {
                        exibirPaginaPedidos();
                        int opcaoPedidos = scanner.nextInt();
                        if (opcaoPedidos == 0) {
                            voltar = true;
                        } else {
                            System.out.print("Selecione a quantidade desejada: ");
                            int quantidade = scanner.nextInt();
                            fazerPedido(opcaoPedidos - 1, quantidade);
                        }
                    }
                    break;
                case 2:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }

    }

    public static void main(String[] args) {
        SistemaPizzaria sistemaPizzaria = new SistemaPizzaria();
        sistemaPizzaria.executar();
    }
}