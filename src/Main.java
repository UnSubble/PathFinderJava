
public class Main {

	public static void main(String[] args) {
		int[][] s = {{0,0,0}, {0,0,0}, {0,0,0}};
		PathFinder path = new PathFinder(s);
		path.findShortestWay(new Point(0,0), new Point(2,2));
	}

}
