package searchAlgorithms;

public class Index {
	int row;
	int column;
	Index prev;
	
	public Index(int r, int c, Index p) {
		row = r;
		column = c;
		prev = p;
	}
	
	// todo make this a better hash code function
	@Override
	public int hashCode() {
		return (int) (Math.pow(this.row, 7) + this.column*4734);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this.row == ((Index) o).row && this.column == ((Index) o).column) {
			return true;
			
		} 
		return false;
	}
}
