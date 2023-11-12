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
	private int pathLength;
	private List<List<Point>> ways;
	
	public PathFinder(int[][] map) {
		changeMap(map);
	}
	
	public void changeMap(int[][] newMap) {
		this.map = newMap;
		pathLength = Math.max(newMap.length, newMap[0].length) / 2 + 1;
		ways = new ArrayList<>();
	}
	
	private boolean check(Point point) {
		return point.getX() > -1 && point.getX() < map[0].length && point.getY() > -1 && 
				point.getY() < map[0].length && map[point.getX()][point.getY()] == 0;
	}
	
	private void findWays(List<Point> list, Point p, int count) {
		if (count == pathLength) {
			System.out.println(list);
			if (list.size() == pathLength + 1) {
				ways.add(list);
			}				
			return;
		}
		Point px = new Point(p.getX() + 1, p.getY());
		Point nx = new Point(p.getX() - 1, p.getY());
		Point py = new Point(p.getX(), p.getY() + 1);
		Point ny = new Point(p.getX(), p.getY() - 1);
		if (check(px) && !list.contains(px)) {
			List<Point> l = new ArrayList<>(list);
			l.add(px);
			findWays(l, px, count + 1);
			
		}	
		if (check(nx) && !list.contains(nx)) {
			List<Point> l = new ArrayList<>(list);
			l.add(nx);
			findWays(l, nx, count + 1);

				
		}
		if (check(py) && !list.contains(py)) {
			List<Point> l = new ArrayList<>(list);
			l.add(py);
			findWays(l, py, count + 1);

				
		}
		if (check(ny) && !list.contains(ny)) {
			List<Point> l = new ArrayList<>(list);
			l.add(ny);
			findWays(l, ny, count + 1);				
		}
	}
	
	private List<List<Point>> getWays(Point start, Point end) {
		findWays(new ArrayList<>(List.of(start)), start, 0);
		int size = Math.min(map.length, map[0].length) - 1;
		for (int i = 0; i < ways.size(); i++) {
			List<Point> l = ways.get(i);
			if (l.get(l.size() - 1).distance(end) != size) {
				ways.remove(i--);
			}
		}
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
