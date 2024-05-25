package amit.yadav.data.mappers

import amit.yadav.domain.entities.match.MatchItem
import amit.yadav.domain.entities.match.MatchResponse
import amit.yadav.domain.entities.match.ScoreItem
import amit.yadav.domain.entities.match.TeamItem
import amit.yadav.network.dto.MatchResponseDto

object MatchResponseMapper {
    fun map(matchResponseDto: MatchResponseDto) = MatchResponse(
        status = matchResponseDto.status,
        data = matchResponseDto.data?.map { matchDto ->
            MatchItem(
                date = matchDto.date,
                venue = matchDto.venue,
                matchType = matchDto.matchType,
                name = matchDto.name,
                status = matchDto.status,
                teamInfo = matchDto.teamInfo?.map { teamDto ->
                    TeamItem(
                        name = teamDto.name,
                        shortname = teamDto.shortname,
                        img = teamDto.img
                    )
                },
                score = matchDto.score?.map { scoreDto ->
                    ScoreItem(
                        run = scoreDto.r,
                        wicket = scoreDto.w,
                        inning = scoreDto.inning,
                        over = scoreDto.o
                    )
                }
            )
        }
    )
}