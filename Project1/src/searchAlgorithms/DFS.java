package searchAlgorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFS extends Search{
	
	public DFS(char[][] maze) {
		super(maze);
	}
	
	public List<Index> search() {
		Index starting = SearchUtils.getStartingIndex(maze);
		Map<Index, Index[]> map = SearchUtils.generateAdjacencyList(maze);
		Stack<Index> frontier = new Stack<>();
		List<Index> solutionPath = new ArrayList<>();
		
		frontier.add(starting);
		while (frontier.size() > 0) {
			Index expand = frontier.pop();
			expanded.add(expand);
			Index[] adjNodes = map.get(expand);
			
			for (Index i : adjNodes) {
				if (!expanded.contains(i)) {
					i.prev = expand;
					if (SearchUtils.isGoal(i, maze)) {
						for (Index p = i; p != null; p = p.prev) {
							solutionPath.add(new Index(p.row, p.column, null));	
						}
					} else {
						frontier.add(i);
					}	
				}
			}
		}
		return solutionPath;
	}
}