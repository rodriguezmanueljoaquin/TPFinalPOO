package game.backend.level;

import game.backend.GameState;

public class Level1 extends Level {
	
	private static int REQUIRED_SCORE = 5000;
	private static int MAX_MOVES = 20;

	public Level1(){
		if (REQUIRED_SCORE < 0 || MAX_MOVES < 0){
			throw new IllegalStateException("REGLAS DE JUEGO INVÁLIDAS");
		}
	}

	public int getRequiredScore() {
		return REQUIRED_SCORE;
	}

	public int getMaxMoves() {
		return MAX_MOVES;
	}

	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}


	private class Level1State extends GameState {
		private long requiredScore;
		private long maxMoves;

		
		public Level1State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() >= requiredScore;
		}
	}

}
