package br.com.project.constants;

public enum ConstantsEnum {
	VA_PARA("vá_para"),
	SE("se"),
	AD("ad"),
	SUB("sub"),
	ENTAO("então"),
	SENAO("senão"),
	SENAO_VA_PARA("senão vá_para"),
	FACA("faça");
	
	ConstantsEnum(String string) {
		value = string;
	}

	private String value;
	
	public String getValue() {
		return value;
	}
}
