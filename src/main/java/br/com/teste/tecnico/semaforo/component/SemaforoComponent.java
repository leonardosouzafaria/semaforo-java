package br.com.teste.tecnico.semaforo.component;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.teste.tecnico.semaforo.model.Semaforo;
import br.com.teste.tecnico.semaforo.utils.SemaforoUtils;
import lombok.SneakyThrows;

@Component
@EnableScheduling 
public class SemaforoComponent {
	
	Semaforo semaforo1 = new Semaforo();
	Semaforo semaforo2 = new Semaforo();
	
	public void ligarSemaforos() {
		this.semaforo1 = new Semaforo("Rua 1",Boolean.TRUE, SemaforoUtils.VERMELHO);
		this.semaforo2 = new Semaforo("Rua 2",Boolean.TRUE, SemaforoUtils.VERMELHO);
	}
	
	
	@Scheduled(fixedDelay = SemaforoUtils.UM_SEGUNDO)
	private void gerenciaCruzamento() {
		if(!semaforo1.isLigado() || !semaforo2.isLigado()) 
			return ;
		
		if(semaforo1.getCor().equals(SemaforoUtils.VERMELHO)) {
			semaforo2.setCor(SemaforoUtils.VERMELHO);
			this.mudarSemaforoParaVerde(semaforo1);
			return;
		}
		
		if(semaforo2.getCor().equals(SemaforoUtils.VERMELHO)) {
			semaforo1.setCor(SemaforoUtils.VERMELHO);
			this.mudarSemaforoParaVerde(semaforo2);
			return;
		}
	}

	@SneakyThrows
	private void iniciarContagemVermelho(Semaforo semaforo) {
		this.escrever(this.semaforo1, this.semaforo2);
		Thread.sleep(SemaforoUtils.DEZ_SEGUNDOS);	
		this.mudarSemaforoParaVerde(semaforo);		
	}

	@SneakyThrows
	private void mudarSemaforoParaVerde(Semaforo semaforo) {		
		semaforo.setCor(SemaforoUtils.VERDE);
		this.escrever(this.semaforo1, this.semaforo2);
		Thread.sleep(SemaforoUtils.DEZ_SEGUNDOS);	
		this.mudarSemaforoParaAmarelo(semaforo);	
	}


	@SneakyThrows
	private void mudarSemaforoParaAmarelo(Semaforo semaforo) { 
		semaforo.setCor(SemaforoUtils.AMARELO);
		this.escrever(this.semaforo1, this.semaforo2);
		Thread.sleep(SemaforoUtils.CINCO_SEGUNDOS);	
	}
	
	private void escrever(final Semaforo semaforo1, final Semaforo semaforo2) {
		System.out.println(String.format("Semáforo da %s está: %s e o Semáforo da rua %s está: %s", semaforo1.getRua(), semaforo1.getCor(), semaforo2.getRua(), semaforo2.getCor()));
	}
	
}
