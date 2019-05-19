import java.util.LinkedList;

public class Rovina extends LinkedList<Città>{
	public Rovina() {}
	
	public Città getCittàDaId(int indice) {
    	for(Città città:this) {
    		if(città.getId() == indice) return città;
    	}
    	return null;
    }
    public Città getCittàDaNome(String nome) {
    	for(Città città:this) {
    		if(città.getNome().equals(nome)) return città;
    	}
    	return null;
    }
	
    public double getDistXY(Città a,Città b) {
    	double x = a.getCoord().getX()-b.getCoord().getX();
    	double y = a.getCoord().getY()-b.getCoord().getY();
    	double diag = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    	return diag;
    }
    
    public int getDistH(Città a,Città b) {
    	return Math.abs(a.getCoord().getH()-b.getCoord().getH());
    }
}
