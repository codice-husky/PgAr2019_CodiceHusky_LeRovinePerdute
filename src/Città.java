import java.util.LinkedList;

public class Città {
	private int id;
	private String nome;
	private Coordinata coord;    
    private LinkedList<Integer> vicini;
    boolean passato;
    
    public Città(int _id,String _nome, String x, String y, String h, LinkedList<Integer> _vicini){
        this.coord = new Coordinata(x, y, h);
        this.nome = _nome;
        this.id = _id;
        this.vicini = _vicini;
        this.passato = false; //per dijkstra
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
    public LinkedList<Integer> getVicini(){
    	return vicini;
    }
}
