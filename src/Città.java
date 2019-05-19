import java.util.LinkedList;

public class Città {
    private Coordinata coord;
    private String nome;
    private int id;
    private LinkedList<Integer> vicini;
    
    public Città(int _x, int _y, int _h, String _nome, int _id ){
        this.coord = new Coordinata(_x, _y, _h);
        this.nome = _nome;
        this.id = _id;
        this.vicini = new LinkedList<Integer>();
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
}
