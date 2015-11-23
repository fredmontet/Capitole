package mobop.capitole.model;

import java.util.ArrayList;

/**
 * Created by fredmontet on 22/11/15.
 */
public class Preference {

    private long mId;
    private ArrayList<String> mGenre;
    private ArrayList<String> mPeople;
    private ArrayList<String> mFilm;
    private ArrayList<String> mLanguage;
    private ArrayList<String> mCountry;

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public ArrayList<String> getGenre() {
        return mGenre;
    }

    public void setGenre(ArrayList<String> mGenre) {
        this.mGenre = mGenre;
    }

    public ArrayList<String> getPeople() {
        return mPeople;
    }

    public void setPeople(ArrayList<String> mPeople) {
        this.mPeople = mPeople;
    }

    public ArrayList<String> getFilm() {
        return mFilm;
    }

    public void setFilm(ArrayList<String> mFilm) {
        this.mFilm = mFilm;
    }

    public ArrayList<String> getLanguage() {
        return mLanguage;
    }

    public void setLanguage(ArrayList<String> mLanguage) {
        this.mLanguage = mLanguage;
    }

    public ArrayList<String> getCountry() {
        return mCountry;
    }

    public void setCountry(ArrayList<String> mCountry) {
        this.mCountry = mCountry;
    }
}
