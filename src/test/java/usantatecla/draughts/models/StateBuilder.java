package usantatecla.draughts.models;

public class StateBuilder {

    private State state;

    public StateBuilder() {
        this.state = new State();
    }

    public StateBuilder withStateValue(StateValue value) {
        while(this.state.getValueState() != value)
            this.state.next();
        return this;
    }

    public State build() {
        return state;
    }

}
