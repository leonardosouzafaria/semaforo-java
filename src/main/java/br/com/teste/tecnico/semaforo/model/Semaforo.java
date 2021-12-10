package br.com.teste.tecnico.semaforo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Semaforo {
	
	private String rua;
	private boolean ligado;
	private String cor;
	
}
