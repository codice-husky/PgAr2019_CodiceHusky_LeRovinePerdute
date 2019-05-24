import java.util.ArrayList;
import java.util.LinkedList;
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
		/*
		LinkedList<Integer> ciao = new LinkedList<Integer>();
		ciao.add(1);
		ciao.add(3);
		ciao.add(2);
		rovine.add(new Città(0,"maometto", "1", "1", "5",ciao ));
	
		ciao = new LinkedList<Integer>();
		ciao.add(0);
		ciao.add(2);
		ciao.add(4);
		rovine.add(new Città(1,"ciao", "1", "1", "25",ciao ));
		
		
		ciao = new LinkedList<Integer>();
		ciao.add(0);
		ciao.add(1);
		ciao.add(3);
		ciao.add(4);
		rovine.add(new Città(2,"er", "1", "1", "4",ciao ));
		
		ciao = new LinkedList<Integer>();
		ciao.add(0);
		ciao.add(2);
		ciao.add(4);
		rovine.add(new Città(3,"lollo", "1", "1", "20",ciao ));
		
		ciao = new LinkedList<Integer>();
		ciao.add(2);
		ciao.add(1);
		rovine.add(new Città(4,"ketchup", "1", "1", "3",ciao ));
		ArrayList<Città>percorso = rovine.dijkstra(false);
		for(Città c:percorso) {
			System.out.println(c.getNome()+" ->");
		}
		*/
		
		
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
		System.out.println("via");
		
		for (int i=0; i<2;i++) {
			LinkedList<Città> percorso = rovine.dijkstra(xy);
			double costo = rovine.getConsumo();
			xy=false;
			System.out.println("ciao");
			output.addRoute(team[i], costo, percorso.size(), percorso);
		}
		
		output.close();
		
		/*
		double lunghezza[] = rovine.dijkstraV2();
		
		for(int i = 0;i< lunghezza.length;i++) {
			System.out.println(lunghezza[i]);
		}*/
		
		
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
