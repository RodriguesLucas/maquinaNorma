package br.com.project;

public class Rotulo {
	private Integer instrucao;
	private String operacao;
	private String registrador;
	private Integer vaPara;
	private Integer seNao;

	public Rotulo() {
	}

	public Rotulo(Integer instrucao, String operacao, String registrador, Integer vaPara, Integer seNao) {
		this.instrucao = instrucao;
		this.operacao = operacao;
		this.registrador = registrador;
		this.vaPara = vaPara;
		this.seNao = seNao;
	}

	public Integer getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Integer instrucao) {
		this.instrucao = instrucao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getRegistrador() {
		return registrador;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}

	public Integer getVaPara() {
		return vaPara;
	}

	public void setVaPara(Integer vaPara) {
		this.vaPara = vaPara;
	}

	public Integer getSeNao() {
		return seNao;
	}

	public void setSeNao(Integer seNao) {
		this.seNao = seNao;
	}

	@Override
	public String toString() {
		return "Rotulo [instrucao=" + instrucao + ", operacao=" + operacao + ", registrador=" + registrador
				+ ", vaPara=" + vaPara + ", seNao=" + seNao + "]";
	}

	
}
