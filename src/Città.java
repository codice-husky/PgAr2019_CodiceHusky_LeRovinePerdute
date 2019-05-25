import java.util.ArrayList;

public class Città {
	private int id;
	private String nome;
	private Coordinata coord;    
    private ArrayList<Integer> vicini;
    boolean passato;
    
    public Città(int _id, String _nome, String x, String y, String h, ArrayList<Integer> _vicini){
        this.coord = new Coordinata(x, y, h);
        this.nome = _nome;
        this.id = _id;
        this.vicini = _vicini;
        this.passato = false; //(in dijkstra)per capire se ho già controllato questa città
    }
    
    public Coordinata getCoord(){
        return coord;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getId(){
        return id;
    }
    
    public void addCittà(int città) {
    	vicini.add(città);
    }
    public ArrayList<Integer> getVicini(){
    	return vicini;
    }
}