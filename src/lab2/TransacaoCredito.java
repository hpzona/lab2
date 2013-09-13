package lab2;

class TransacaoCredito extends Transacao {
	
	TransacaoCredito(int valor, String data) {
		super(valor, data);
	}

	int getVariacao() {
		return valor;

	}

	void emiteLinhaExtrato() {
		
	}
}
