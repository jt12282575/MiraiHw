package dada.com.miraihw.ui.git_detail_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pivincii.livedata_retrofit.network.ApiEmptyResponse
import com.pivincii.livedata_retrofit.network.ApiErrorResponse
import com.pivincii.livedata_retrofit.network.ApiSuccessResponse
import com.squareup.picasso.Picasso
import dada.com.miraihw.R
import dada.com.miraihw.config.Config
import dada.com.miraihw.const.Const.Companion.GIT_USER_LOGIN
import dada.com.miraihw.data.GitUserInfo
import dada.com.miraihw.util.NoUnderlineSpan
import kotlinx.android.synthetic.main.activity_git_user_detail.*
import kotlin.math.roundToInt

class GitUserDetailActivity : AppCompatActivity() {
    lateinit var loginStr: String
    lateinit var gitUserDetailViewModel: GitUserDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user_detail)
        initView()
        initViewModel()
        initIntentData()
    }

    private fun initView() {
        iv_cross.setOnClickListener {
            finish()
        }
    }

    private fun initViewModel() {
        gitUserDetailViewModel = ViewModelProviders.of(this).get(GitUserDetailViewModel::class.java)
        gitUserDetailViewModel.gitUserInfo.observe(this, Observer {
            pb_loading.visibility = View.GONE
            if (it is ApiErrorResponse) {
                val errorResponse = it as ApiErrorResponse
                Toast.makeText(this, errorResponse.errorMessage, Toast.LENGTH_LONG)
                    .show()
            } else if (it is ApiSuccessResponse) {
                val successResponse = it as ApiSuccessResponse
                updateUI(it.body)
                val name = it.body.name
            } else if (it is ApiEmptyResponse) {
                Toast.makeText(this, getString(R.string.load_empty_data), Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

    private fun updateUI(gitUserInfo: GitUserInfo) {
        v_line.visibility = View.VISIBLE

        gitUserInfo.avatarUrl?.let { imageUrl ->
            val imageSize: Int =
                resources.getDimension(R.dimen.detail_avatar_image_size).roundToInt()
            Picasso.get().load(imageUrl).placeholder(R.drawable.social).resize(imageSize, imageSize).into(ci_avatar)
        }

        tv_name.text = gitUserInfo.name
        tv_bio.text = gitUserInfo.bio
        tv_login.text = gitUserInfo.login
        gitUserInfo.siteAdmin?.let { siteAdmin ->
            chip_staff.visibility = if (Config.showStaffTag(siteAdmin)) View.GONE else View.VISIBLE
        }

        tv_location.text = gitUserInfo.location

        gitUserInfo.blog?.let { blogLink ->
            tv_blog_link.text = blogLink
            tv_blog_link.autoLinkMask = Linkify.WEB_URLS
            tv_blog_link.movementMethod = LinkMovementMethod.getInstance()
            if (tv_blog_link.text is Spannable) {
                val mNoUnderlineSpan = NoUnderlineSpan()
                val spannable: Spannable = tv_blog_link.text as Spannable
                spannable.setSpan(mNoUnderlineSpan, 0, spannable.length, Spanned.SPAN_MARK_MARK)
            }
        }


    }

    private fun initIntentData() {
        intent.getStringExtra(GIT_USER_LOGIN)?.let {
            loginStr = it
            gitUserDetailViewModel.login.value = loginStr
        }
    }

}
