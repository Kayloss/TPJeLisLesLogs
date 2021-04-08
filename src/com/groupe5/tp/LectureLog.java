package com.groupe5.tp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureLog {
	
	public BufferedReader fichierlog;
	
	
	
	public LectureLog(File fichier) {
		try {
			fichierlog = new BufferedReader(new FileReader(fichier));			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String rechercheEtat(String l) {
		if(l.contains("SEVERE")) {
			if(l.contains("Jenkins is fully up and running")){
				return l+"\nJenkins est en fonctionnement";
			}
			return l+"\nJenkins est arrete";
		}
		if(l.contains("Jenkins is fully up and running")) {
			return "Jenkins est en fonctionnement";
		}
		if(l.contains("Jenkins stopped")) {
			return "Jenkins est arrete";
		}
		return "";
	}
	
	public static void main(String [] args){
		File file = new File("C:\\Users\\sylvain\\Documents\\Jenkins\\apache-tomcat-9.0.44\\apache-tomcat-9.0.44\\logs\\catalina.2021-04-07.log");
		File filetest = new File("C:\\test\\test.txt");
		LectureLog l = new LectureLog(file);
		try {
			String ligne;
			while((ligne = l.fichierlog.readLine()) != null){
				String etat =l.rechercheEtat(ligne);
				System.out.println(etat);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
}
