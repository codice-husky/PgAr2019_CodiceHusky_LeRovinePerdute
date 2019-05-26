import java.util.LinkedList;
import java.util.Scanner;

public class RovineMain {
	private static final String SCELTA = "Scegli un file : ";
	private static final String LETTURA = "Stiamo leggendo la mappa";
	private static final String FILE_INPUT = "%d)\t%s";
	private static final String PERCORSO =  "\nI nostri esperti stanno studiando il percorso migliore per il team ";
	private static final String RISULTATO = "Abbiamo trovato il percorso perfetto che passa per %d città per un costo di %s";
	private static final String FILE_OUTPUT = "xml/output.xml";
	private static final String OUTPUT = "\nControlla il file output.xml per conoscere la strada da seguire \nBUON VIAGGIO";
	private static final String[] FILE = {
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
		//visualizzazione dei file disponibili
		for(int i = 0; i < FILE.length; i++) {
			System.out.println(String.format(FILE_INPUT ,(i+1),FILE[i]));
		}
		int scelta = 0;
		//scelta del file
		while(true) {
			try {
				System.out.print(SCELTA);
				scelta = (Integer.parseInt(sc.nextLine())) - 1;
				if(scelta < 0 || scelta > (FILE.length)-1) throw new Exception();
				break;
			}catch(Exception e) {
				System.out.println("Devi inserire un numero tra 1 e 6");
			}
		}
		
		XMLInput inputcitta = new XMLInput(FILE[scelta]);
		Città citta = null;
		//lettura 
		do {
			citta = inputcitta.readNextCittà();
			if(citta != null) {
				rovine.add(citta);
			}
		} while(citta != null);	
		
		System.out.println(LETTURA);
		
		XMLOutput output = new XMLOutput(FILE_OUTPUT);
		boolean xy = true;
		String[] team = {"Tonatiuh","Metztli"};
		//applicazione Dijkstra
		for (int i = 0; i < 2; i++) {
			System.out.println(PERCORSO + team[i]);
			LinkedList<Città> percorso = rovine.dijkstra(xy);  //non è il metodo più bello...
			//se xy è true, chiama dijkstra per la squadra Tonatiuh, altrimenti per Metztli
			double costo = rovine.getConsumo();
			xy = false;
			System.out.println(String.format(RISULTATO,percorso.size(),costo));
			//scrittura 
			output.addRoute(team[i], costo, percorso.size(), percorso);
		}
		
		output.close();
				
		System.out.println(OUTPUT);
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
}