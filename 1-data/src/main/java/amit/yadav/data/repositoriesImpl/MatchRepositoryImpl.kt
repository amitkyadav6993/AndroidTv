package amit.yadav.data.repositoriesImpl

import amit.yadav.data.mappers.MatchResponseMapper
import amit.yadav.domain.repositories.MatchesRepository
import amit.yadav.network.api.MatchApiServices
import amit.yadav.domain.Result
import amit.yadav.domain.entities.match.MatchResponse
import amit.yadav.domain.map
import amit.yadav.network.dto.MatchResponseDto

internal class MatchRepositoryImpl(
    private val matchApiServices: MatchApiServices,
    private val matchApiKey: String
) : MatchesRepository {

    override suspend fun getLatestMatch(): Result<MatchResponse> {
        return Result.runCatching { matchApiServices.getCurrentMatch(apikey = matchApiKey) }.map {
            MatchResponseMapper.map(it)
        }
    }
}
