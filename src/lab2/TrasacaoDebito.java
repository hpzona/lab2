package lab2;

class TransacaoDebito extends Transacao {
	TransacaoDebito(int valor, String data) {
		super(valor, data);

	}

	int getVariacao() {
		return valor;

	}

	void emiteLinhaExtrato() {

	}
}
