import java.util.LinkedList;

public  class Rovina extends LinkedList<Città>{
	
	private static final long serialVersionUID = 3100388286862619819L;
	Double[] valori;
	int[]da;
	
	
	public Rovina() {
		valori = new Double[this.size()];
		da = new int[this.size()];
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
    
    public void dijkstra() {
    	LinkedList<Città> copia= this;
    	
    	valori = new Double[this.size()];
		da = new int[this.size()];
		
    	valori[0] = 0.0;
    	for(int i = 1;i<valori.length;i++) {
    		valori[i] = Double.POSITIVE_INFINITY; //in alternativa Double.POSITIVE_INFINITY
    	}
    	while(true) {
    		double min = Double.POSITIVE_INFINITY;
    		int memo = 0;
    		for(Città città:copia) {
    			if(!città.passato) {
    				memo = città.getId();
    				min = valori[memo];
    				break;
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
    				if(distanza <valori[vicino]) {
    					valori[vicino] = distanza;
    					da[vicino] = inControllo.getId();
    				}
    			}
    		}
    		int cont = 0;
    		for(Città c: copia) {
    			if(!c.passato) cont++;
    			
    		}
    		if(cont == 1) break;
    	}
    	System.out.println(calcolaPercorso());
    	
    }
    public String calcolaPercorso() {
    	int idRovina = this.size()-1;
    	int perc = da[idRovina];
    	return percorso(perc) +" -> "+ this.get(idRovina).getNome() ;
    }
    
    public String percorso(int perc) {
    	if(perc == 0) return this.get(perc).getNome();
    	return percorso(da[perc])+" -> "+this.get(perc).getNome();
    }
}
