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
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import dada.com.miraihw.R
import dada.com.miraihw.const.Const.Companion.GIT_USER_LOGIN
import dada.com.miraihw.data.GitUserInfo
import dada.com.miraihw.util.NoUnderlineSpan
import kotlinx.android.synthetic.main.activity_git_user_detail.*
import kotlin.math.roundToInt

class GitUserDetailActivity : AppCompatActivity() {
    lateinit var loginStr: String
    lateinit var gitUserDetailViewModel: GitUserDetailViewModel
    val mockUserJson: String =
        "{\"login\":\"defunkt\",\"id\":2,\"node_id\":\"MDQ6VXNlcjI=\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/2?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/defunkt\",\"html_url\":\"https://github.com/defunkt\",\"followers_url\":\"https://api.github.com/users/defunkt/followers\",\"following_url\":\"https://api.github.com/users/defunkt/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/defunkt/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/defunkt/subscriptions\",\"organizations_url\":\"https://api.github.com/users/defunkt/orgs\",\"repos_url\":\"https://api.github.com/users/defunkt/repos\",\"events_url\":\"https://api.github.com/users/defunkt/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/defunkt/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Chris Wanstrath\",\"company\":null,\"blog\":\"http://chriswanstrath.com/\",\"location\":null,\"email\":null,\"hireable\":null,\"bio\":\"\uD83C\uDF54\",\"public_repos\":107,\"public_gists\":273,\"followers\":20986,\"following\":210,\"created_at\":\"2007-10-20T05:24:19Z\",\"updated_at\":\"2019-11-01T21:56:00Z\"}"

    companion object {
        fun launch(context: Context, login: String) {
            val intent = Intent(context, GitUserDetailActivity::class.java)
            intent.putExtra(GIT_USER_LOGIN, login)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user_detail)
        initView()
        initViewModel()
        initIntentData()
    }

    private fun loadFromMockData() {
        pb_loading.visibility = View.GONE
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<GitUserInfo> = moshi.adapter(GitUserInfo::class.java)
        val gitUserInfo = adapter.fromJson(mockUserJson)
        gitUserInfo?.let {
            updateUI(gitUserInfo)
        }
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
            } else if (it is ApiSuccessResponse) {
                val successResponse = it as ApiSuccessResponse
                updateUI(it.body)
                val name = it.body.name
            } else if (it is ApiEmptyResponse) {
                Toast.makeText(this, "Sorry, there are something went wrong", Toast.LENGTH_LONG)
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
            chip_staff.visibility = if (siteAdmin) View.GONE else View.VISIBLE
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
