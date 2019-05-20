import java.util.LinkedList;

public class RovineMain {
	private static Rovina rovine = new Rovina();
	public static void main(String[] args) {
		/*LinkedList<Integer> checco = new LinkedList<>();
		checco.add(1);
		checco.add(3);
		rovine.add(new Città(0,"campo base","8279","8338","2745",checco));
		
		checco = new LinkedList<>();
		checco.add(0);
		checco.add(2);
		checco.add(3);
		checco.add(4);
		rovine.add(new Città(1,"Tikal","1898","927","1336",checco));
		
		
		checco.add(0);
		checco.add(1);
		checco.add(4);
		rovine.add(new Città(2,"Teotiguacan","6317","3345","2090",checco));
		
		checco = new LinkedList<>();
		checco.add(1);
		checco.add(4);
		rovine.add(new Città(3,"Mixico Vieio","4798","1462","1022",checco));
		
		checco = new LinkedList<>();
		checco.add(0);
		checco.add(1);
		checco.add(2);
		checco.add(3);
		rovine.add(new Città(4,"Rovine Perdute","4415","4898","954",checco));
				
		rovine.dijkstra();*/
		XMLInput inputcitta = new XMLInput("xml/PgAr_Map_5.xml");
		
		Città test = inputcitta.readNextCittà();
		test = inputcitta.readNextCittà();
		System.out.println(test.getVicini().toString());
		test = inputcitta.readNextCittà();
		test = inputcitta.readNextCittà();
		test = inputcitta.readNextCittà();
		test = inputcitta.readNextCittà();
		test = inputcitta.readNextCittà();
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
