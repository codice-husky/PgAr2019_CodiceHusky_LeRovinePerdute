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
	
	/**
	 * Metodo che date due città applica pitagora per ricavare la 
	 * distanza tra 2 coordinate (x,y)
	 * 
	 * @param a  	prima città da confrontare
	 * @param b		seconda città da confrontare
	 * @return		la distanza tra le due città secondo pitagora
	 * */
    public double getDistXY(Città a,Città b) {
    	double x = a.getCoord().getX() - b.getCoord().getX();
    	double y = a.getCoord().getY() - b.getCoord().getY();
    	double diag = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    	return diag;
    }    
    /**
     * Metodo che calcola la distanza tra 2 città in base alla altitudine
     * @param a  	prima città da confrontare
	 * @param b		seconda città da confrontare
	 * @return		la distanza tra le due città in base alla differenza
	 * 				di altitudine (sempre positiva)
     * */
    public int getDistH(Città a,Città b) {
    	return Math.abs(a.getCoord().getH() - b.getCoord().getH());
    }
    
    /**
     * Algrotimo di Dijkstra converito da come ci è stato spiegato a Java
     * (Non sappiamo il perché ma se metto i commenti nel codice diventa
     * molto più lento)
     * @param xy   	(non è il metodo più bello) se xy è true chiama Dijkstra
     * 				per la squadra Tonatiuh altrimenti Metztli
     * @return 		il percorso da percorrere (al contrario) 
     * */
    
    public LinkedList<Città> dijkstra(boolean xy) {
    	setup();    		
   
    	valori[0] = 0.0;
    	
    	for(int i = 1; i < valori.length; i++) {
    		valori[i] = Double.POSITIVE_INFINITY; 
    	}
    	
    	while(true) {
    		double min = Double.POSITIVE_INFINITY;
    		int indice = 0;
    		
    		for(Città città: this) {
    			if(valori[città.getId()] < min && !città.passato) {
    				indice = città.getId();
    				min = valori[città.getId()];
    			}
    		}
    		Città inControllo = this.get(indice);
    		inControllo.passato = true;
    		for(int vicino: inControllo.getVicini()) {
    			Città ilVicino = this.get(vicino);
    			if(!ilVicino.passato) {
    				double distanza = valori[indice];
    				if(xy) {
    					distanza += getDistXY(inControllo, ilVicino);
    				}else {
    					distanza+= getDistH(inControllo, ilVicino);
    				}
    				if(distanza < valori[vicino]) {
    					valori[vicino] = distanza;
    					da[vicino] = inControllo.getId();
    				}
    			}
    		}
    		int cont = 0;
    		for(Città c: this) {
    			if(!c.passato) cont++;
    			
    		}
    		if(inControllo.getId() == this.size() - 1) break;
    		if(cont == 1) break;
    		
    	} 
    	int rovina = this.size() - 1;
    	calcolaPercorso(rovina);
    	return percorso;
    }
    
    /**
     * Metodo ricorsivo che aggiunge l'id della città precedente a quella
     * che si controlla in modo da ricostruire il percorso al contrario
     * @param rovina	l'id della città che si sta controllando
     */
    
    public  void calcolaPercorso(int rovina){
    	percorso.add(this.get(rovina));
    	if(valori[rovina] != 0) calcolaPercorso(da[rovina]); 
    }
    
    /**
     * Metodo che restituisce il consumo minimo necessario ad arrivare
     * alla rovina perduta
     * 
     * @return il consumo minimo
     * */
    public double getConsumo() {
    	return valori[this.size() - 1];
    }
    
    /**
     * Metodo che funge un po da costruttore solo che deve essere richiamato
     * più volte senza cancellare tutto
     * */
    
    public void setup() {
    	for(Città c: this) {
    		c.passato = false;
    	}
    	valori = new Double[this.size()];
    	da = new int[this.size()];
    	percorso = new LinkedList<Città>();
    }    
}