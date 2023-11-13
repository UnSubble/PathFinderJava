import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathFinder {
	private int[][] map;
	private int pathLength;
	private Set<List<Point>> ways;
	
	public PathFinder(int[][] map) {
		changeMap(map);
	}
	
	private void clear() {
		if (ways == null)
			ways = new HashSet<>();
		else 
			ways.clear();
	}
	
	public void changeMap(int[][] newMap) {
		this.map = newMap;
		pathLength = 4;
		clear();
	}
	
	private boolean check(Point point) {
		return point.getX() > -1 && point.getX() < map.length && point.getY() > -1 && 
				point.getY() < map[0].length && map[point.getX()][point.getY()] == 0;
	}
	
	private void next(List<Point> path, Point point, int count, Point end) {
		if (check(point) && !path.contains(point)) {
			List<Point> newPath = new ArrayList<>(path);
			newPath.add(point);
			findWays(newPath, point, count, end);
		}
	}
	
	private void findWays(List<Point> path, Point p, int count, Point end) {
		if (count == pathLength || path.get(path.size() - 1).equals(end)) {
			ways.add(path);	
			return;
		}
		next(path, new Point(p.getX() + 1, p.getY()), count + 1, end);	
		next(path, new Point(p.getX() - 1, p.getY()), count + 1, end);
		next(path, new Point(p.getX(), p.getY() + 1), count + 1, end);
		next(path, new Point(p.getX(), p.getY() - 1), count + 1, end);	
	}
	
	private List<List<Point>> getWay(Point start, Point end) {
		List<List<Point>> pathList = new ArrayList<>();
		findWays(new ArrayList<>(List.of(start)), start, 0, end);
		int length = 0;
		while (pathList.isEmpty()) {
			Set<Point> lastPoints = new HashSet<>();
			List<List<Point>> tempWays = new ArrayList<>(ways);
			ways.clear();
			pathLength = Math.min(4, tempWays.size());
			if (pathLength == 0)
				break;
			int distance = start.distance(end);;
			
			length += pathLength;
			for (int i = 0; i < tempWays.size(); i++) {
				List<Point> tempPath = tempWays.get(i);
				distance = Math.max(distance, tempPath.get(tempPath.size() - 1).distance(end));
				if (tempPath.size() < length) {
					if (tempPath.get(tempPath.size() - 1).distance(end) == 0) {
						pathList.add(tempPath);
						break;
					} else {
						tempWays.remove(i--);
					}
				}
			}
			for (List<Point> tempPath : tempWays) {
				Point lastPoint = tempPath.get(tempPath.size() - 1);				
				if (lastPoints.contains(lastPoint)) 	
					continue;										
				else
					lastPoints.add(lastPoint);
				if (lastPoint.distance(end) != distance) {
					if (lastPoint.distance(end) == 0) {
						pathList.add(tempPath);
						break;
					}	
					findWays(tempPath, lastPoint, 0, end);
				} else
					ways.remove(tempPath);
			}
		}
		return pathList;
	}
	
	public boolean isReachable(Point start, Point end) {
		return findShortestWay(start, end).isEmpty();
	}
	
	public List<Point> findShortestWay(Point start, Point end) { 
		clear();
		List<List<Point>> l = getWay(start, end);
		return l.isEmpty() ? Collections.emptyList() : l.get(0);
	}

}
