package id.haadii.submission.dicoding.footballmatchschedule.model

data class TeamFavorite(
    val id: Long,
    val idLeague: String,
    val strTeam: String,
    val intFormedYear: String,
    val strStadium: String,
    val strWebsite: String,
    val strTeamBadge: String,
    val strDescriptionEN: String,
    val strCountry: String
) {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val STR_TEAM: String = "STR_TEAM"
        const val INT_FORMED_YEAR: String = "INT_FORMED_YEAR"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_WEBSITE: String = "STR_WEBSITE"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_DESCRIPTION: String = "STR_DESCRIPTION"
        const val STR_COUNTRY: String = "STR_COUNTRY"
    }
}