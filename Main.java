
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        NumberFormat dinheiro =
                NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        Funcionario ana = new Funcionario("Ana", 3000.00);

        Funcionario bruno = new Funcionario("Bruno", 6000.00);

        FuncionarioPJ carla =
                new FuncionarioPJ("Carla", 50.00, 120);

        Professor daniel =
                new Professor("Daniel", 50.00, 20);

        Professor elisa =
                new Professor("Elisa", 100.00, 20);

        System.out.println("==========================================");
        System.out.println("       FOLHA DE PAGAMENTO");
        System.out.println("==========================================");

        imprimirFuncionario(ana, dinheiro);
        imprimirFuncionario(bruno, dinheiro);
        imprimirFuncionario(carla, dinheiro);
        imprimirFuncionario(daniel, dinheiro);
        imprimirFuncionario(elisa, dinheiro);

    }

    public static void imprimirFuncionario(
            Funcionario funcionario, NumberFormat dinheiro) {

        System.out.println("\n------------------------------------------");

        System.out.println("Nome: " + funcionario.getNome());

        System.out.println("Regime: "
                + (funcionario.isPJ() ? "PJ" : "CLT"));

        System.out.println("Bruto: "
                + dinheiro.format(funcionario.calcularBruto()));

        System.out.println("INSS: "
                + dinheiro.format(funcionario.calcularINSS()));

        System.out.println("IRRF: "
                + dinheiro.format(funcionario.calcularIRRF()));

        System.out.println("FGTS: "
                + dinheiro.format(funcionario.calcularFGTS()));

        System.out.println("DAS/MEI: "
                + dinheiro.format(funcionario.calcularMEI()));

        System.out.println("Liquido: "
                + dinheiro.format(funcionario.calcularLiquido()));

        System.out.println("------------------------------------------");
    }
}


