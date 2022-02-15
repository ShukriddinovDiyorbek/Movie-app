package uz.pdp.movieapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.pdp.movieapp.model.Movie
import uz.pdp.movieapp.utils.Constant

class MyFatabaseService(context: Context) :
    SQLiteOpenHelper(context, Constant.DB_NAME, null, Constant.DB_VERSION), DBService {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table ${Constant.TABLE_NAME} (${Constant.ID} integer not null primary key autoincrement unique, ${Constant.MOVIE_TITLE} text not null, ${Constant.AUTHORS} text not null, ${Constant.COMMENT} text not null,${Constant.DATE} text not null);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists ${Constant.TABLE_NAME}")
        onCreate(db)
    }

    override fun addContact(movie: Movie) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.MOVIE_TITLE, movie.title)
        contentValues.put(Constant.AUTHORS, movie.authors)
        contentValues.put(Constant.COMMENT, movie.comment)
        contentValues.put(Constant.DATE, movie.date)
        database.insert(Constant.TABLE_NAME, null,contentValues)

    }

    override fun getAllContacts(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        val query = "select * from ${Constant.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val author = cursor.getString(2)
                val comment = cursor.getString(3)
                val date = cursor.getString(4)

                val movie = Movie(id,title,author,comment,date)
                movies.add(movie)
            }while (cursor.moveToNext())
        }
        return movies
    }

    override fun updateContact(movie: Movie):Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.MOVIE_TITLE, movie.title)
        contentValues.put(Constant.AUTHORS, movie.authors)
        contentValues.put(Constant.COMMENT, movie.comment)
        contentValues.put(Constant.DATE, movie.date)
        return database.update(Constant.TABLE_NAME,contentValues,"${Constant.ID} = ?", arrayOf("${movie.id}"))
    }

    override fun deleteContact(movie: Movie) {
        val database = this.writableDatabase
        database.delete(Constant.TABLE_NAME,"${Constant.ID} =?", arrayOf("${movie.id}"))
        //val query = "delete from ${Constant.TABLE_NAME} where id=${movie.id}"
        //database.execSQL(query)
        database.close()
    }
}