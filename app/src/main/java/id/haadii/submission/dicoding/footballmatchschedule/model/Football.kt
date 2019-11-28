package id.haadii.submission.dicoding.footballmatchschedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Football(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int
) : Parcelable