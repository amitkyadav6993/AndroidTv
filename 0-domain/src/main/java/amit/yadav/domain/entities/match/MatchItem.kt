package amit.yadav.domain.entities.match

data class MatchItem(
    val date: String? = null,
    val venue: String? = null,
    val teamInfo: List<TeamItem>? = null,
    val matchType: String? = null,
    val score: List<ScoreItem>? = null,
    val name: String? = null,
    val status: String? = null,
)
