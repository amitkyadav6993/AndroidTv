package amit.yadav.network.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ScoreItemDto(

    @Json(name="r")
    val r: Int?,

    @Json(name="w")
    val w: Int?,

    @Json(name="inning")
    val inning: String?,

    @Json(name="o")
    val o: Double?

) : Parcelable
