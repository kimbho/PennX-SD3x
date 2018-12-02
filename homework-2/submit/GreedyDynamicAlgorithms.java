
import java.util.*;

public class GreedyDynamicAlgorithms {

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	
	//If two intervals are interselected
	public static boolean doOverlap(Interval red, Interval blue) {

		if(red.start < blue.start) {

			return red.finish >= blue.start;
		} else {

			return red.start <= blue.finish;
		}
	}
	
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
		
		Interval.sortByStartTime(blue);
		Interval.sortByFinishTime(red);
		int i = 0, j = 0, count = 0;
		while(i < blue.size() && j < red.size()) {

			//Remove the red intervals that do not Overlap at all
			if(!doOverlap(red.get(j), blue.get(i))) {

				j++;
				continue;
			}

			//find the last ending red interval that overalps with current blue interval
			while(j < red.size() && doOverlap(red.get(j), blue.get(i))) {

				j++;
			}

			count++;

			//Skip the blue intervals that already intersect with selected red interval
			while(doOverlap(red.get(j-1), blue.get(i))) {

				i++;
				//All blue intervals traversed
				if(i == blue.size()) {

					return count;
				}
			}
		}
		if(i < blue.size()) return -1; //Error: Enough red intervals are not present
		return count;
	}

	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {

		int m = grid.length; int n = grid[0].length;
		int[][] min = new int[m][n];
		int minPath = minPath(grid, 0, 0, min);
		List<Direction> list = new ArrayList<>();
		int i = 0; int j = 0;
		while (true) {

			if (i == m-1 && j == n-1) break;

			if (!isValidSquare(grid, i, j+1) || (isValidSquare(grid, i+1, j) && min[i+1][j] < min[i][j+1])) {

				list.add(Direction.DOWN);
				i++;

			} else {

				list.add(Direction.RIGHT);
				j++;
			}
		}

		return list;
	}

	// returns whether grid[row][col] is valid
	public static boolean isValidSquare(int[][] grid, int row, int col) {

		return row < grid.length && col < grid[0].length;
	}

	

	public static int minPath(int[][] grid, int row, int col, int[][] memo) {

		if (!isValidSquare(grid, row, col)) { // invalid square

			return Integer.MAX_VALUE;
		}

		

		if (row == grid.length - 1 && col == grid[0].length - 1) { // at bottom right

			memo[row][col] = grid[row][col];
			return grid[row][col];
		} 

		

		if (memo[row][col] == 0) { // if not yet in memo 

			memo[row][col] = grid[row][col] + 
                Math.min(minPath(grid, row, col+1, memo), minPath(grid, row+1, col, memo));
		}
		return memo[row][col];		
	}
	
	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}
	
}