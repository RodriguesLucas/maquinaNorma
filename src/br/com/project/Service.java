package br.com.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.project.constants.ConstantsEnum;

public class Service {
	static List<Macro> macros;
	
	public static void main(String[] args) {
		macros = getMacros();
		List<Registrador> registradores = getRegistradores();
		List<Rotulo> instrucoes = getInstrucoes(true, getScriptPath());
		
		executa(registradores, instrucoes, true);

	}

	public static void executa(List<Registrador> registradors, List<Rotulo> rotulos, boolean isMain) {
		boolean aux = true;
		Integer instrucao = 1;
		if(isMain) System.out.println("Computação: ");
		while (aux) {
			if (instrucaoExist(instrucao, rotulos)) {
				for (Rotulo rotulo : rotulos) {
					if (instrucao == rotulo.getInstrucao()) {
						if (rotulo.getOperacao().equals(ConstantsEnum.SE.getValue())) {
							String auxString = rotulo.getRegistrador().substring(5, rotulo.getRegistrador().length());
							for (Registrador registrador : registradors) {
								if (registrador.getId().equals(auxString)) {
									printComputacao(registradors, instrucao);
									if (registrador.getValue() == 0) {
										instrucao = rotulo.getVaPara();
									} else {
										instrucao = rotulo.getSeNao();
									}
								}
							}
						} else if (rotulo.isMacro()) {
							String auxString = rotulo.getRegistrador().substring(rotulo.getRegistrador().indexOf("_") + 1, rotulo.getRegistrador().length());
							String[] regs = auxString.split("_");
							List<Registrador> macroRegs = new ArrayList<>();
							for (String value : regs) {
								for(Registrador r: registradors) {
									if(r.getId().equals(value)) {
										macroRegs.add(r);
									}
								}
							}
							executaMacro(rotulo.getOperacao(), macroRegs);
							//TODO: VA PARA
							
						} else if (rotulo.getOperacao().equals(ConstantsEnum.AD.getValue())) {
							String auxString = rotulo.getRegistrador().substring(3, rotulo.getRegistrador().length());
							for (Registrador registrador : registradors) {
								if (registrador.getId().equals(auxString)) {
									printComputacao(registradors, instrucao);
									registrador.setValue(registrador.getValue() + 1);
									instrucao = rotulo.getVaPara();
								}
							}

						} else if (rotulo.getOperacao().equals(ConstantsEnum.SUB.getValue())) {
							String auxString = rotulo.getRegistrador().substring(4, rotulo.getRegistrador().length());
							for (Registrador registrador : registradors) {
								if (registrador.getId().equals(auxString)) {
									printComputacao(registradors, instrucao);
									registrador.setValue(registrador.getValue() - 1);
									instrucao = rotulo.getVaPara();
								}
							}
						}
					}
				}
			} else {
				break;
			}
		}
		
		if(isMain) System.out.println("Valor Registradores: ");
		if(isMain) System.out.println(registradors.toString());
	}

	public static void executaMacro(String idMacro, List<Registrador> registradores) {
		List<Registrador> macroRegistradores = new ArrayList<>();
		for (Macro macro : macros) {
			if (macro.getId().equals(idMacro)) {
				for(int i=0; i<macro.getRegistradorEntrada().size(); i++) {				// adiciona registradores auxiliares
					Registrador regInput = macro.getRegistradorEntrada().get(i);
					regInput.setValue(registradores.get(i).getValue());
					macroRegistradores.add(regInput);
				}
				for(Registrador regAux: macro.getRegistradorAuxiliar()) {				// adiciona registradores auxiliares
					macroRegistradores.add(regAux);
				}
				System.out.println("iniciodoMacro");
				executa(macroRegistradores, macro.getInstrucoes(), false);
				System.out.println("fimdoMacro + " + macroRegistradores);
			}
		}
	}
	
	private static void printComputacao(List<Registrador> registradors, Integer instrucao) {
		System.out.println("(".concat(instrucao.toString())
				.concat(",(").concat(registradors.get(0).getValue().toString()).concat(",")
				.concat(registradors.get(1).getValue().toString()).concat("))"));
	}

	public static String getScriptPath() {
		File currentDirFile = new File(".");
		String relativePath = currentDirFile.getAbsolutePath();
		String scriptPath = relativePath.substring(0, relativePath.length() -2) + "\\src\\br\\com\\project\\scripts\\script.txt";
		return scriptPath;
	}
	
	private static boolean instrucaoExist(Integer instrucao, List<Rotulo> rotulos) {
		for (Rotulo rotulo : rotulos) {
			if (rotulo.getInstrucao() == instrucao) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasMacro(String instrucao) {
		for(Macro m: macros) {
			if(instrucao.contains(m.getId())) {
				return true;
			}
		}
		return false;
		
	}
	
	public static List<Registrador> getRegistradores() {
		List<Registrador> registradores = new ArrayList<>();
		
		File currentDirFile = new File(".");
		String relativePath = currentDirFile.getAbsolutePath();
		String scriptPath = relativePath.substring(0, relativePath.length() -2) + "\\src\\br\\com\\project\\scripts\\script.txt";
		
		try {
			File doc = new File(
					scriptPath);
			Scanner obj = new Scanner(doc);
			String registradoreString = obj.nextLine();

			String[] regs = registradoreString.split(";");

			for (String value : regs) {
				Registrador registrador = new Registrador();
				registrador.setId(value.substring(0, value.indexOf('=')));
				registrador.setValue(Long.parseLong(value.substring(value.indexOf('=') + 1, value.length())));
				registradores.add(registrador);
			}
			obj.close();

		} catch (FileNotFoundException e) {

		}

		return registradores;
	}
	
	public static List<Rotulo> getInstrucoes(boolean isScript, String filePath) {
		List<Rotulo> rotulos = new ArrayList<>();
		
		try {
			File doc = new File(
					filePath);
			Scanner obj = new Scanner(doc);
			
			obj.nextLine();
			if(!isScript) {							// verifica se pega instruções macro ou script
				obj.nextLine(); obj.nextLine();
			}
			String line = "";

			while (obj.hasNextLine()) {
				line = obj.nextLine();
				Integer posInstrucao = Integer.valueOf(line.substring(0, line.indexOf(":")));
				String operacao = "", registrador = "";
				Integer vaPara = null, seNao = null;
				boolean macro = false;
				
				if (line.contains(ConstantsEnum.SE.getValue())) {
					operacao = ConstantsEnum.SE.getValue();
					registrador = line.substring(line.indexOf(ConstantsEnum.SE.getValue() + " ") + 3, line.indexOf(" " + ConstantsEnum.ENTAO.getValue()));
					vaPara = Integer.valueOf(line.substring(line.indexOf(ConstantsEnum.VA_PARA.getValue() + " ") + 8, line.indexOf(" " + ConstantsEnum.SENAO.getValue())));
					seNao = Integer.valueOf(line.substring(line.indexOf(ConstantsEnum.SENAO_VA_PARA.getValue() + " ") + 14, line.length()));
				} else if (isScript && hasMacro(line)) {			
					operacao = line.substring(line.indexOf(ConstantsEnum.FACA.getValue() + " ") + 5, line.indexOf("_"));
					registrador = line.substring(line.indexOf(ConstantsEnum.FACA.getValue() + " ") + 5, line.indexOf(" " + ConstantsEnum.VA_PARA.getValue()));
					vaPara = Integer.valueOf(line.substring(line.indexOf(ConstantsEnum.VA_PARA.getValue() + " ") + 8, line.length()));
					macro = true;
				} else if (line.contains(ConstantsEnum.AD.getValue())) {
					operacao = ConstantsEnum.AD.getValue();
					registrador = line.substring(line.indexOf(ConstantsEnum.FACA.getValue() + " ") + 5, line.indexOf(" " + ConstantsEnum.VA_PARA.getValue()));
					vaPara = Integer.valueOf(line.substring(line.indexOf(ConstantsEnum.VA_PARA.getValue() + " ") + 8, line.length()));
				} else if (line.contains(ConstantsEnum.SUB.getValue())) {
					operacao = ConstantsEnum.SUB.getValue();
					registrador = line.substring(line.indexOf(ConstantsEnum.FACA.getValue() + " ") + 5, line.indexOf(" " + ConstantsEnum.VA_PARA.getValue()));
					vaPara = Integer.valueOf(line.substring(line.indexOf(ConstantsEnum.VA_PARA.getValue() + " ") + 8, line.length()));
				} 

				Rotulo rotulo = new Rotulo(posInstrucao, operacao, registrador, vaPara, seNao, macro);
				rotulos.add(rotulo);

			}
			obj.close();

		} catch (FileNotFoundException e) {

		}

		return rotulos;
	}
	
	public static List<Macro> getMacros() {
		List<Macro> macros = new ArrayList<>();
		List<String> macroPaths = new ArrayList<>();
		
		File currentDirFile = new File(".");
		String relativePath = currentDirFile.getAbsolutePath();
		String macroAdicaoRegPath = relativePath.substring(0, relativePath.length() -2) + "\\src\\br\\com\\project\\scripts\\macros\\adicao_dois_registradores.txt";
		
		macroPaths.add(macroAdicaoRegPath);
		
		
		
		for(String path: macroPaths) {	// loop pelos arquivos de macro
			Macro macro = new Macro();
			List<Registrador> registradoresEntrada = new ArrayList<>();
			List<Registrador> registradoresAux = new ArrayList<>();

			try {
				File doc = new File(
						path);
				Scanner obj = new Scanner(doc);
				String nomeMacro = obj.nextLine();
				macro.setId(nomeMacro);
				
				
				String registradoresInputString = obj.nextLine();
				String[] regsInput = registradoresInputString.split(";");
				for (String value : regsInput) {								//populando registradores de entrada
					Registrador registrador = new Registrador();
					registrador.setId(value);
					registradoresEntrada.add(registrador);
				}
				macro.setRegistradorEntrada(registradoresEntrada);
				
				String registradoresAuxString = obj.nextLine();
				String[] regsAux = registradoresAuxString.split(";");
				for (String value : regsAux) {									//populando registradores auxiliares
					Registrador registrador = new Registrador();
					registrador.setId(value.substring(0, value.indexOf('=')));
					registrador.setValue(Long.parseLong(value.substring(value.indexOf('=') + 1, value.length())));
					registradoresAux.add(registrador);
				}
				macro.setRegistradorAuxiliar(registradoresAux);
				obj.close();

			} catch (FileNotFoundException e) {

			}
			
			List<Rotulo> instrucoesMacro = getInstrucoes(false, path);
			macro.setInstrucoes(instrucoesMacro);
			macros.add(macro);
		}
		return macros;
		
	}
	
	

	
	
	
}
