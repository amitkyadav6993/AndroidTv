package amit.yadav.tvpiptask.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : FragmentActivity(), IBaseUi {

    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.let { setContentView(it) }
        initUI()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).also { it.show() }
    }
}

// Extension for view-binding START
fun <T : ViewBinding> FragmentActivity.viewBinding(bind: (View) -> T): Lazy<T> = object : Lazy<T> {
    private var binding: T? = null

    override fun isInitialized(): Boolean = binding != null

    override val value: T
        get() = binding ?: bind(getContentView()).also {
            binding = it
        }

    private fun FragmentActivity.getContentView(): View {
        return checkNotNull(findViewById<ViewGroup>(android.R.id.content).getChildAt(0)) {
            "Call setContentView or Use Activity's secondary constructor passing layout res id."
        }
    }
}