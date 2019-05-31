package fr.nansty.yuesport

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import fr.nansty.yuesport.controllers.Game


private const val DATABASE_NAME = "esport.db"
private const val DATABASE_VERSION = 1

private const val GAME_TABLE_NAME = "game"
private const val GAME_KEY_ID = "id"
private const val GAME_KEY_NAME = "name"

private const val GAME_TABLE_CREATE = """
CREATE TABLE $GAME_TABLE_NAME (
    $GAME_KEY_ID INTEGER PRIMARY KEY,
    $GAME_KEY_NAME TEXT)
"""

private const val GAME_QUERY_SELECT_ALL = "SELECT * FROM $GAME_TABLE_NAME"

class Database(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    private val TAG = Database::class.java.simpleName

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(GAME_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun createGame(game: Game) : Boolean {
        val values = ContentValues()
        values.put(GAME_KEY_NAME, game.name)
        Log.d(TAG, "create game: $values")
        val id = writableDatabase.insert(GAME_TABLE_NAME, null, values)
        game.id = id
        return id> 0
    }

    fun getAllGames() : MutableList<Game> {
        val games = mutableListOf<Game>()

        readableDatabase.rawQuery(GAME_QUERY_SELECT_ALL, null).use { cursor ->
            while (cursor.moveToNext()) {
                val game = Game(
                    cursor.getLong(cursor.getColumnIndex(GAME_KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(GAME_KEY_NAME))
                )
                games.add(game)
            }
        }

        return games
    }

    fun deleteGame(game: Game): Boolean {
        Log.d(TAG, "Delete game $game")

        val deleteCount = writableDatabase.delete(GAME_TABLE_NAME, "$GAME_KEY_ID = ?", arrayOf("${game.id}"))

        return deleteCount == 1
    }

}