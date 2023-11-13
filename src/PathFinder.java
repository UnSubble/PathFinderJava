import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PathFinder {
	private int[][] map;
	private int pathLength;
	private List<List<Point>> ways;
	
	public PathFinder(int[][] map) {
		changeMap(map);
	}
	
	private void clear() {
		if (ways == null)
			ways = new ArrayList<>();
		else 
			ways.clear();
	}
	
	public void changeMap(int[][] newMap) {
		this.map = newMap;
		pathLength = Math.max(newMap.length, newMap[0].length) / 2 + 1;
		clear();
	}
	
	private boolean check(Point point) {
		return point.getX() > -1 && point.getX() < map[0].length && point.getY() > -1 && 
				point.getY() < map[0].length && map[point.getX()][point.getY()] == 0;
	}
	
	private void next(List<Point> list, Point p, int count, Point end) {
		if (check(p) && !list.contains(p)) {
			List<Point> l = new ArrayList<>(list);
			l.add(p);
			findWays(l, p, count, end);
		}
	}
	
	private void findWays(List<Point> list, Point p, int count, Point end) {
		if (count == pathLength) { 
			ways.add(list);			
			return;
		}
		Point px = new Point(p.getX() + 1, p.getY());
		Point nx = new Point(p.getX() - 1, p.getY());
		Point py = new Point(p.getX(), p.getY() + 1);
		Point ny = new Point(p.getX(), p.getY() - 1);
		next(list, px, count + 1, end);	
		next(list, nx, count + 1, end);
		next(list, py, count + 1, end);
		next(list, ny, count + 1, end);
		
		if (list.get(list.size() - 1).equals(end)) {
			ways.add(list);
			return;
		}		
	}
	
	private List<List<Point>> getWays(Point start, Point end) {
		List<List<Point>> pathList = new ArrayList<>();
		findWays(new ArrayList<>(List.of(start)), start, 0, end);
		int n = 3;
		while (n-- > 0) {
			List<List<Point>> temp = new ArrayList<>(ways);
			ways.clear();
			pathLength = (temp.size() == 0 ? 1 : temp.get(0).size()) - 1;
			if (pathLength == 0)
				break;
			int distance = temp.get(0).get(pathLength).distance(end);;
			
			for (int i = 1; i < temp.size(); i++) 
				distance = Math.min(distance, temp.get(i).get(temp.get(i).size() - 1).distance(end));
			for (List<Point> t : temp) {
				Point s = t.get(t.size() - 1);
				if (s.distance(end) == distance) {
					if (s.distance(end) == 0) {
						pathList.add(t);
						continue;
					}	
					findWays(t, t.get(pathLength), 0, end);
				} else {
					ways.remove(t);
				}
			}
		}
		return pathList;
	}
	
	public boolean isReachable(Point start, Point end) {
		List<List<Point>> l = Collections.emptyList();
		if (ways.isEmpty()) {
			clear();
			l = getWays(start, end);
		}
		return !l.isEmpty();
	}
	
	public List<Point> findShortestWay(Point start, Point end) {
		List<List<Point>> l = Collections.emptyList();
		if (ways.isEmpty()) {
			clear();
			l = getWays(start, end);
		}
		return l.isEmpty() ? Collections.emptyList() : l.get(0);
	}

}
