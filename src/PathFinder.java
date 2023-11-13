import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		pathLength = 3;
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
		if (count == pathLength || list.get(list.size() - 1).equals(end)) {
			ways.add(list);			
			return;
		}
		next(list, new Point(p.getX() + 1, p.getY()), count + 1, end);	
		next(list, new Point(p.getX() - 1, p.getY()), count + 1, end);
		next(list, new Point(p.getX(), p.getY() + 1), count + 1, end);
		next(list, new Point(p.getX(), p.getY() - 1), count + 1, end);	
	}
	
	private List<List<Point>> getWay(Point start, Point end) {
		List<List<Point>> pathList = new ArrayList<>();
		findWays(new ArrayList<>(List.of(start)), start, 0, end);
		while (pathList.isEmpty()) {
			Set<Point> lastPoints = new HashSet<>();
			List<List<Point>> temp = new ArrayList<>(ways);
			ways.clear();
			pathLength = Math.min(4, temp.size());
			if (pathLength == 0)
				break;
			int distance = start.distance(end);;	
			for (List<Point> t : temp) 
				distance = Math.min(distance, t.get(t.size() - 1).distance(end));
			for (List<Point> t : temp) {
				Point s = t.get(t.size() - 1);
				if (lastPoints.contains(t.get(t.size() - 1)))
					continue;
				else
					lastPoints.add(t.get(t.size() - 1));
				if (s.distance(end) == distance) {
					if (s.distance(end) == 0) {
						pathList.add(t);
						continue;
					}	
					findWays(t, t.get(t.size() - 1), 0, end);
				} else
					ways.remove(t);
			}
		}
		return pathList;
	}
	
	public boolean isReachable(Point start, Point end) {
		List<List<Point>> l = Collections.emptyList();
		if (ways.isEmpty()) {
			clear();
			l = getWay(start, end);
		}
		return !l.isEmpty();
	}
	
	public List<Point> findShortestWay(Point start, Point end) {
		List<List<Point>> l = Collections.emptyList();
		if (ways.isEmpty()) {
			clear();
			l = getWay(start, end);
		}
		return l.isEmpty() ? Collections.emptyList() : l.get(0);
	}

}
