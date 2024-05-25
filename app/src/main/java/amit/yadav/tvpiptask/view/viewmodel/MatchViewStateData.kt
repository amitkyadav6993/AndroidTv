package amit.yadav.tvpiptask.view.viewmodel

import amit.yadav.domain.entities.match.MatchItem

sealed class MatchViewStateData {
    internal data object Initial : MatchViewStateData()
    internal data object Loading : MatchViewStateData()
    internal data object LoadFailed : MatchViewStateData()
    internal data class Failure(val throwable: Throwable) : MatchViewStateData()
    internal data class Success(val data: MatchItem?) : MatchViewStateData()
}