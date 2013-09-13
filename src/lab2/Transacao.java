package lab2;

abstract class Transacao {
	int valor;
	String data;
	

	public Transacao(int valor, String data) {
		this.valor = valor;
		this.data = data;
	}

	int getValor() {
		return valor;
	}

	String getData() {
		return data;
	}

	abstract int getVariacao();

	abstract void emiteLinhaExtrato();
}
