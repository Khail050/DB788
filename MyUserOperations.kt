
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyUserOperations(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, MyUserDatabase, factory, DATABASE_VERSION) {
    companion object{
        // variable for database name
        private val  MyUserDatabase= "USERS6"
        // variable for database version
        private val DATABASE_VERSION = 1
        // variable for table name
        val TABLE_NAME = "users_table"
        //variable for id column
        val ID_COL = "id"
        // variable for name column
        val NAME_COl = "user"
        // variable for age column
        val AGE_COL = "pass"
    }
    //method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT" + ")")
        // method for executing our query
        db.execSQL(query)
    }
    override fun onUpgrade(db: SQLiteDatabase, v1: Int, v2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun login(user : String, pass : String ): Cursor {
        val db = this.readableDatabase
        val cols = arrayOf(ID_COL, NAME_COl, AGE_COL)  // 1 الحقول في الجدول
        val selections = "$NAME_COl=? AND $AGE_COL=?"  // 2  الشرط
        val args = arrayOf(user, pass)          // 3  بيانات المستخدم

        val cursor = db.query(TABLE_NAME, cols, selections, args, null, null, null)
        return  cursor
    }
    fun all(user: Int ): Cursor {
        val db = this.readableDatabase
        val cols = arrayOf(ID_COL, NAME_COl, AGE_COL)  // 1 الحقول في الجدول
        val selections = "$AGE_COL=?"  // 2  الشرط
        val args = arrayOf(user.toString())          // 3  بيانات المستخدم
        val cursor = db.query(TABLE_NAME, cols, selections,args, null, null, null)
        return  cursor

    }

    fun login(id: Int): Cursor {
        val db = this.readableDatabase
        val cols = arrayOf(ID_COL, NAME_COl, AGE_COL)  // 1 الحقول في الجدول
        val selections = "$ID_COL=?"  // 2  الشرط
        val args = arrayOf(id.toString())          // 3  بيانات المستخدم

        val cursor = db.query(TABLE_NAME, cols, selections, args, null, null, null)
        return  cursor
    }

    fun getAllByAge(pass: String): Cursor {
        val db = this.readableDatabase
        val cols = arrayOf(ID_COL, NAME_COl, AGE_COL)  // 1 الحقول في الجدول
        val selections = "$AGE_COL=?"  // 2  الشرط
        val args = arrayOf(pass)          // 3  بيانات المستخدم
        val cursor = db.query(TABLE_NAME, cols, selections, args, null, null, null)
        return  cursor
    }
    fun updateName(id: Int, user: String?, pass: String?): Int {
        var db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COl, user)
        values.put(AGE_COL, pass)
        return db.update(TABLE_NAME, values, ID_COL + " = " + id,null)
    }

    fun deleteName(id: Int): Int{
        var db = this.writableDatabase
        return db.delete(TABLE_NAME, ID_COL + "=" + id, null);
    }
    // This method is for adding data in our database
    fun addName(user : String, pass : String ): Long{
        // creating content values variable
        val values = ContentValues()
        // we are inserting our values
        values.put(NAME_COl, user)
        values.put(AGE_COL, pass)
        // creating writable variable of our database
        val db = this.writableDatabase
        // all values are inserted into database
        val response = db.insert(TABLE_NAME, null, values)
        // closing our database
        db.close()
        return response
    }
    // get all data from our database
    fun getAll(): Cursor? {
        // creating a readable variable of our database
        val db = this.readableDatabase
        // returns a cursor to read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
}