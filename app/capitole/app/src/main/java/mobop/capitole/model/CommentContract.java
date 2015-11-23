package mobop.capitole.model;

import android.provider.BaseColumns;

/**
 * Created by fredmontet on 23/11/15.
 */
public class CommentContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public CommentContract() {}

    public static final String TABLE_NAME = "comment";

    /**
     * Contains the SQL query to use to create the table.
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE ...";

    /**
     * This class represents the rows for an entry in the table.
     * The primary key is the _id column from the BaseColumn class.
     */
    public static abstract class CommentEntry implements BaseColumns {

        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TEXT = "text";

        // Foreign key(s)
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
    }

}
