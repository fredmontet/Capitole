package mobop.capitole.model;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Scale {

    private long mId;
    private int mMin;
    private int mMax;
    private int mStep;

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public int getMin() {
        return mMin;
    }

    public void setMin(int mMin) {
        this.mMin = mMin;
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int mMax) {
        this.mMax = mMax;
    }

    public int getStep() {
        return mStep;
    }

    public void setStep(int mStep) {
        this.mStep = mStep;
    }
}
