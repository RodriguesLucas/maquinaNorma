package br.com.project;

public class Registrador {
	
	private String id;
	private Long value;

	public Registrador() {
	}

	public Registrador(String id, Long value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Registrador [id=" + id + ", value=" + value + "]";
	}
	
}
