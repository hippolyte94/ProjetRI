import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;


public class Launch {

	public static void main(String[] args) throws IOException, ParserConfigurationException{
		
		Scanner sc = new Scanner(System.in);

			System.out.println("***RECHECRCHE D'INFORMATION****");
			System.out.println();
			System.out.println("Options : ");
			System.out.println();
			System.out.println("1. Màj des Indexes");
			System.out.println("2. Recherche d'infos");
			System.out.println("3. Quitter");
			System.out.println();
			System.out.println("Votre choix ? ");
			String choix = sc.nextLine();

			switch (choix) {
			
				case "1":
					Moteur mot=new Moteur();
					mot.start();
				break;
				
				case "2":
					
				break;
				
				case "3" :
					System.out.println("fin du programme");
					System.exit(0);
					break;
			}


		
	

	}
}
