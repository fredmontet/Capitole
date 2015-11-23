package mobop.capitole.model;

import android.provider.BaseColumns;

/**
 * Created by fredmontet on 23/11/15.
 */
public class GenreContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public GenreContract() {}

    /**
     * Contains the name of the table to create that contains the row counters.
     */
    public static final String TABLE_NAME = "genre";

    /**
     * Contains the SQL query to use to create the table containing the row counters.
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE ..."; // TODO: 23/11/15 make create sql

    /**
     * This class represents the rows for an entry in the row_counter table. The
     * primary key is the _id column from the BaseColumn class.
     */
    public static abstract class GenreEntry implements BaseColumns {

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_GENRE = "genre";
    }

}
