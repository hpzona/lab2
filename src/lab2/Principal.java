package lab2;

public class Principal {

	public static void main(String[] args) {

		ContaBancaria conta = new ContaBancaria(1);

		conta.debite(20, "16/09/2013");
		conta.credite(40, "16/09/2013");

		conta.emiteExtrato();

	}
}
