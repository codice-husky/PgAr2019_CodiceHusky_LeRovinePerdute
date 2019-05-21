import java.util.ArrayList;
import java.util.Scanner;

public class RovineMain {
	
	public static final String[] FILE = {
			"xml/PgAr_Map_5.xml", 
			"xml/PgAr_Map_12.xml", 
			"xml/PgAr_Map_50.xml", 
			"xml/PgAr_Map_200.xml", 
			"xml/PgAr_Map_2000.xml", 
			"xml/PgAr_Map_10000.xml"
			};
	private static Rovina rovine = new Rovina();
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i< FILE.length; i++) {
			System.out.println((i+1)+") \t"+ FILE[i]);
		}
		System.out.println("scegli un file : ");
		
		int scelta = (Integer.parseInt(sc.nextLine()))-1;
				
		XMLInput inputcitta = new XMLInput(FILE[scelta]);
		Città citta = null;
		do {
			citta = inputcitta.readNextCittà();
			if(citta!=null) {
				rovine.add(citta);
			}
		} while(citta!=null);	
		
		XMLOutput output = new XMLOutput("xml/output.xml");
		boolean xy = true;
		String[] team = {"Tonatiuh","Metztli"};
		for (int i=0; i<2;i++) {
			ArrayList<Città> percorso = rovine.dijkstra(xy);
			double costo = rovine.getConsumo();
			xy=false;
			output.addRoute(team[i], costo, percorso.size(), percorso);
		}
		
		output.close();
		
		
		//per sapere il consumo basta invocare rovine.getConsumo();
		ArrayList<Città> percorsoH = rovine.dijkstra(false);
		
		
		
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
