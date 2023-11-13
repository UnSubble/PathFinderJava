
public class Main {

	public static void main(String[] args) {
		int[][] s = new int[5][8];
		
		for (int i = 0; i < 5; i++) {
			s[i] = new int[5];
		}
		
		PathFinder path = new PathFinder(s);
		System.out.println(path.findShortestWay(new Point(4,4), new Point(0,0)));
	}

}
