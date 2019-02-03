package FarmFrenzy.GameUI.MiddleMap.Animals;

import Animals.AnimalState;
import Animals.BaseAnimal;
import Utils.SpriteAnimation;

import java.util.HashMap;

public class AnimalView {
    private HashMap<AnimalState, AnimalStateView> states;

    public AnimalView(HashMap<AnimalState, AnimalStateView> states) {
        this.states = states;
    }

    public SpriteAnimation getView(BaseAnimal animal) {
        AnimalStateView animalStateView = states.get(animal.getState());
        if (animalStateView == null)
            return null;
        return animalStateView.getView();
    }

    @Override
    public String toString() {
        return states.toString();
    }
}
