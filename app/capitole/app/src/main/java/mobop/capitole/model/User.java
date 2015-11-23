package mobop.capitole.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by fredmontet on 22/11/15.
 */
public class User {

    private long mId;
    private UUID mUuid;
    private String mFirstname;
    private String mLastname;
    private Date mBirthdate;
    private String mCity;
    private String mGender;

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public void setUuid(UUID mUuid) {
        this.mUuid = mUuid;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String mFirstname) {
        this.mFirstname = mFirstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String mLastname) {
        this.mLastname = mLastname;
    }

    public Date getBirthdate() {
        return mBirthdate;
    }

    public void setBirthdate(Date mBirthdate) {
        this.mBirthdate = mBirthdate;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }
}
