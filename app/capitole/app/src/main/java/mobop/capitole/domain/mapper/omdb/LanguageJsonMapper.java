package mobop.capitole.domain.mapper.omdb;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import mobop.capitole.domain.model.Language;

public class LanguageJsonMapper {

    /**
     * Mapper to get a list of Language from a
     * String of languages like this one: "French, English,..."
     * @param languagesStr
     * @return RealmList<Language>
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
