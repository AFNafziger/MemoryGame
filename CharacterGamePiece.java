package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
	}

	public Character getSymbol() {
		return symbol;
	}
	
	public void setVisible(boolean v) {
		visible = v;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(GamePiece other) { //Check if the two symbols are equal (google this stuff)
		if (other instanceof CharacterGamePiece) {
            CharacterGamePiece otherPiece = (CharacterGamePiece) other;
            return this.symbol.equals(otherPiece.symbol);
        }
        return false;
    }
	
}
