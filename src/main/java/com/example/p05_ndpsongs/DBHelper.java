package com.example.p05_ndpsongs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

    public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "revisionnote.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_SONG = "song";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_SINGERS = "singers";
        private static final String COLUMN_YEAR = "year";
        private static final String COLUMN_STARS = "stars";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // CREATE TABLE Note
            // (id INTEGER PRIMARY KEY AUTOINCREMENT, note_content TEXT, rating
            // INTEGER );
            String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_SINGERS + " TEXT, "
                    + COLUMN_YEAR + " INTEGER, "
                    + COLUMN_STARS + " INTEGER )";
            db.execSQL(createNoteTableSql);
            Log.i("info", "created tables");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
            onCreate(db);
        }

        public void insertNote(String title, String singers, int year, int stars) {
            // Get an instance of the database for writing
            SQLiteDatabase db = this.getWritableDatabase();
            // We use ContentValues object to store the values for the db operation
            ContentValues values = new ContentValues();
            // Store the column name as key and the title as value
            values.put(COLUMN_TITLE, title);
            // Store the column name as key and the note content as value
            values.put(COLUMN_SINGERS, singers);
            // Store the column name as key and the note content as value
            values.put(COLUMN_YEAR, year);
            // Store the column name as key and the rating as value
            values.put(COLUMN_STARS, stars);
            // Insert the row into the TABLE_NOTE
            db.insert(TABLE_SONG, null, values);
            // Close the database connection
            db.close();
        }

//        public boolean isExistingNote(String content) {
//            // Select all the notes' content
//            String selectQuery = "SELECT " + COLUMN_NOTE_CONTENT + " FROM "
//                    + TABLE_NOTE + " WHERE " + COLUMN_NOTE_CONTENT + " = '"
//                    + content + "'";
//            // Get the instance of database to read
//            SQLiteDatabase db = this.getReadableDatabase();
//            // Run the SQL query and get back the Cursor object
//            Cursor cursor = db.rawQuery(selectQuery, null);
//            // moveToFirst() moves to first row
//            if (cursor.moveToFirst()) {
//                return true;
//            }
//            // Close connection
//            cursor.close();
//            db.close();
//
//            return false;
//        }

        public ArrayList<Song> getAllSongs() {
            ArrayList<Song> notes = new ArrayList<Song>();
            // "SELECT id, note_content, stars FROM note"
            String selectQuery = "SELECT " + COLUMN_ID + ","
                    + COLUMN_TITLE + ","
                    + COLUMN_SINGERS + ","
                    + COLUMN_YEAR + ","
                    + COLUMN_STARS
                    + " FROM " + TABLE_SONG;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // Loop through all rows and add to ArrayList
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String singers = cursor.getString(2);
                    int year = cursor.getInt(3);
                    int stars = cursor.getInt(4);

                    Song note = new Song(id, title, singers, year, stars);
                    notes.add(note);
                } while (cursor.moveToNext());
            }
            // Close connection
            cursor.close();
            db.close();
            return notes;
        }

//        public ArrayList<String> getNoteContent() {
//            //TODO return records in Strings
//
//            // Create an ArrayList that holds String objects
//            ArrayList<String> notes = new ArrayList<String>();
//            // Select all the notes' content
//            String selectQuery = "SELECT " + COLUMN_NOTE_CONTENT + " FROM "
//                    + TABLE_NOTE;
//
//            // Get the instance of database to read
//            SQLiteDatabase db = this.getReadableDatabase();
//            // Run the SQL query and get back the Cursor object
//            Cursor cursor = db.rawQuery(selectQuery, null);
//            // moveToFirst() moves to first row
//            if (cursor.moveToFirst()) {
//                // Loop while moveToNext() points to next row and returns true;
//                // moveToNext() returns false when no more next row to move to
//                do {
//                    // Add the note content to the ArrayList object
//                    notes.add(cursor.getString(0));
//                } while (cursor.moveToNext());
//            }
//            // Close connection
//            cursor.close();
//            db.close();
//
//            return notes;
//        }

}
