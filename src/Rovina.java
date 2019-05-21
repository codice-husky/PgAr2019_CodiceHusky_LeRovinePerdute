import java.util.ArrayList;
import java.util.LinkedList;

public  class Rovina extends LinkedList<Città>{
	
	private static final long serialVersionUID = 3100388286862619819L;
	Double[] valori;
	int[]da;
	ArrayList<Città> percorso;
	
	
	public Rovina() {
		super();
		valori = new Double[this.size()];
		da = new int[this.size()];
		percorso = new ArrayList<Città>();
	}
	
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
    
    public ArrayList<Città> dijkstra(boolean xy) {
    	/*se non risetto ogni volta che chiamo il metodo rimangono
    	in memoria i vecchi dati e va in loop infinito*/
    	setup();
		
    	valori[0] = 0.0;
    	for(int i = 1;i<valori.length;i++) {
    		valori[i] = Double.POSITIVE_INFINITY; //in alternativa Double.POSITIVE_INFINITY
    	}
    	while(true) {
    		double min = Double.POSITIVE_INFINITY;
    		int memo = 0;
    		for(Città città:this) {
    			if(!città.passato) {
    				memo = città.getId();
    				min = valori[memo];
    				break;
    			}
    		}
    		int indice = memo;
    		for(int i = memo+1; i <valori.length;i++) {
    			if(valori[i] < min && !this.get(i).passato) {
    				 indice =i;
    			}
    		}
    		Città inControllo = this.get(indice);
    		inControllo.passato = true;
    		for(int vicino: inControllo.getVicini()) {
    			if(!this.get(vicino).passato) {
    				double distanza = valori[indice];
    				if(xy) {
    					distanza += getDistXY(inControllo, this.get(vicino));
    				}else {
    					distanza+= getDistH(inControllo, this.get(vicino));
    				}
    				if(distanza <valori[vicino]) {
    					valori[vicino] = distanza;
    					da[vicino] = inControllo.getId();
    				}
    			}
    		}
    		int cont = 0;
    		for(Città c: this) {
    			if(!c.passato) cont++;
    			
    		}
    		if(cont == 1) break;
    		
    	} 
    	int rovina = this.size()-1;
    	calcolaPercorso(rovina);
    	return percorso;
    }
    public  void calcolaPercorso(int rovina){
    	percorso.add(this.get(rovina));
    	if(valori[rovina]!= 0) calcolaPercorso(da[rovina]); 
    }
    public double getConsumo() {
    	return valori[this.size()-1];
    }
    
    
    
    public void setup() {
    	for(Città c: this) {
    		c.passato = false;
    	}
    	da = new int[this.size()];
    	valori = new Double[this.size()];
    	percorso = new ArrayList<Città>();
    }
}