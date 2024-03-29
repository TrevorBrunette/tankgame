package pro.trevor.tankgame.state.board.floor;

import org.json.JSONObject;
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

    @Override
    public JSONObject toJson() {
        JSONObject output = new JSONObject();
        output.put("type", "empty");
        return output;
    }
}
