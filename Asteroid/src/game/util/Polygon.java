package game.util;

public class Polygon {
	
	private int[] xpoints;
	private int[] ypoints;
	private int npoints;
	private int width;
	private int height;
	
	/*
	 * Crée un polygone avec xpoints points en X,
	 * ypoints points en Y et npoints nombres de points
	 */
	public Polygon(int[] xpoints, int[] ypoints, int npoints, int width, int height){
		this.xpoints = xpoints;
		this.ypoints = ypoints;
		this.npoints = npoints;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Polygon other){
		int tw = this.width;
		int th = this.height;
		int ow = other.width;
		int oh = other.height;
		if (ow <= 0 || oh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		int tx = this.getXpoint(0);
		int ty = this.getYpoint(0);
		int ox = other.getXpoint(0);
		int oy = other.getYpoint(0);
		ow += ox;
		oh += oy;
		tw += tx;
		th += ty;
		//      overflow || intersect
		return ((ow < ox || ow > tx) &&
				(oh < oy || oh > ty) &&
				(tw < tx || tw > ox) &&
				(th < ty || th > oy));
	}
	
	public int getXpoint(int i){
		return xpoints[i];
	}
	
	public void setXpoint(int i, int value){
		xpoints[i] = value;
	}

	public int[] getXpoints() {
		return xpoints;
	}

	public void setXpoints(int[] xpoints) {
		this.xpoints = xpoints;
	}

	public int getYpoint(int i){
		return ypoints[i];
	}
	
	public void setYpoint(int i, int value){
		ypoints[i] = value;
	}
	
	public int[] getYpoints() {
		return ypoints;
	}

	public void setYpoints(int[] ypoints) {
		this.ypoints = ypoints;
	}

	public int getNpoints() {
		return npoints;
	}

	public void setNpoints(int npoints) {
		this.npoints = npoints;
	}
}
