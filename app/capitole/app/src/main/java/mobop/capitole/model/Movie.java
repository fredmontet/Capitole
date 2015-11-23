package mobop.capitole.model;

import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Movie {

    private long mId;
    private String mTitle;
    private String mTagline;
    private String mSynopsis;
    private Duration mLength;
    private String mTrailerlink;
    private String mWebsite;
    private Date mReleaseDate;
    private int mBudget;

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String mTagline) {
        this.mTagline = mTagline;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

    public Duration getLength() {
        return mLength;
    }

    public void setLength(Duration mLength) {
        this.mLength = mLength;
    }

    public String getTrailerlink() {
        return mTrailerlink;
    }

    public void setTrailerlink(String mTrailerlink) {
        this.mTrailerlink = mTrailerlink;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(Date mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public int getBudget() {
        return mBudget;
    }

    public void setBudget(int mBudget) {
        this.mBudget = mBudget;
    }
}
