package br.com.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
	public static void main(String[] args) {
		getRegistradores();

	}

	public static List<Registrador> getRegistradores() {
		List<Registrador> registradores = new ArrayList<>();

		try {
			File doc = new File(
					"E:\\ProjetosJava\\workSpaceJava2022\\MaquinaNorma\\src\\br\\com\\project\\scripts\\script.txt");
			Scanner obj = new Scanner(doc);
			String registradoreString = obj.nextLine();

			String[] regs = registradoreString.split(";");

			for (String r : regs) {
				Registrador registrador = new Registrador();
				registrador.setId(r.substring(0, r.indexOf('=')));
				registrador.setValue(Long.parseLong(r.substring(r.indexOf('=') + 1, r.length())));
				registradores.add(registrador);
				System.out.println(registrador);
			}
			obj.close();

		} catch (FileNotFoundException e) {

		}

		return registradores;

	}
}
