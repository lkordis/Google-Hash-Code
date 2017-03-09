package com.google.hashcode;

public class Slice {
	Dimension dimension;
	int startRow, startColumn;
	
	public Slice(Dimension dimension, int startRow, int startColumn) {
		super();
		this.dimension = dimension;
		this.startRow = startRow;
		this.startColumn = startColumn;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getStartColumn() {
		return startColumn;
	}
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(startRow);
		sb.append(" ");
		sb.append(startColumn);
		sb.append(" ");
		sb.append(dimension.getRows() + startRow - 1);
		sb.append(" ");
		sb.append(dimension.getColumns() + startColumn - 1);
		sb.append(" ");
		
		return sb.toString();
	}
	
	
}
