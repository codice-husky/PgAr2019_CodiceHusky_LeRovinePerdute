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
    	double x = Double.parseDouble(a.getCoord().getX()) - Double.parseDouble(b.getCoord().getX());
    	double y = Double.parseDouble(a.getCoord().getY()) - Double.parseDouble(b.getCoord().getY());
    	double diag = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    	return diag;
    }    
    public int getDistH(Città a,Città b) {
    	return Math.abs(Integer.parseInt(a.getCoord().getH())-Integer.parseInt(b.getCoord().getH()));
    }
    
    public void dijkstra() {
    	LinkedList<Città> copia= this;
    	Città partenza = this.get(0);
    	Città arrivo = this.get(this.size()-1);
    	Double[] valori = new Double[this.size()];
    	Integer[]da = new Integer[this.size()];
    	valori[0] = 0.0;
    	for(int i = 1;i<valori.length;i++) {
    		valori[i] = Double.POSITIVE_INFINITY; //in alternativa Double.POSITIVE_INFINITY
    	}
    	while(copia.size()!=1) {
    		double min = Double.POSITIVE_INFINITY;
    		int memo = 0;
    		for(Città città:copia) {
    			if(!città.passato) {
    				memo = città.getId();
    				min = valori[memo];
    			}
    		}
    		int indice = memo;
    		for(int i = memo+1; i <valori.length;i++) {
    			ifSS(valori[i] < min && !copia.get(i).passato) {
    				 indice =i;
    			}
    		}
    		copia.get(indice).passato = true;
    		for(int vicino: copia.get(indice).) {
    			
    		}
    	}
    }
}
