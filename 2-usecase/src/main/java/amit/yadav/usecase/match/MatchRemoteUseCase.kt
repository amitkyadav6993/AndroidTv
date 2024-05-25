package amit.yadav.usecase.match

import amit.yadav.domain.entities.match.MatchResponse
import amit.yadav.usecase.base.UseCase
import amit.yadav.domain.Result
import kotlinx.coroutines.flow.Flow

interface MatchRemoteUseCase : UseCase<String, Flow<Result<MatchResponse>>>