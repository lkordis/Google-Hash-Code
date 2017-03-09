package com.google.hashcode;

public class Dimension implements Comparable<Dimension>{
	private int rows;
	private int columns;
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	public Dimension(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
	}
	
	@Override
	public String toString() {
		return "Dimension [rows=" + rows + ", columns=" + columns + "]";
	}
	
	@Override
	public int compareTo(Dimension o) {
		int result = this.getColumns() * this.getRows();
		return Integer.valueOf(result).compareTo(Integer.valueOf(o.columns*o.rows));
	}
	
}
