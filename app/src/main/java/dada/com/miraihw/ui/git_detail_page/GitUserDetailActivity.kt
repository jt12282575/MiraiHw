package dada.com.miraihw.ui.git_detail_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pivincii.livedata_retrofit.network.ApiEmptyResponse
import com.pivincii.livedata_retrofit.network.ApiErrorResponse
import com.pivincii.livedata_retrofit.network.ApiSuccessResponse
import dada.com.miraihw.R
import dada.com.miraihw.const.Const.Companion.GIT_USER_LOGIN
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.data.GitUserInfo
import kotlin.math.log

class GitUserDetailActivity : AppCompatActivity() {
    lateinit var login:String
    lateinit var gitUserDetailViewModel: GitUserDetailViewModel

    companion object{
        fun launch(context: Context,login:String){
            val intent = Intent(context,GitUserDetailActivity::class.java)
            intent.putExtra(GIT_USER_LOGIN,login)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user_detail)
        initData()
        initViewModel()
    }

    private fun initViewModel() {
        gitUserDetailViewModel = ViewModelProviders.of(this).get(GitUserDetailViewModel::class.java)
        if (!TextUtils.isEmpty(login)){
            gitUserDetailViewModel.loadGitUser(login).observe(this, Observer {
                if (it is ApiErrorResponse){
                    val errorResponse = it as ApiErrorResponse
                }else if(it is ApiSuccessResponse){
                    val successResponse = it as ApiSuccessResponse
                    updateUi(it.body)
                    val name = it.body.name
                }else if(it is ApiEmptyResponse){
                    //TODO show there is no userItem
                }
            })
        }

    }

    private fun updateUi(gitUserInfo: GitUserInfo){

    }

    private fun initData() {
        login = intent.getStringExtra(GIT_USER_LOGIN)
    }

}
