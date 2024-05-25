package amit.yadav.network.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MatchItemDto(

    @Json(name="date")
    val date: String?,

    @Json(name="venue")
    val venue: String?,

    @Json(name="score")
    val score: List<ScoreItemDto>?,

    @Json(name="teamInfo")
    val teamInfo: List<TeamItemDto>?,

    @Json(name="matchType")
    val matchType: String?,

    @Json(name="name")
    val name: String?,

    @Json(name="status")
    val status: String?,

) : Parcelable