package P6;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {

	Medienverwaltung mv = new Medienverwaltung();

	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		int index = 0;
		
		do {
		System.out.printf("+++++++++++++++++++++++++++++++++\n");
		System.out.printf(
				"Medienverwaltung \n" + "1. Audio aufnehmen \n" + "2. Bildaufnehmen \n" + "3. Zeige alle Medien \n"
						+ "4. Medienliste in Datei schreiben \n" + "5. Zeige neues Medium \n" +
						"6. Berechne durchschnittliches Erscheinungsjahr \n"
						+"7. Speicher\n"+ "8. Laden\n"
						+"9. Beenden \n" + "\n" + "Bitte Menüpunkt wählen");

		
		
		
		while(true) {
			

			index = sc.nextInt(); // nach fangen der exception nextLine() um buffer zu leeren
			sc.nextLine();
			if (!(index > 9 || index < 1)) {
			break;
			}
			System.err.printf("Bitte gültige Zahl eingeben!!!\n");
			
		}
		
		
			switch (index) {
			case 1:

				String titel = JOptionPane.showInputDialog(null, "Gib Titel ein:");
				int jahr;
				int dauer;

				while (true) {

					try {

						jahr = Integer.parseInt(JOptionPane.showInputDialog(null, "Gibt Jahr ein:"));
						break;

					} catch (NumberFormatException e) {

						JOptionPane.showMessageDialog(null, "Gib ein Zahl Bitte!");

					}

				}

				String interp = JOptionPane.showInputDialog(null, "Gibt Interpret ein:");

				while (true) {
					try {
						dauer = Integer.parseInt(JOptionPane.showInputDialog(null, "Gibt Dauer ein:"));
						break;

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Gib ein Zahl Bitte!");
					}
				}

				mv.aufnehmen(new Audio(titel, jahr, interp, dauer));
				break;

			case 2:
				int jah;

				String tit = JOptionPane.showInputDialog(null, "Gibt Titel ein:");

				while (true) {
					try {
						jah = Integer.parseInt(JOptionPane.showInputDialog(null, "Gibt Jahr ein:"));
						break;

					} catch (NumberFormatException e) {

					}

				}

				String ort = JOptionPane.showInputDialog(null, "Gibt Ort ein:");

				mv.aufnehmen(new Bild(tit, jah, ort));
				break;
			case 3:
				mv.zeigeMediensort(System.out);
				break;
				
			case 4:
				mv.schreibenMedienInDatei();
				break;

			case 5:
				mv.sucheNeuesMedium(System.out);
				break;

			case 6:
				System.out.printf("durchschnittliches Erscheinungsjahr ist %f \n", mv.berechneErscheinungsjahr());
				break;
				
			case 7:
				mv.speicher();
				break;
				
			case 8:
				mv.laden();
				break;
				
			case 9:
				System.exit(0);
				break;

			default:
				getMenu();

			}

		}while(index !=9);
		

	}

}
