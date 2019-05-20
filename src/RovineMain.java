
public class RovineMain {
	private static Rovina rovine = new Rovina();
	public static void main(String[] args) { 
		rovine.add(new Città(0,"campo base","8279","8338","2745"));
		rovine.add(new Città(1,"Tikal","1898","927","1336"));
		rovine.add(new Città(2,"Teotiguacan","6317","3345","2090"));
		rovine.add(new Città(3,"Mixico Vieio","4798","1462","1022"));
		rovine.add(new Città(4,"Rovine Perdute","4415","4898","954"));
		rovine.get(0).addCittà(1);
		rovine.get(0).addCittà(3);
		
		rovine.get(1).addCittà(0);
		rovine.get(1).addCittà(2);
		rovine.get(1).addCittà(3);
		rovine.get(1).addCittà(4);
		
		rovine.get(2).addCittà(0);
		rovine.get(2).addCittà(1);
		rovine.get(2).addCittà(4);
		
		rovine.get(3).addCittà(1);
		rovine.get(3).addCittà(4);
		
		rovine.get(4).addCittà(0);
		rovine.get(4).addCittà(1);
		rovine.get(4).addCittà(2);
		rovine.get(4).addCittà(3);
		
		rovine.dijkstra();
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
