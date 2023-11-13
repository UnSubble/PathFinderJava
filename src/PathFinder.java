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
	private List<Integer> distanceList;
	
	public PathFinder(int[][] map) {
		changeMap(map);
	}
	
	private void clear() {
		if (ways == null) {
			ways = new ArrayList<>();
			distanceList = new ArrayList<>();
		} else {
			ways.clear();
			distanceList.clear();
		}
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
	
	private void next(List<Point> list, Point p, int count) {
		if (check(p) && !list.contains(p)) {
			List<Point> l = new ArrayList<>(list);
			l.add(p);
			findWays(l, p, count);
				
		}
	}
	
	private void findWays(List<Point> list, Point p, int count) {
		if (count == pathLength) {
			if (list.size() == pathLength + 1) 
				ways.add(list);			
			return;
		}
		next(list, new Point(p.getX() + 1, p.getY()), count + 1);	
		next(list, new Point(p.getX() - 1, p.getY()), count + 1);
		next(list, new Point(p.getX(), p.getY() + 1), count + 1);
		next(list, new Point(p.getX(), p.getY() - 1), count + 1);
	}
	
	private List<List<Point>> getWays(Point start, Point end) {
		int size = Math.min(map.length, map[0].length) - 1;
		findWays(new ArrayList<>(List.of(start)), start, 0);
		while (ways.isEmpty()) {
			for (int i = 0; i < ways.size(); i++) {
				List<Point> l = ways.get(i);
				if (l.get(l.size() - 1).distance(end) != size) {
					ways.remove(i--);
				}
			}
			break;
		}
		System.out.println(ways);
		return null;
	}
	
	public boolean isReachable(Point start, Point end) {
		if (ways.isEmpty()) {
			clear();
			getWays(start, end);
		}
		return !ways.isEmpty();
	}
	
	public List<Point> findShortestWay(Point start, Point end) {
		if (!isReachable(start, end))
			return ways.get(0);
		return Collections.emptyList();
	}

}
