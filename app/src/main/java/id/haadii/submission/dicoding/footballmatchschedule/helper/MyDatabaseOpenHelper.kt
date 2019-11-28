package id.haadii.submission.dicoding.footballmatchschedule.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.haadii.submission.dicoding.footballmatchschedule.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY,
            Favorite.ID_LEAGUE to TEXT,
            Favorite.EVENT_TITLE to TEXT,
            Favorite.EVENT_TIME to TEXT,
            Favorite.EVENT_DATE to TEXT,
            Favorite.EVENT_SPORT to TEXT,
            Favorite.TEAM_HOME_ID to TEXT,
            Favorite.TEAM_HOME_SCORE to TEXT,
            Favorite.TEAM_HOME_NAME to TEXT,
            Favorite.TEAM_HOME_BADGE to TEXT,
            Favorite.TEAM_HOME_LINEUP_FORWARD to TEXT,
            Favorite.TEAM_HOME_GOAL to TEXT,
            Favorite.TEAM_HOME_GOAL_KEEPER to TEXT,
            Favorite.TEAM_HOME_RED_CARDS to TEXT,
            Favorite.TEAM_HOME_YELLOW_CARDS to TEXT,
            Favorite.TEAM_AWAY_ID to TEXT,
            Favorite.TEAM_AWAY_SCORE to TEXT,
            Favorite.TEAM_AWAY_NAME to TEXT,
            Favorite.TEAM_AWAY_BADGE to TEXT,
            Favorite.TEAM_AWAY_LINEUP_FORWARD to TEXT,
            Favorite.TEAM_AWAY_GOAL to TEXT,
            Favorite.TEAM_AWAY_GOAL_KEEPER to TEXT,
            Favorite.TEAM_AWAY_RED_CARDS to TEXT,
            Favorite.TEAM_AWAY_YELLOW_CARDS to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)