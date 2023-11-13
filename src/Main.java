
public class Main {

	public static void main(String[] args) {
		int[][] s = {
				{0,1,1,0,1,1,0},
				{0,0,0,0,1,0,0},
				{0,1,1,1,0,0,1},
				{0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0}
		};	
		
		PathFinder path = new PathFinder(s);
		System.out.println(path.findShortestWay(new Point(0,0), new Point(0,6)));
	}

}
