package amit.yadav.network.dto

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MatchResponseDto(

    @Json(name = "status")
    val status: String?,

    @Json(name="data")
    val data: List<MatchItemDto>?

) : Parcelable
