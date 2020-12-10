package com.shiroecreative.todolist.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shiroecreative.todolist.data.model.Task;

import java.util.ArrayList;

public class TaskTableHandler implements TableHandler<Task> {
    DatabaseHelper dbHelper;

    public TaskTableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void create(Task task) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());

        long newRowId = db.insert(DatabaseContract.TaskEntry.TABLE_NAME, null, values);
    }

    @Override
    public Task readById(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DatabaseContract.TaskEntry._ID,
                DatabaseContract.TaskEntry.COLUMN_TITLE,
                DatabaseContract.TaskEntry.COLUMN_DESCRIPTION
        };

        // Filter results WHERE "id" = id
        String selection = DatabaseContract.TaskEntry._ID + " = ?";
        String[] selectionArgs = {id};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseContract.TaskEntry.COLUMN_TITLE + " DESC";

        Cursor cursor = db.query(
                DatabaseContract.TaskEntry.TABLE_NAME,   // The table to query
                projection,                             // The array of columns to return (pass null to get all)
                selection,                              // The columns for the WHERE clause
                selectionArgs,                          // The values for the WHERE clause
                null,                          // don't group the rows
                null,                           // don't filter by row groups
                sortOrder                               // The sort order
        );

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(
                cursor.getLong(
                        cursor.getColumnIndexOrThrow(DatabaseContract.TaskEntry._ID)) + "",
                cursor.getString(1),//title
                cursor.getString(2));//description

        return task;
    }

    @Override
    public ArrayList<Task> readAll() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseContract.TaskEntry.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getLong(
                                cursor.getColumnIndexOrThrow(DatabaseContract.TaskEntry._ID)) + "",
                        cursor.getString(1),//title
                        cursor.getString(2));//description

                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return contact list
        return taskList;
    }

    @Override
    public void update(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // set New value
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TaskEntry.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());

        // Which row to update, based on the title
        String selection = DatabaseContract.TaskEntry._ID + " LIKE ?";
        String[] selectionArgs = {task.getId()};

        int count = db.update(
                DatabaseContract.TaskEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public void delete(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = DatabaseContract.TaskEntry._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {task.getId()};
        // Issue SQL statement.
        int deletedRows = db.delete(DatabaseContract.TaskEntry.TABLE_NAME, selection, selectionArgs);
    }

}
