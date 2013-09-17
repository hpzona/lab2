package lab2;

class TransacaoDebito extends Transacao implements Runnable {
	private ContaBancaria contaBancaria;
	private int valor;
	private String data;
	
	TransacaoDebito(ContaBancaria contaBancaria,int valor, String data) {
		super(valor, data);
		this.valor = valor;
		this.data = data;
		this.contaBancaria = contaBancaria;
	}

	int getVariacao() {
		return valor;

	}

	void emiteLinhaExtrato() {
		System.out.println("Debito no valor de: " + getValor());
		System.out.println("Data: " + getData());
	}

	@Override
	public void run() {
		contaBancaria.debite(valor, data);
		
	}
}
