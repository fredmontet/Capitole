package mobop.capitole.domain.mapper.tmdb;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Language;

public class LanguageJsonMapper {

    /**
     * Mapper to transform a String of Languages in a
     * RealmList of Languages
     * @param languagesStr in the form : "language1, language2,..., languageN"
     * @return languageList the RealmList<Language>
     */
    public RealmList<Language> transform(String languagesStr){
        RealmList<Language> languageList = new RealmList<>();
        List<String> languageStrList = Arrays.asList(languagesStr.split(", "));
        Language language;
        for(String languageStr : languageStrList){
            language = new Language();
            language.setLanguage(languageStr);
            languageList.add(language);
        }
        return languageList;
    }

}
