package mobop.capitole.domain.model;

import io.realm.RealmObject;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Rating extends RealmObject{

    private int rate;

    // Many to one
    private Scale scale;

    // Methods
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }
}
