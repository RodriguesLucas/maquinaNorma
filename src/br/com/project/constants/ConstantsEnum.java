package br.com.project.constants;

public enum ConstantsEnum {
	VA_PARA("v�_para"),
	SE("se"),
	AD("ad"),
	SUB("sub"),
	ENTAO("ent�o"),
	SENAO("sen�o"),
	SENAO_VA_PARA("sen�o v�_para"),
	FACA("fa�a");
	
	ConstantsEnum(String string) {
		value = string;
	}

	private String value;
	
	public String getValue() {
		return value;
	}
}
