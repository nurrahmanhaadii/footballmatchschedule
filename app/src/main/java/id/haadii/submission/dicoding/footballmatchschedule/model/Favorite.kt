package id.haadii.submission.dicoding.footballmatchschedule.model

data class Favorite(
    val id: Long,
    val idLeague: String,
    val eventName: String,
    val eventTime: String,
    val eventDate: String,
    val eventSport: String,
    val teamHomeId: String,
    val teamHomeScore: String?,
    val teamHomeName: String,
    val teamHomeBadge: String?,
    val teamHomeLineUp: String?,
    val teamHomeGoal: String?,
    val teamHomeGoalKeeper: String?,
    val teamHomeRedCards: String?,
    val teamHomeYellowCards: String?,
    val teamAwayId: String,
    val teamAwayScore: String?,
    val teamAwayName: String,
    val teamAwayBadge: String?,
    val teamAwayLineUp: String?,
    val teamAwayGoal: String?,
    val teamAwayGoalKeeper: String?,
    val teamAwayRedCards: String?,
    val teamAwayYellowCards: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val EVENT_TITLE: String = "EVENT_TITLE"
        const val EVENT_SPORT: String = "EVENT_SPORT"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_HOME_BADGE: String = "TEAM_HOME_BADGE"
        const val TEAM_HOME_LINEUP_FORWARD: String = "TEAM_HOME_LINEUP_FORWARD"
        const val TEAM_HOME_GOAL: String = "TEAM_HOME_GOAL"
        const val TEAM_HOME_RED_CARDS: String = "TEAM_HOME_RED_CARDS"
        const val TEAM_HOME_YELLOW_CARDS: String = "TEAM_HOME_YELLOW_CARDS"
        const val TEAM_HOME_GOAL_KEEPER: String = "TEAM_HOME_GOAL_KEEPER"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val TEAM_AWAY_BADGE: String = "TEAM_AWAY_BADGE"
        const val TEAM_AWAY_LINEUP_FORWARD: String = "TEAM_AWAY_LINEUP_FORWARD"
        const val TEAM_AWAY_GOAL: String = "TEAM_AWAY_GOAL"
        const val TEAM_AWAY_RED_CARDS: String = "TEAM_AWAY_RED_CARDS"
        const val TEAM_AWAY_YELLOW_CARDS: String = "TEAM_AWAY_YELLOW_CARDS"
        const val TEAM_AWAY_GOAL_KEEPER: String = "TEAM_AWAY_GOAL_KEEPER"
    }
}