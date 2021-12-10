package br.com.teste.tecnico.semaforo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.tecnico.semaforo.component.SemaforoComponent;

@RestController
public class SemaforoController {

	@Autowired
	private SemaforoComponent semaforoComponent;
	
	@GetMapping("/iniciar")
	public void iniciar() {
		this.semaforoComponent.ligarSemaforos();
	}
	
}
