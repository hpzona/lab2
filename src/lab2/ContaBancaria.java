package lab2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ContaBancaria {
	int numero;
	int saldoAnterior;
	ArrayList<Transacao> transacoes;

	private Lock accessLock = new ReentrantLock();

	// condi��es para controlar leitura e grava��o
	private Condition canWrite = accessLock.newCondition();

	private boolean occupied = false; // se o buffer estiver ocupado

	ContaBancaria(int numero) {
		this.numero = numero;
		this.transacoes = new ArrayList<Transacao>();
	}

	int getNumero() {
		return numero;
	}

	int getSaldoAtual() {
		return saldoAnterior;
	}

	void debite(int valor, String data) {
		accessLock.lock();
		try {
			while (occupied) {
				System.out.println("Producer tries to write.");
				canWrite.await();
			}

			occupied = true;

			TransacaoDebito debito = new TransacaoDebito(valor, data);
			transacoes.add(debito);
			saldoAnterior -= valor;

			occupied = false;
			canWrite.signal();
			
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}

	void credite(int valor, String data) {
		
		accessLock.lock();
		try {
			while (occupied) {
				System.out.println("Producer tries to write.");
				canWrite.await();
			}

			occupied = true;

			TransacaoCredito credito = new TransacaoCredito(valor, data);
			transacoes.add(credito);
			saldoAnterior += valor;
			

			occupied = false;
			canWrite.signal();
			
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}

	void emiteExtrato() {

	}
}
