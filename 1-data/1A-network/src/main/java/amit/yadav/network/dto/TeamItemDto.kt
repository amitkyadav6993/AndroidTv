package amit.yadav.network.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TeamItemDto(

    @Json(name="name")
    val name: String?,

    @Json(name="shortname")
    val shortname: String?,

    @Json(name="img")
    val img: String?,

) : Parcelable