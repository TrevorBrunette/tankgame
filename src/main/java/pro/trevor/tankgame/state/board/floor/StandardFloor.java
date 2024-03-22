package pro.trevor.tankgame.state.board.floor;

import pro.trevor.tankgame.state.board.Position;
import pro.trevor.tankgame.state.board.unit.IWalkable;

public class StandardFloor extends ConditionallyWalkableFloor {

    public StandardFloor(Position position) {
        super(position, (f, b) -> b.getUnit(f.getPosition()).orElse(null) instanceof IWalkable);
    }

    @Override
    public char toBoardCharacter() {
        return '_';
    }
}