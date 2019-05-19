
public class RovineMain {
	private static Rovina rovine = new Rovina();
	public static void main(String[] args) { 
		Città campoBase = rovine.get(0);
		Città rovinaPerduta = rovine.get(rovine.size()-1);
	}
	
	public void addRovine (Città città) {
		rovine.add(città);
	}
	
}
