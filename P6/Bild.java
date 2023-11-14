package P6;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;


public class Bild extends Medium {
	private String ort;
	  private static final long serialVersionUID = -2525232732172341694L;
	
	public Bild (String titel, int jahr, String ort) {
		super(titel, jahr);
		this.ort = ort;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	
	public void druckeDaten(OutputStream stream) {
		PrintStream ps = new PrintStream(stream);
		ps.printf("ID = %s '%s' aufgenommen im Jahr %d in %s.%n", super.getId(), super.getTitel(),
				super.getJahr(), this.ort);
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) // hier wird noch Referenze verglichen.
			return true;
		if ((o == null) || this.getClass() != o.getClass()) // falls die Objekte von verschiidene Klasse sind,aber mit gleichen Inhalt
			return false; 
		Bild bild = (Bild) o; // da die Methode equals Objekt als Perameter bekommt, soll diese zu entsprechenden Objekt dekalieret werden
		if (Objects.equals(this.ort, bild.ort))
		return true;
		else
			return false;
	}
	
	
	@Override
	public int haschCode() {
		return Objects.hash(super.haschCode(), ort);
	}


}
