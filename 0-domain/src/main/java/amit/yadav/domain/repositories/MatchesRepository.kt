package amit.yadav.domain.repositories

import amit.yadav.domain.Result
import amit.yadav.domain.entities.match.MatchResponse

interface MatchesRepository {
    suspend fun getLatestMatch(): Result<MatchResponse>
}