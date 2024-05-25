package amit.yadav.network.api

import amit.yadav.network.dto.MatchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApiServices {

    @GET("currentMatches")
    suspend fun getCurrentMatch(
        @Query("apikey") apikey: String,
    ): MatchResponseDto

}