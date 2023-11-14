package P6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import java.util.Iterator;

public class Medienverwaltung {
	private LinkedList<Medium> medien;
	private static final String dateiname = "C:/Users/Che/Downloads/medien.ser";

	public Medienverwaltung() {
		medien = new LinkedList<Medium>();
	}

	public void aufnehmen(Medium neu) {
		medien.add(neu);
	}

//	public void zeigeMedien() {
//		Iterator<Medium> it = medien.iterator();
//		while (it.hasNext()) {
//			Medium medium = it.next();
//			medium.druckeDaten();
//		}
//	}

	public void zeigeMediensort(OutputStream stream) {

		Collections.sort(medien);

		for (Medium f : medien) {

			f.druckeDaten(stream);
		}
	}

	public void sucheNeuesMedium(OutputStream stream) {
		int jahr;
		Medium neu = null;
		Iterator<Medium> it = medien.iterator();
		if (it.hasNext()) {
			Medium medium = (Medium) it.next();
			neu = medium;
			jahr = medium.getJahr();
			while (it.hasNext()) {
				medium = (Medium) it.next();
				if (medium.getJahr() > jahr) {
					jahr = medium.getJahr();
					neu = medium;
				}
			}
			neu.druckeDaten(stream);
		}

	}

	public double berechneErscheinungsjahr() {
		double jahr = 0.0;
		if (medien.size() > 0) {
			for (Object o : medien) {
				Medium medium = (Medium) o;
				jahr += medium.getJahr();
			}
			jahr = jahr / (double) medien.size();
		}
		return jahr;
	}
	 public static void serialisieren(Medienverwaltung m, File file){
	        try(FileOutputStream fos = new FileOutputStream(file);
	                ObjectOutputStream oos = new ObjectOutputStream(fos)){
	            oos.writeObject(m);
	            System.out.println("Medien wurden erfolgreich gespeichert!");
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    // AUSWAHL 8
	    public static Medienverwaltung deserialisieren(File file){
	        Medienverwaltung m = null;
	        try(FileInputStream fis = new FileInputStream(file);
	                ObjectInputStream ois = new ObjectInputStream(fis)){
	            m = (Medienverwaltung) ois.readObject();
	         //   Medium.setCounter(m.getSizeListe());
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	        return m;
	    }

	public void schreibenMedienInDatei() {

		boolean ok = true;
		File f = null;
		String dateiname;
		do {
			try {
				dateiname = JOptionPane.showInputDialog("Dateiname");
				if (dateiname == null) {
					return;
				}
				if (dateiname.isEmpty()) {
					throw new EmptyFilenameException("Dateiname ist leer");
				}
				f = new File(dateiname);
			} catch (EmptyFilenameException e) {
				int option = JOptionPane.showConfirmDialog(null, "Dateinamen ist leer! Neuen Dateinamen wählen?",
						"Hinweis", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					ok = false;
				} else {
					return;
				}
			}
			if (f != null) {
				try (FileOutputStream fos = new FileOutputStream(f); PrintStream printer = new PrintStream(fos)) {

					printer.printf("Medienliste%n%n");
					zeigeMediensort(fos);
					ok = true;
				} catch (FileNotFoundException e) {
					int option = JOptionPane.showConfirmDialog(null, "Datei nicht vorhanden! Neuen Dateinamen wählen?",
							"Hinweis", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						ok = false;
					}
				} catch (IOException e) {
					int option = JOptionPane.showConfirmDialog(null, "Fehler beim Dateizugriff! Erneut versuchen?",
							"Hinweis", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						ok = false;
					}
				}
			}
		} while (!ok);
	}

	public void speicher() {
		File file = new File(dateiname);

		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(medien);
		} catch (FileNotFoundException e) {
			System.out.printf("auf Datei %s konnte nicht zugegriffen %n", dateiname);

		} catch (IOException e) {
			System.out.printf("Serialisierung war nicht erfolg! %n");
		}
	}

	public void laden() {
		File f = new File(dateiname);

		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
			Object reOb = ois.readObject();

			if (reOb instanceof LinkedList<?>) {
				medien = (LinkedList<Medium>) reOb;

			} else {
				throw new IOException();
			}
			System.out.printf("Deserialisiert %n");

			Medium.counter = medien.size();
		} catch (FileNotFoundException e) {
			System.out.printf("auf Datei %s konnte nicht zugegriffen %n", dateiname);
		} catch (IOException e) {
			System.out.printf("Deserialisierung war nicht erfolg! %n");
		} catch (ClassNotFoundException e) {
			System.out.printf("Deserialisierung war nicht erfolg, Erforderliche class-Dateien liegen nichT%n");
		}

	}

}
