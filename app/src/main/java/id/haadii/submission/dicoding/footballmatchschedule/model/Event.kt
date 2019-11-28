package id.haadii.submission.dicoding.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val idEvent: String,
    val strEvent: String,
    val strSport: String,
    val idLeague: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val dateEvent: String,
    val strTime: String,
    val idHomeTeam: String,
    val idAwayTeam: String,
    val intHomeScore: String? = null,
    val intAwayScore: String? = null,
    val strHomeLineupForward: String? = null,
    val strAwayLineupForward: String? = null,
    val strHomeGoalDetails: String? = null,
    val strAwayGoalDetails: String? = null,
    val strHomeRedCards: String? = null,
    val strAwayRedCards: String? = null,
    val strHomeYellowCards: String? = null,
    val strAwayYellowCards: String? = null,
    val strHomeLineupGoalkeeper: String? = null,
    val strAwayLineupGoalkeeper: String? = null,
    var strBadgeHomeTeam: String? = null,
    var strBadgeAwayTeam: String? = null
) : Parcelable