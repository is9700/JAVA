package P6;



public class Hauptprogramm {

	public static void main(String[] args) {
		Medienverwaltung mv = new Medienverwaltung();
		mv.sucheNeuesMedium(System.out);
		System.out.println("Durchschnittliches Erscheinungsjahr = " + mv.berechneErscheinungsjahr());
		mv.aufnehmen(new Audio("It Means Nothing", 2007,"Stereophonics", 229));
		mv.aufnehmen(new Bild("Gebäude FB Informatik", 2012, "Dortmund"));
		mv.aufnehmen(new Audio("Sultans of Swing",1979, "Dire Straits", 180));
		System.out.println("Verwaltete Medien:");
		mv.zeigeMediensort(System.out);
		System.out.println("Medium mit jungsten Erscheinungsjahr:");
		mv.sucheNeuesMedium(System.out);
		System.out.println("Durchschnittliches Erscheinungsjar = " + mv.berechneErscheinungsjahr());
		
		
		System.out.printf("********************************************************************\n");
		System.out.printf("Hier ist die sortierte List\n");
		mv.zeigeMediensort(System.out);
		
		System.out.printf("********************************************************************\n");
		System.out.printf("Hier ist das Menue\n");
		Menu menu=new Menu();
    	menu.getMenu();
	
	}
}