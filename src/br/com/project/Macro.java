package br.com.project;

import java.util.List;

public class Macro {

	private String id;
	private List<Rotulo> instrucoes;
	private List<Registrador> registradorEntrada;
	private List<Registrador> registradorAuxiliar;
	
	
	public Macro() {
	}
	

	public Macro(String id, List<Rotulo> instrucoes, List<Registrador> registradorEntrada,
			List<Registrador> registradorAuxiliar) {
		this.id = id;
		this.instrucoes = instrucoes;
		this.registradorEntrada = registradorEntrada;
		this.registradorAuxiliar = registradorAuxiliar;
	}




	public List<Rotulo> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(List<Rotulo> instrucoes) {
		this.instrucoes = instrucoes;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Registrador> getRegistradorEntrada() {
		return registradorEntrada;
	}

	public void setRegistradorEntrada(List<Registrador> registradorEntrada) {
		this.registradorEntrada = registradorEntrada;
	}

	public List<Registrador> getRegistradorAuxiliar() {
		return registradorAuxiliar;
	}

	public void setRegistradorAuxiliar(List<Registrador> registradorAuxiliar) {
		this.registradorAuxiliar = registradorAuxiliar;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + 
				"\n Registradores Entrada: " + this.registradorEntrada +
				"\n Registradores Auxiliar: " + this.registradorAuxiliar + 
				"\n Instruções: " + this.instrucoes;
	}
	

}
