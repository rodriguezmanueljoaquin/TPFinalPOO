package game.backend.level;

import game.backend.GameState;
import game.backend.cell.Cell;
import game.backend.cell.L2CandyGeneratorCell;
import game.backend.element.TimeCandy;
import game.backend.element.TimeBombCandy;

import java.util.*;

public class Level2 extends TimeLevel {

    private static final int MAX_SPECIAL_CANDY = 7;

    public Level2(){
        super(MAX_SPECIAL_CANDY);
    }

    public void addSpecial( TimeBombCandy candy ){
        ((Level2State)state()).add( candy );
    }

    public boolean noActive(){
        return ((Level2State)state()).activeSpecials.isEmpty();
    }

    @Override
    protected Cell getCandyGenerator(){
        return new L2CandyGeneratorCell( this );
    }

    @Override
    protected void executeInstructionsTryMove() {
        ((Level2State)state()).decTimers();
        super.executeInstructionsTryMove();
        ((Level2State)state()).updateCountdown();
    }

    @Override
    protected GameState newState(){
        return new Level2State( getQuota() );
    }

    private class Level2State extends SpecialLevelGameState{
        private TreeMap<Integer, TimeBombCandy> activeSpecials = new TreeMap<>();

        public Level2State( int candyGoal ){
            super( candyGoal );
        }

        @Override
        public void removeSpecial( TimeCandy candy ){
            decSpecialsLeft();
            removeTimeBomb( (TimeBombCandy) candy );
        }

        @Override
        protected void updateCountdown(){
            if(noActive())
                setCountdown(-1);
            else
                setCountdown( activeSpecials.firstEntry().getValue().getTimer() );
        }

        private void removeTimeBomb( TimeBombCandy candy ){
            activeSpecials.remove( candy.getId() );
            if( !noActive() ){
                updateCountdown();
            }
        }

        public void decTimers(){
            for( TimeBombCandy candy : activeSpecials.values() ){
                candy.decTimer();
            }
        }

        public void add( TimeBombCandy candy ){
            activeSpecials.put( candy.getId(), candy );
        }
    }
}