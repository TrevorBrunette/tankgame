package pro.trevor.tankgame.rule.definition;

import pro.trevor.tankgame.rule.type.IMetaElement;
import pro.trevor.tankgame.state.State;

import java.util.function.BiConsumer;

public class MetaTickActionRule<T extends IMetaElement> implements IApplicableRule<T> {

    private final BiConsumer<T, State> consumer;

    public MetaTickActionRule(BiConsumer<T, State> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void apply(State state, T subject) {
        consumer.accept(subject, state);
    }

}
