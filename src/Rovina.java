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
    	double x =a.getCoord().getX() - b.getCoord().getX();
    	double y = a.getCoord().getY() - b.getCoord().getY();
    	double diag = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    	return diag;
    }    
    public int getDistH(Città a,Città b) {
    	return Math.abs(a.getCoord().getH()-b.getCoord().getH());
    }
    
    public void dijkstra() {
    	LinkedList<Città> copia= this;
    	Double[] valori = new Double[this.size()];
    	String[]da = new String[this.size()];
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
    			if(valori[i] < min && !copia.get(i).passato) {
    				 indice =i;
    			}
    		}
    		Città inControllo = copia.get(indice);
    		inControllo.passato = true;
    		for(int vicino: inControllo.getVicini()) {
    			if(!copia.get(vicino).passato) {
    				double distanza = valori[indice] + getDistH(inControllo, copia.get(vicino));
    				if(distanza <copia.get(vicino).getId()) {
    					valori[copia.get(vicino).getId()] = distanza;
    					da[copia.get(vicino).getId()] = inControllo.getNome();
    				}
    			}
    		}
    	}
    	for(int i = 0;i<valori.length;i++) {
    		System.out.println(valori[i]);
    	}
    }
}
