
public class Main {

	public static void main(String[] args) {
		int[][] s = new int[5][8];	
		
		PathFinder path = new PathFinder(s);
		System.out.println(path.findShortestWay(new Point(0,0), new Point(1,1)));
	}

}
