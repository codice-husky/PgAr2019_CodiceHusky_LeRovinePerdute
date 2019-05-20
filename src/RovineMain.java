public class RovineMain {
	private static Rovina rovine = new Rovina();
	public static void main(String[] args) {
		
		XMLInput inputcitta = new XMLInput("xml/PgAr_Map_5.xml");
		
		for(int i = 0;i<5;i++) {
			rovine.add(inputcitta.readNextCittà());
			
		}
		rovine.dijkstra();
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
