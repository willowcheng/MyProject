package me.willowcheng.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willowcheng on 4/18/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "localDatabase";
    private static final String TABLE_PROJECT = "project";

    private static final String PROJECT_KEY_ID = "id";
    private static final String PROJECT_KEY_NAME = "name";
    private static final String PROJECT_KEY_COURSE_NUMBER = "course_number";
    private static final String PROJECT_KEY_INSTRUCTOR_NAME = "instructor_name";
    private static final String PROJECT_KEY_PROJECT_NUMBER = "project_number";
    private static final String PROJECT_KEY_DESCRIPTION = "description";
    private static final String PROJECT_KEY_DUE = "due";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PROJECT_TABLE = "CREATE TABLE " + TABLE_PROJECT + "(" + PROJECT_KEY_ID + " INTEGER PRIMARY KEY," + PROJECT_KEY_NAME + " TEXT,"
                + PROJECT_KEY_COURSE_NUMBER + " TEXT," + PROJECT_KEY_INSTRUCTOR_NAME + " TEXT," + PROJECT_KEY_PROJECT_NUMBER + " TEXT,"
                + PROJECT_KEY_DESCRIPTION + " TEXT," + PROJECT_KEY_DUE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PROJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_PROJECT);
        onCreate(sqLiteDatabase);
    }

    public void addProject(ProjectItem projectItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_KEY_NAME, projectItem.getName());
        contentValues.put(PROJECT_KEY_COURSE_NUMBER, projectItem.getCourseNumber());
        contentValues.put(PROJECT_KEY_INSTRUCTOR_NAME, projectItem.getInstructor());
        contentValues.put(PROJECT_KEY_PROJECT_NUMBER, projectItem.getProjectNumber());
        contentValues.put(PROJECT_KEY_DESCRIPTION, projectItem.getDescription());
        contentValues.put(PROJECT_KEY_DUE, projectItem.getDue());

        sqLiteDatabase.insert(TABLE_PROJECT, null, contentValues);
        sqLiteDatabase.close();
    }

    public void deleteProject(ProjectItem projectItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_PROJECT, PROJECT_KEY_ID + " = ?",
                new String[]{String.valueOf(projectItem.getId())});
        sqLiteDatabase.close();
    }

    public int updateProject(ProjectItem projectItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_KEY_NAME, projectItem.getName());
        contentValues.put(PROJECT_KEY_COURSE_NUMBER, projectItem.getCourseNumber());
        contentValues.put(PROJECT_KEY_INSTRUCTOR_NAME, projectItem.getInstructor());
        contentValues.put(PROJECT_KEY_PROJECT_NUMBER, projectItem.getProjectNumber());
        contentValues.put(PROJECT_KEY_DESCRIPTION, projectItem.getDescription());
        contentValues.put(PROJECT_KEY_DUE, projectItem.getDue());

        // updating row
        return sqLiteDatabase.update(TABLE_PROJECT, contentValues, PROJECT_KEY_ID + " = ?",
                new String[] { String.valueOf(projectItem.getId()) });
    }

    public ProjectItem getProject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROJECT, new String[] { PROJECT_KEY_ID,
                        PROJECT_KEY_NAME, PROJECT_KEY_COURSE_NUMBER, PROJECT_KEY_INSTRUCTOR_NAME,
                        PROJECT_KEY_PROJECT_NUMBER, PROJECT_KEY_DESCRIPTION, PROJECT_KEY_DUE }, PROJECT_KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ProjectItem projectItem = new ProjectItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return contact
        return projectItem;
    }

    public List<ProjectItem> getAllProjectItems() {
        List<ProjectItem> projectItemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProjectItem projectItem = new ProjectItem(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                projectItemList.add(projectItem);
            } while (cursor.moveToNext());
        }
        return projectItemList;
    }
}
