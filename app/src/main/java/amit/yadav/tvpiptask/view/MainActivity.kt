package amit.yadav.tvpiptask.view

import amit.yadav.domain.entities.match.MatchItem
import amit.yadav.tvpiptask.R
import amit.yadav.tvpiptask.base.BaseActivity
import amit.yadav.tvpiptask.base.viewBinding
import amit.yadav.tvpiptask.databinding.ActivityMainBinding
import amit.yadav.tvpiptask.view.util.ViewStatus
import amit.yadav.tvpiptask.view.util.ViewStatus.*
import amit.yadav.tvpiptask.view.viewmodel.MatchViewStateData
import amit.yadav.tvpiptask.view.viewmodel.MainViewModel
import android.annotation.SuppressLint
import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.util.Log
import android.util.Rational
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val binding by viewBinding { ActivityMainBinding.bind(it) }
    private val viewModel by viewModel<MainViewModel>()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initUI() {
        initData()
        observeViewModelData()
        onBackPressedDispatcher.addCallback(this, onBackPressCallBack)
    }

    private fun initData() {
        viewModel.fetchMatchScore(true)
    }

    private fun observeViewModelData(){
        viewModel.matchLiveData.observe(this){ newState ->
            when (newState) {
                is MatchViewStateData.Loading -> showUiScreenTypes(status = ON_SHOW_PROGRESS)
                is MatchViewStateData.Success -> showUiScreenTypes(status = ON_SHOW_DATA, data = newState.data)
                is MatchViewStateData.LoadFailed -> showUiScreenTypes(status = ON_SHOW_ERROR)
                is MatchViewStateData.Failure -> showUiScreenTypes(status = NO_INTERNET)
                else -> Unit
            }
        }
    }

    private fun showUiScreenTypes(status: ViewStatus, data: MatchItem? = null){
        with(binding){
            when(status){
                ON_SHOW_PROGRESS -> {
                    mainLayout.isVisible = false
                    errorLayout.isVisible = false
                    tvNoData.isVisible = false
                    prgBar.isVisible = true
                }
                ON_SHOW_DATA -> {
                    errorLayout.isVisible = false
                    tvNoData.isVisible = false
                    prgBar.isVisible = false
                    mainLayout.isVisible = true
                    showMatchInfo(data)
                }
                ON_SHOW_ERROR -> {
                    mainLayout.isVisible = false
                    errorLayout.isVisible = false
                    prgBar.isVisible = false
                    tvNoData.isVisible = true
                }
                NO_INTERNET -> {
                    mainLayout.isVisible = false
                    prgBar.isVisible = false
                    tvNoData.isVisible = false
                    errorLayout.isVisible = true
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMatchInfo(matchItem: MatchItem?) {
        with(binding){
            matchItem?.let { data ->
                data.teamInfo?.firstOrNull()?.let { firstTeam ->
                    ivTeamOne.loadGlideImage(firstTeam.img)
                    tvTeamOne.text = firstTeam.shortname
                }

                data.teamInfo?.lastOrNull()?.let { secondTeam ->
                    ivTeamTwo.loadGlideImage(secondTeam.img)
                    tvTeamTwo.text = secondTeam.shortname
                }

                tvType.text = data.matchType
                tvStatus.text = data.status

                data.score?.firstOrNull()?.let { scoreOne ->
                    tvScoreOne.text = "${scoreOne.run}-${scoreOne.wicket} (${scoreOne.over})"
                }

                data.score?.lastOrNull()?.let { scoreTwo ->
                    tvScoreTwo.text = "${scoreTwo.run}-${scoreTwo.wicket} (${scoreTwo.over})"
                }

            }
        }
    }


    private fun enterPipMode() {
        val aspectRatio = Rational(16, 9) // Set the desired aspect ratio
        val pipParams = PictureInPictureParams.Builder()
            .setAspectRatio(aspectRatio)
            .build()
        if (enterPictureInPictureMode(pipParams)) {
            Log.d("PIP_Log", "Successfully entered PiP mode")
        } else {
            Log.d("PIP_Log", "Failed to enter PiP mode")
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        Log.d("PIP_Log", "isInPictureInPictureMode="+isInPictureInPictureMode)
        if (isInPictureInPictureMode) {
            // Code to handle entering PiP mode
        } else {
            // Code to handle exiting PiP mode
        }
    }

    private val onBackPressCallBack = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            enterPipMode()
        }
    }

}

private fun ImageView.loadGlideImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl.orEmpty())
        .into(this)
}
