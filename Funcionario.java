
public class Funcionario {

    private String nome;
    private double salarioMensal;
    private final boolean pj;

    public Funcionario(String nome, double salarioMensal) {
        this(nome, salarioMensal, false);
    }

    protected Funcionario(String nome, double salarioMensal, boolean pj) {

        if (nome == null || nome.isBlank() || salarioMensal < 0) {
            throw new IllegalArgumentException("Nome e salario invalidos.");
        }

        this.nome = nome;
        this.salarioMensal = salarioMensal;
        this.pj = pj;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public boolean isPJ() {
        return pj;
    }

    public double calcularBruto() {
        return salarioMensal;
    }

    public double calcularINSS() {

        if (isPJ()) {
            return 0;
        }

        double salario = calcularBruto();
        double inss = 0;

        inss += Math.min(salario, 1621.00) * 0.075;

        if (salario > 1621.00) {
            inss += Math.min(salario - 1621.00, 1281.84) * 0.09;
        }

        if (salario > 2902.84) {
            inss += Math.min(salario - 2902.84, 1451.43) * 0.12;
        }

        if (salario > 4354.27) {
            inss += Math.min(salario - 4354.27, 4121.28) * 0.14;
        }

        return arredondar(inss);
    }

    public double calcularIRRF() {

        double bruto = calcularBruto();

        if (isPJ() || bruto <= 5000.00) {
            return 0;
        }

        double deducao = Math.max(calcularINSS(), 607.20);
        double base = Math.max(0, bruto - deducao);

        double imposto;

        if (base <= 2428.80) {
            imposto = 0;
        } else if (base <= 2826.65) {
            imposto = base * 0.075 - 182.16;
        } else if (base <= 3751.05) {
            imposto = base * 0.15 - 394.16;
        } else if (base <= 4664.68) {
            imposto = base * 0.225 - 675.49;
        } else {
            imposto = base * 0.275 - 908.73;
        }

        imposto = arredondar(Math.max(0, imposto));

        double reducao = 0;

        if (bruto > 5000.00 && bruto <= 7350.00) {
            reducao = Math.max(0, 978.62 - 0.133145 * bruto);
        }

        reducao = arredondar(reducao);

        double irFinal = Math.max(0, imposto - reducao);

        return arredondar(irFinal);
    }

    public double calcularFGTS() {

        if (isPJ()) {
            return 0;
        }

        return arredondar(calcularBruto() * 0.08);
    }

    public double calcularMEI() {

        if (isPJ()) {
            return 86.05;
        }

        return 0;
    }

    public double calcularLiquido() {

        double liquido = calcularBruto()
                - calcularINSS()
                - calcularIRRF()
                - calcularMEI();

        return arredondar(liquido);
    }

    protected double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}



