package edu.wm.cs.cs301.memorygame;

public class LeaderboardInfo {
    private String name;
    private int turns;
    private String difficulty;

    public LeaderboardInfo(String name, int turns, String difficulty) {
        this.name = name;
        this.turns = turns;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getTurns() {
        return turns;
    }
    public String getDifficulty() {
        return difficulty;
    }
}
