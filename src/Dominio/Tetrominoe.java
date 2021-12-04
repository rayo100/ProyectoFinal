package Dominio;

import Presentacion.BoardPanel;

import java.awt.Color;
import java.util.Random;

public enum Tetrominoe {

	TypeI(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX), 4, 4, 1, new boolean[][]{
			{
					false, false, false, false,
					true, true, true, true,
					false, false, false, false,
					false, false, false, false,
			},
			{
					false, false, true, false,
					false, false, true, false,
					false, false, true, false,
					false, false, true, false,
			},
			{
					false, false, false, false,
					false, false, false, false,
					true, true, true, true,
					false, false, false, false,
			},
			{
					false, true, false, false,
					false, true, false, false,
					false, true, false, false,
					false, true, false, false,
			}
	},new String[]{"Negro","Plateado","Rojo", "Dorado"}),

	TypeL(new Color(BoardPanel.COLOR_MAX, 127, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][]{
			{
					false, false, true,
					true, true, true,
					false, false, false,
			},
			{
					false, true, false,
					false, true, false,
					false, true, true,
			},
			{
					false, false, false,
					true, true, true,
					true, false, false,
			},
			{
					true, true, false,
					false, true, false,
					false, true, false,
			}
	},new String[]{"Negro","Plateado","Rojo", "Dorado"}),

	TypeO(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 2, 2, 2, new boolean[][]{
			{
					true, true,
					true, true,
			},
			{
					true, true,
					true, true,
			},
			{
					true, true,
					true, true,
			},
			{
					true, true,
					true, true,
			}
	},new String[]{"Negro","Plateado","Rojo", "Dorado"}),

	TypeS(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][]{
			{
					false, true, true,
					true, true, false,
					false, false, false,
			},
			{
					false, true, false,
					false, true, true,
					false, false, true,
			},
			{
					false, false, false,
					false, true, true,
					true, true, false,
			},
			{
					true, false, false,
					true, true, false,
					false, true, false,
			}
	},new String[]{"Negro","Plateado","Rojo", "Dorado"}),

	TypeT(new Color(128, BoardPanel.COLOR_MIN, 128), 3, 3, 2, new boolean[][]{
			{
					false, true, false,
					true, true, true,
					false, false, false,
			},
			{
					false, true, false,
					false, true, true,
					false, true, false,
			},
			{
					false, false, false,
					true, true, true,
					false, true, false,
			},
			{
					false, true, false,
					true, true, false,
					false, true, false,
			}
	},new String[]{"Negro","Plateado","Rojo", "Dorado"});

	private Color baseColor;
	private Color lightColor;
	private Color darkColor;
	private int spawnCol;
	private int spawnRow;
	private int dimension;
	private int rows;
	private int cols;
	private boolean[][] tiles;
	private String[] types;
	private String type;
	
	Tetrominoe(Color color, int dimension, int cols, int rows, boolean[][] tiles, String[] types) {
		Random r = new Random();
		this.type = types[r.nextInt(2)];
		this.baseColor = color;
		this.lightColor = Color.BLACK;
		this.darkColor = Color.BLACK;
		this.dimension = dimension;
		this.tiles = tiles;
		this.cols = cols;
		this.rows = rows;
		this.spawnCol = 5 - (dimension >> 1);
		this.spawnRow = getTopInset(0);
		this.type = types[r.nextInt(4)];
		assignColors();
	}
	public void changeType(){
		Random r = new Random();
		this.type = types[r.nextInt(2)];
		assignColors();
	}
	private void assignColors(){
		switch (type){
			case ("Plateado"):
				lightColor = Color.LIGHT_GRAY;
				darkColor = Color.LIGHT_GRAY;
				break;
			case ("Rojo"):
				lightColor = Color.RED;
				darkColor = Color.RED;
				break;
			case ("Dorado"):
				lightColor = Color.ORANGE;
				darkColor = Color.ORANGE;
				break;
			case ("Negro"):
				lightColor = Color.BLACK;
				darkColor = Color.BLACK;
		}
	}

	public Color getBaseColor() {
		return baseColor;
	}
	
	public Color getLightColor() {
		return lightColor;
	}
	
	public Color getDarkColor() {
		return darkColor;
	}
	
	public int getDimension() {
		return dimension;
	}
	
	public int getSpawnColumn() {
		return spawnCol;
	}
	
	public int getSpawnRow() {
		return spawnRow;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public boolean isTile(int x, int y, int rotation) {
		return tiles[rotation][y * dimension + x];
	}
	
	public int getLeftInset(int rotation) {
		for(int x = 0; x < dimension; x++) {
			for(int y = 0; y < dimension; y++) {
				if(isTile(x, y, rotation)) {
					return x;
				}
			}
		}
		return -1;
	}
	
	public int getRightInset(int rotation) {
		for(int x = dimension - 1; x >= 0; x--) {
			for(int y = 0; y < dimension; y++) {
				if(isTile(x, y, rotation)) {
					return dimension - x;
				}
			}
		}
		return -1;
	}
	
	public int getTopInset(int rotation) {
		for(int y = 0; y < dimension; y++) {
			for(int x = 0; x < dimension; x++) {
				if(isTile(x, y, rotation)) {
					return y;
				}
			}
		}
		return -1;
	}
	
	public int getBottomInset(int rotation) {
		for(int y = dimension - 1; y >= 0; y--) {
			for(int x = 0; x < dimension; x++) {
				if(isTile(x, y, rotation)) {
					return dimension - y;
				}
			}
		}
		return -1;
	}
	public String getType(){
		return type;
	}
}
