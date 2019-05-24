import java.util.ArrayList;
import java.util.LinkedList;

public  class Rovina extends ArrayList<Città>{
	
	private static final long serialVersionUID = 3100388286862619819L;
	Double[] valori;
	int[]da;
	LinkedList<Città> percorso;
	
	
	
	public Rovina() {
		super();
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
    
    public LinkedList<Città> dijkstra(boolean xy) {
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
    		/*
    		//vecchio
    		for(int i = memo+1; i <valori.length;i++) {
    			if(valori[i] < min && !this.get(i).passato) {
    				 indice =i;
    				 min = valori[i];
    				 	
    			}
    		}*/
    		//nuovo
    		for(Città città: this) {
    			if(valori[città.getId()]<min && !città.passato) {
    				indice = città.getId();
    				min = valori[città.getId()];
    			}
    		}
    		
    		
    		
    		//conviene usare l'arrayList
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
    		if(inControllo.getId()==this.size()-1)break;
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
    	valori = new Double[this.size()];
    	da = new int[this.size()];
    	percorso = new LinkedList<Città>();
    }
    
    
    /*public double[] dijkstraV2() {
    	int lunghezza = this.size();
    	boolean visitati[] = new boolean[lunghezza];
    	double distanza[] = new double[lunghezza];
    	distanza[0] = 0;
    	for(int i = 1;i<lunghezza;i++) {
    		distanza[i] = Double.POSITIVE_INFINITY;
    	}
    	for(int i = 0;i<lunghezza-1;i++) {
    		int minLung = trovaMin(distanza,visitati);
    		visitati[minLung] = true;
    		for(int j = 0;j<lunghezza;j++) {
    			if(this.get(minLung).getVicini().get(j) != 0 && !visitati[j] && distanza[minLung]!= Double.POSITIVE_INFINITY) {
    				double nuovaDistanza = distanza[minLung] + this.get(minLung).getVicini().get(j);
    				if(nuovaDistanza<distanza[j]) {
    					distanza[j] = nuovaDistanza;
    				}
    			}
    		}
    	}
    	return distanza;
    }*/
    
    public int trovaMin(double distanza[],boolean visitati[]) {
    	int minLungh = -1;
    	for(int i = 0;i< distanza.length;i++) {
    		if(!visitati[i]&& (minLungh == -1 || distanza[i]<distanza[minLungh] )){
    			minLungh = i;
    		}
    	}
    	return minLungh;
    }
    
    
}