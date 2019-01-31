package Animals.AnimalView;

import Utils.AnimationProperties;

import java.util.HashMap;

class StateHandler {
    private String state;
    private HashMap<String, AnimationProperties> animations = new HashMap<>();
    private String path;
    private AnimationProperties current = null;

    StateHandler(String path) {
        this.path = path;
    }

    void setState(String state) {
        if (state.equals(this.state))
            return;
        this.state = state;
        AnimationProperties properties = animations.getOrDefault(state, null);
        if (properties == null) {
            properties = AnimationProperties.readFromFile(path + state);
            animations.put(state, properties);
        }
        current = properties;
    }

    AnimationProperties getCurrent() {
        return current;
    }
}
