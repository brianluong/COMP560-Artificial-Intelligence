package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class CheeseIndex extends Index {

	List<CheeseIndex> cheeses;
	public CheeseIndex(int r, int c, Index p) {
		super(r, c, p);
		cheeses = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void addCheese(Index index) {
		this.cheeses.add((CheeseIndex) index);
	}
}