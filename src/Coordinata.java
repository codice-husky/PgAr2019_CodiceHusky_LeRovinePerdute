public class Coordinata {
    private int x;
    private int y;
    private int h;
    
    public Coordinata(String _x, String _y, String _h){
        this.x = Integer.parseInt(_x);
        this.y = Integer.parseInt(_y);
        this.h = Integer.parseInt(_h);
    }

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public int getH() {
		return h;
	}
    
}
