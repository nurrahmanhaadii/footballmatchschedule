package id.haadii.submission.dicoding.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val idTeam: String = "",
    val idLeague: String = "",
    val strTeam: String = "",
    val intFormedYear: String = "",
    val strStadium: String = "",
    val strWebsite: String = "",
    val strTeamBadge: String = "",
    val strSport: String = "",
    val strDescriptionEN: String = "",
    val strCountry: String = ""
) : Parcelable
