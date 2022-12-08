package br.com.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
	public static void main(String[] args) {
		List<Registrador> registradores = getRegistradores();
		List<Rotulo> instrucoes = getInstrucoes();
		executa(registradores, instrucoes);

	}

	public static void executa(List<Registrador> registradors, List<Rotulo> rotulos) {
		boolean aux = true;
		Integer instrucao = 1;
		System.out.println("Computação: ");
		while (aux) {
			if (instrucaoExist(instrucao, rotulos)) {
				for (Rotulo rotulo : rotulos) {
					if (instrucao == rotulo.getInstrucao()) {
						if (rotulo.getOperacao().equals("se")) {
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
						} else if (rotulo.getOperacao().equals("ad")) {
							String auxString = rotulo.getRegistrador().substring(3, rotulo.getRegistrador().length());
							for (Registrador registrador : registradors) {
								if (registrador.getId().equals(auxString)) {
									printComputacao(registradors, instrucao);
									registrador.setValue(registrador.getValue() + 1);
									instrucao = rotulo.getVaPara();
								}
							}

						} else if (rotulo.getOperacao().equals("sub")) {
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
		
		System.out.println("Valor Registradores: ");
		System.out.println(registradors.toString());
	}

	private static void printComputacao(List<Registrador> registradors, Integer instrucao) {
		System.out.println("(".concat(instrucao.toString())
				.concat(",(").concat(registradors.get(0).getValue().toString()).concat(",")
				.concat(registradors.get(1).getValue().toString()).concat("))"));
	}

	private static boolean instrucaoExist(Integer instrucao, List<Rotulo> rotulos) {
		for (Rotulo rotulo : rotulos) {
			if (rotulo.getInstrucao() == instrucao) {
				return true;
			}
		}
		return false;
	}

	public static List<Registrador> getRegistradores() {
		List<Registrador> registradores = new ArrayList<>();

		try {
			File doc = new File(
					"E:\\ProjetosJava\\workSpaceJava2022\\MaquinaNorma\\src\\br\\com\\project\\scripts\\script.txt");
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

	public static List<Rotulo> getInstrucoes() {
		List<Rotulo> rotulos = new ArrayList<>();
		try {
			File doc = new File(
					"E:\\ProjetosJava\\workSpaceJava2022\\MaquinaNorma\\src\\br\\com\\project\\scripts\\script.txt");
			Scanner obj = new Scanner(doc);
			obj.nextLine();
			String line = "";

			while (obj.hasNextLine()) {
				line = obj.nextLine();
				Integer posInstrucao = Integer.valueOf(line.substring(0, line.indexOf(":")));
				String operacao = "", registrador = "";
				Integer vaPara = null, seNao = null;

				if (line.contains("se")) {
					operacao = "se";
					registrador = line.substring(line.indexOf("se ") + 3, line.indexOf(" então"));
					vaPara = Integer.valueOf(line.substring(line.indexOf("vá_para ") + 8, line.indexOf(" senão")));
					seNao = Integer.valueOf(line.substring(line.indexOf("senão vá_para ") + 14, line.length()));

				} else if (line.contains("ad")) {
					operacao = "ad";
					registrador = line.substring(line.indexOf("faça ") + 5, line.indexOf(" vá_para"));
					vaPara = Integer.valueOf(line.substring(line.indexOf("vá_para ") + 8, line.length()));

				} else if (line.contains("sub")) {
					operacao = "sub";
					registrador = line.substring(line.indexOf("faça ") + 5, line.indexOf(" vá_para"));
					vaPara = Integer.valueOf(line.substring(line.indexOf("vá_para ") + 8, line.length()));

				}

				Rotulo rotulo = new Rotulo(posInstrucao, operacao, registrador, vaPara, seNao);
				rotulos.add(rotulo);

			}
			obj.close();

		} catch (FileNotFoundException e) {

		}

		return rotulos;
	}
}
