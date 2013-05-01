package game.util;

public class Polygon {
	
	//private final int[] defaultXpoints;
	//private final int[] defaultYpoints;
	private int[] xpoints;
	private int[] ypoints;
	private int centerX=20;
	private int centerY=20;
	
	/*
	 * Crée un polygone avec xpoints points en X,
	 * ypoints points en Y et npoints nombres de points
	 */
	public Polygon(int[] xpoints, int[] ypoints){ //TODO remove width et height
		//this.defaultXpoints = xpoints;
		//this.defaultYpoints = ypoints;
		this.xpoints=xpoints.clone();
		this.ypoints=ypoints.clone();
	}
	
	public boolean intersects(Polygon other){
		System.out.println("("+this.getLowestX() + ","+this.getLowestY()+")");
		System.out.println("("+other.getLowestX() + ","+other.getLowestY()+")");
		System.out.println("h: "+this.getHeight() +" "+other.getHeight());
		System.out.println("w: "+this.getWidth() +" "+other.getWidth());
		int tw = this.getWidth();
		int th = this.getHeight();
		int ow = other.getWidth();
		int oh = other.getHeight();
		if (ow <= 0 || oh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		int tx = this.getLowestX();
		int ty = this.getLowestY();
		int ox = other.getLowestX();
		int oy = other.getLowestY();
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
	
	public void moveTo(int x, int y){
		for(int i=0;i<getNpoints();i++){
			xpoints[i]+=x-centerX;
			ypoints[i]+=y-centerY;
		}
		centerX=x;
		centerY=y;
	}
	
	public void rotate(float angle){
		double sin = Math.sin((double)angle);
		double cos = Math.cos((double)angle);
		/*int centerX=(int) ((this.getWidth()/2)+.5);
		int centerY=(int) ((this.getWidth()/2)+.5);*/
		for(int i=0;i<this.getNpoints();i++){
			int dx=xpoints[i]-centerX;
			int dy=ypoints[i]-centerY;
			this.xpoints[i]=(int) (centerX+dx*cos-dy*sin);
			this.ypoints[i]=(int) (centerY+dx*sin+dy*cos);
		}
	}
	
	public int getHeight(){
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<getNpoints();i++){
			if(ypoints[i]<min){
				min=ypoints[i];
			}
			else if(ypoints[i]>max){
				max=ypoints[i];
			}
		}
		return max-min;
	}
	
	public int getWidth(){
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<getNpoints();i++){
			if(xpoints[i]<min){
				min=xpoints[i];
			}
			else if(xpoints[i]>max){
				max=xpoints[i];
			}
		}
		return max-min;
	}
	
	public int getLowestX(){
		int lo=Integer.MAX_VALUE;
		for(int i=0;i<getNpoints();i++){
			if(this.xpoints[i]<lo)
				lo=this.xpoints[i];
		}
		return lo;
	}
	public int getLowestY(){
		int lo=Integer.MAX_VALUE;
		for(int i=0;i<getNpoints();i++){
			if(this.ypoints[i]<lo)
				lo=this.ypoints[i];
		}
		return lo;
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
	
	public int getNpoints(){
		return this.xpoints.length;
	}
}
