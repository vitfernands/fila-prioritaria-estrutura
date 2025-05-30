import java.util.Scanner;

class NoPessoa {
    String nome;
    int idade;
    NoPessoa proximo;
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n = 0;

        boolean vazia = false;

        NoPessoa inicio;

        inicio = new NoPessoa();

        do {
            System.out.println("1) Novo cadastro de clinte prioritário");
            System.out.println("2) Atender proximo cliente");
            System.out.println("3) Exibir dados próximo cliente");
            System.out.println("4) Exibir todos os clientes da fila");
            System.out.println("5) Verificar se a fila está vazia");
            System.out.println("6) Fechar o sistema");

            n = sc.nextInt();

            switch (n) {
                case 1:
                    inicio = insercaoOrdernada(inicio);
                    break;
                case 2:
                    inicio = atender(inicio);
                    break;
                case 3:
                    frente(inicio);
                    break;
                case 4:
                    exibirFila(inicio);
                    break;
                case 5:
                    vazia = estaVazia(inicio);
                    if (vazia) {
                        System.out.println("A fila está vazia");
                        break;
                    } else {
                        System.out.println("A fila não está vazia");
                        break;
                    }
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Digite um valor válido");
            }
        } while (n != 6);
    }

    static NoPessoa insercaoOrdernada(NoPessoa inicio) {
        NoPessoa novo = new NoPessoa();
        System.out.println("Digite o nome: ");
        novo.nome = sc.next();
        System.out.println("Digite o idade: ");
        novo.idade = sc.nextInt();
        //sc.nextLine();

        if(novo.idade < 60) {
            System.out.println("Não é possível cadastrar cliente na fila PRIORITÁRIA");
            return inicio;
        }

        if (inicio == null || novo.idade > inicio.idade) {
            novo.proximo = inicio;
            return novo;
        }

        NoPessoa atual = inicio;

        while (atual.proximo != null && atual.proximo.idade >= novo.idade) {
            atual = atual.proximo;
        }

        novo.proximo = atual.proximo; //novo nó aponta para quem estava depois do atual
        atual.proximo = novo; //atual (anterior ao novo) aponta para novo
        return inicio;
    }

    static NoPessoa atender(NoPessoa inicio) {
        if (inicio.nome != null) {
            System.out.println("Atendendo " + inicio.nome);
            inicio = inicio.proximo;
            return inicio;
        }

        System.out.println("A fila está vazia");
        return inicio;
    }

    static void frente(NoPessoa inicio) {
        if (inicio.nome != null) {
            System.out.println("Nome: " + inicio.nome);
            System.out.println("Idade: " + inicio.idade);
        } else {
            System.out.println("Fila vazia");
        }
    }

    static void exibirFila(NoPessoa inicio) {
        if (inicio.nome == null) {
            System.out.println("Fila vazia");
        } else {
            while (inicio.proximo != null) {
                System.out.println("Nome: " + inicio.nome + ", Idade:" + inicio.idade);
                inicio = inicio.proximo;
            }
        }
    }
    static boolean estaVazia(NoPessoa inicio) {
        if (inicio.nome == null) {
            return true;
        }
        return false;
    }
}