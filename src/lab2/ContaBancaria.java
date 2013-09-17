package lab2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ContaBancaria {
	int saldoAnterior;
	int numero;
	ArrayList<Transacao> transacoes;

	private Lock accessLock = new ReentrantLock();
	private boolean ocupado = false; // se o buffer estiver ocupado
	
	// condi��es para controlar leitura e grava��o
	private Condition podeDebitar = accessLock.newCondition();
	private Condition podeCreditar = accessLock.newCondition();
	

	ContaBancaria(int saldo) {
		this.saldoAnterior = saldo;
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
			while (ocupado) {
				System.out.println();
				System.out.println("----------Outra transação em execução---------"); 
				System.out.println("A transação de débito não pode ser executada. ");
				System.out.println();
				podeDebitar.await();
			}
			this.saldoAnterior -= valor;
			ocupado = true;
			podeCreditar.signal();
			System.out.println("-------------Transação de débito-------------"); 
			System.out.println("Valor: -R$" + valor + ",00");
			System.out.println("Saldo atual: R$" + getSaldoAtual() + ",00");
			System.out.println("Data: " + data);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}

	void credite(int valor, String data) {
		
		accessLock.lock();
		try {
			while (!ocupado) {
				System.out.println();
				System.out.println("----------Outra transação em execução---------"); 
				System.out.println("A transação de crédito não pode ser executada. ");
				System.out.println();
				podeCreditar.await();
			}
			this.saldoAnterior += valor;
			ocupado = false;
			System.out.println("-------------Transação de crédito-------------"); 
			System.out.println("Valor: +R$" + valor + ",00");
			System.out.println("Saldo atual: R$" + getSaldoAtual() + ",00");
			System.out.println("Data: " + data);
			podeDebitar.signal();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}

	void emiteExtrato() {

	}
}
