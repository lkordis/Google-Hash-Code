package com.google.hashcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.omg.Messaging.SyncScopeHelper;

public class Pizza {

	Thing[][] pizza;
	private int l,h,rows,columns;
	private List<Slice> slices;
		
	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Pizza(Path file) {
		readInput(file);
	}
	
	private void readInput(Path file) {
		
		List<String> input;
		
		try {
			input = Files.readAllLines(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		String instruction[] = input.remove(0).split(" ");
		
		rows = Integer.parseInt(instruction[0]);
		columns = Integer.parseInt(instruction[1]);
		l = Integer.parseInt(instruction[2]);
		h= Integer.parseInt(instruction[3]);
		
		pizza = new Thing[rows][columns];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				pizza[i][j] = input.get(i).charAt(j) == 'M' ? Thing.Mushroom : Thing.Tomato;
			}
		}
	}
	
	public void writeOutput(String fileName){
		File out = new File(fileName);
		
		try {
			Writer writer = new BufferedWriter(new FileWriter(out));
			writer.write(Integer.toString(slices.size()));
			writer.write(String.format("%n"));
			
			slices.forEach((s) -> {
				try {
					writer.write(s.toString());
					writer.write(String.format("%n"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printPizza() {
		for(int i = 0; i < pizza.length; i++) {
			for(int j = 0; j < pizza[0].length; j++) {
				System.out.print(pizza[i][j] + "   ");
			}
			System.out.println();
		}

	}
	
	public List<Dimension> generateCombinations(){
		List<Dimension> dimensions = new ArrayList<>();
		
		for(int i=l; i <= h; i++){
			for(int j=1; j <= h; j++){
				if(i*j <= h && i*j > l){
					dimensions.add(new Dimension(i, j));
				}
			}
		}
		
		Collections.sort(dimensions);
		
		return dimensions;
	}
	
	public void findSlices() {
		List<Dimension> dimensions = generateCombinations();
		Collections.reverse(dimensions);
		
		slices = new ArrayList<>();
		
		for(Dimension currentDimension : dimensions){
			
			for(int i = 0; i < this.rows; i++){
				for(int j = 0; j<this.columns; j++){
					if(!hasNull(i,j,currentDimension) && hasL(i,j,currentDimension)){
						slices.add(new Slice(currentDimension,i,j));
						renderNull(i,j,currentDimension);
					} 
				}
				
			}
		}
		
		int res = 0;
		for(Slice slice : slices){
			res += slice.dimension.getRows()*slice.dimension.getColumns();
		}
		
		System.out.println(res);
	}

	private void renderNull(int x, int y, Dimension currentDimension) {
		for(int i = x; i < currentDimension.getRows() + x; i++){
			for(int j = y; j < currentDimension.getColumns() + y; j++){
				try {
					pizza[i][j] = null;
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
				}
			}
		}		
	}

	private boolean hasNull(int x, int y, Dimension currentDimension) {
		for(int i = x; i < currentDimension.getRows() + x; i++){
			for(int j = y; j < currentDimension.getColumns() + y; j++){
				try {
					if(pizza[i][j] == null){
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasL(int x, int y, Dimension currentDimension) {
		int M = 0, T = 0;
		
		for(int i = x; i < currentDimension.getRows() + x; i++){
			for(int j = y; j < currentDimension.getColumns() + y; j++){
				try {
					if(pizza[i][j].equals(Thing.Mushroom)){
						M++;
					} else {
						T++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
				}
			}
		}
		
		if(M >= l && T >= l){
			return true;
		}
		return false;
	}
	
	
}
