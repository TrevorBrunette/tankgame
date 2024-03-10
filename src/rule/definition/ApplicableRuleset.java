package rule.definition;

import state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicableRuleset {

    private final Map<Class<?>, List<IApplicableRule<?>>> rules;

    public ApplicableRuleset() {
        rules = new HashMap<>();
    }

    public <T> void put(Class<T> c, IApplicableRule<T> rule) {
        List<IApplicableRule<?>> list = rules.get(c);
        if (list == null) {
            list = new ArrayList<>();
            list.add(rule);
            rules.put(c, list);
        } else {
            list.add(rule);
        }
    }

    public <T> List<IApplicableRule<T>> get(Class<T> c) {
        if (rules.containsKey(c)) {
            try {
                return (List<IApplicableRule<T>>) ((Object) rules.get(c));
            } catch (Exception ignored) {
            }
        }
        return new ArrayList<>(0);
    }

    public <T> void applyRules(State state, T subject){
        Class<?> c = subject.getClass();
        for (IApplicableRule<T> rule : (List<IApplicableRule<T>>) (Object) get(c)) {
            rule.apply(state, subject);
        }
    }

    public <T> List<IConditionalRule<T>> applicableConditionalRules(Class<T> c, State state, T subject) {
        List<IConditionalRule<T>> output = new ArrayList<>();
        for (IApplicableRule<T> rule : get(c)) {
            if (rule instanceof IConditionalRule<T> conditional && conditional.canApply(state, subject)) {
                output.add(conditional);
            }
        }
        return output;
    }
}