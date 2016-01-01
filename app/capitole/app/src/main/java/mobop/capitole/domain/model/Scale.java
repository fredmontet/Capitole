package mobop.capitole.domain.model;

import io.realm.RealmObject;

/**
 *  Scale model for the Capitole database
 */
public class Scale extends RealmObject{

    private int min;
    private int max;
    private int step;

    // Methods
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
