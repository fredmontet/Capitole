package mobop.capitole.model;

import java.text.SimpleDateFormat;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Comment {

    private long mId;
    private SimpleDateFormat mTimestamp;
    private String mText;

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public SimpleDateFormat getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(SimpleDateFormat mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }
}
