package P6;

import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;



public abstract class Medium implements Comparable <Medium> , Serializable{
	// Attributes
	private final int id;
	private String titel;
	private int jahr;
	static int counter = 0;
	
	private static final long serialVersionUID = -7669576823979949107L;
	// Constructor
	public Medium (String titel, int jahr)
	{
		this.id = counter++ ;
		this.titel = titel;
		this.jahr = jahr;
	}
	
	
	public int getId()
	{
		return id;
	}
	
	public int getJahr()
	{
		return jahr;
	}
	public String getTitel()
	{
		return titel;
	}
	
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	
	public int alter() {
		return LocalDate.now().getYear() - jahr;
	}
	
	
	
	public abstract void druckeDaten(OutputStream stream);
	
	@Override
	public boolean equals (Object o) {
		if (this == o) // hier wird noch Referenze verglichen.
			return true;
		if ((o == null) || this.getClass() != o.getClass()) // falls die Objekte von verschiidene Klasse sind,aber mit gleichen Inhalt
			return false; 
		Medium medium = (Medium) o; // da die Methode equals Objekt als Perameter bekommt, soll diese zu entsprechenden Objekt dekalieret werden
		if (this.jahr == medium.jahr && Objects.equals(this.titel, medium.titel))
		return true;

			return false;
	}
	
	public int haschCode() {
		return Objects.hash(titel, jahr);
	}


public int compareTo(Medium o) {
	if(this.getJahr() > o.getJahr()) return 1;
	if(this.getJahr() < o.getJahr()) return -1;
	return 0;
	
}	
	
}
