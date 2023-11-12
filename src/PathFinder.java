import java.util.List;

public class PathFinder {
	private int[][] map;
	
	public PathFinder(int[][] map) {
		this.map = map;
	}
	
	public void changeMap(int[][] newMap) {
		map = newMap;
	}
	
	public boolean isReachable(Point start, Point end) {
		return false;
	}
	
	public List<Point> findShortestWay() {
		return null;
	}
}
