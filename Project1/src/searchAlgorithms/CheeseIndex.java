package searchAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class CheeseIndex extends Index {

	List<CheeseIndex> cheeses;
	
	public CheeseIndex() {
		cheeses = new ArrayList<>();
	}
	
	public CheeseIndex(int r, int c, Index p) {
		super(r, c, p);
		cheeses = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void addCheese(Index index) {
		this.cheeses.add((CheeseIndex) index);
	}
	
	public boolean equalsCheese(CheeseIndex o) {
		if (this.row == ((Index) o).row && this.column == ((Index) o).column) {
			
			if (this.cheeses.size() >= o.cheeses.size()) {
				for (CheeseIndex index : this.cheeses) {
					if (!(o.cheeses.contains(index))) {
						return false;
					}
				}
			} else {
				for (CheeseIndex index : o.cheeses) {
					if (!(this.cheeses.contains(index))) {
						return false;
					}
				}
			}
			
			return true;
			
		} 
		return false;
		
	}
}