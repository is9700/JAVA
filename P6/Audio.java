package P6;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;


public class Audio extends Medium {
	private String interpret;
	private int dauer;

	private static final long serialVersionUID = 890366068386602567L;
	
	public Audio (String titel, int jahr, String interpret, int dauer) {
		super(titel, jahr);
		this.interpret = interpret;
		this.dauer = dauer;
		}
	
	public String getInterpret() {
		return interpret;
	}
	
	public int getDauer() {
		return dauer;
	}
	
	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}
	
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}
	
	@Override
	public void druckeDaten(OutputStream stream) {
		PrintStream ps = new PrintStream(stream);
		ps.printf("ID = %d '%s' von %s aus %d Spieldauer : %s sek.%n", getId(), getTitel(), interpret, getJahr(), dauer);
		
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) // hier wird noch Referenze verglichen.
			return true;
		if ((o == null) || this.getClass() != o.getClass()) // falls die Objekte von verschiidene Klasse sind,aber mit gleichen Inhalt
			return false; 
		Audio audio = (Audio) o; // da die Methode equals Objekt als Perameter bekommt, soll diese zu entsprechenden Objekt dekalieret werden
		if (this.dauer == audio.dauer && Objects.equals(this.interpret , audio.interpret))
		return true;
		else
			return false;
	}
	
	@Override
	public int haschCode() {
		return Objects.hash(super.haschCode(), interpret, dauer);
	}
	
	
}
