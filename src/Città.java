public class Città {
    private static Coordinata coord;
    private static String nome;
    private static int id;
    
    public Città(int _x, int _y, int _h, String _nome, int _id ){
        this.coord = new Coordinata(_x, _y, _h);
        this.nome = _nome;
        this.id = _id;
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
}
