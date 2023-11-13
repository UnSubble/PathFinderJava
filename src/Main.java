
public class Main {

	public static void main(String[] args) {
		int n = 8;
		int[][] s = new int[n][n];
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = a;
		}
		PathFinder path = new PathFinder(s);
		System.out.println(path.findShortestWay(new Point(0,0), new Point(n - 1, n - 1)));
	}

}
