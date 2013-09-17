package lab2;

public class Principal {

	public static void main(String[] args) {

		ContaBancaria conta = new ContaBancaria(200);
		TransacaoCredito credito = new TransacaoCredito(conta, 40, "16/09/2013");
		TransacaoDebito debito = new TransacaoDebito(conta, 20, "16/09/2013");

		for (int i = 0; i< 9; i++ ) {
			Thread threaDebito = new Thread(debito);
			Thread threaCredito = new Thread(credito);
			threaDebito.start();
			threaCredito.start();
		}
	}
}
