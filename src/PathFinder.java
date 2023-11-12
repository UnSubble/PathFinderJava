import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathFinder {
	private int[][] map;
	private Set<Point> waySet;
	private int pathLength;
	
	public PathFinder(int[][] map) {
		changeMap(map);
	}
	
	public void changeMap(int[][] newMap) {
		this.map = newMap;
		waySet = new HashSet<>();
		pathLength = newMap.length;
	}
	
	private boolean check(Point point) {
		return point.getX() > -1 && point.getX() < map[0].length - 1 && point.getY() > -1 && 
				point.getY() < map[0].length - 1 && map[point.getX()][point.getY()] == 0;
	}
	
	private void findWays(Set<Point> way, Point p) {
		if (way.contains(p))
			return;
	}
	
	private List<List<Point>> getWays(Point start, Point end) {
		return null;
	}
	
	public boolean isReachable(Point start, Point end) {
		return !getWays(start, end).isEmpty();
	}
	
	public List<Point> findShortestWay(Point start, Point end) {
		if (getWays(start, end).isEmpty())
			return Collections.emptyList();
		return null;
	}

}
