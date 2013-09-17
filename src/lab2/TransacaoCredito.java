package lab2;

class TransacaoCredito extends Transacao implements Runnable {
	
	private ContaBancaria contaBancaria;
	private int valor;
	private String data;
	
	TransacaoCredito(ContaBancaria contaBancaria, int valor, String data) {
		super(valor, data);
		this.valor = valor;
		this.data = data;
		this.contaBancaria = contaBancaria;
	}

	int getVariacao() {
		return valor;

	}

	void emiteLinhaExtrato() {
		System.out.println("Credito no valor de: " + getValor());
		System.out.println("Data: " + getData());
	}

	@Override
	public void run() {
		contaBancaria.credite(valor, data);
	}
}
