package dada.com.miraihw.ui.git_all_user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pivincii.livedata_retrofit.network.ApiEmptyResponse
import com.pivincii.livedata_retrofit.network.ApiErrorResponse
import com.pivincii.livedata_retrofit.network.ApiSuccessResponse
import dada.com.miraihw.R
import dada.com.miraihw.const.Const.Companion.INIT_SINCE_USER_ID
import dada.com.miraihw.const.Const.Companion.LOAD_YET_SINCE_USER_ID
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.ui.git_detail_page.GitUserDetailActivity
import kotlinx.android.synthetic.main.git_user_list_layout.*


class GitUserListActivity : AppCompatActivity() {
    lateinit var gitUserListViewModel: GitUserListViewModel
    lateinit var gitUserListAdapter: GitUserListAdapter
    var gitUserList = mutableListOf<GitUser>()
    var currentTailGitUserId = INIT_SINCE_USER_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_user_list_layout)
        initView()
        initViewModel()

    }

    private fun initView() {
        gitUserListAdapter = GitUserListAdapter(this,gitUserList,
            object : GitUserListAdapter.GitUserItemOnClickListener {
                override fun onClick(v: View, position: Int) {
                    GitUserDetailActivity.launch(this@GitUserListActivity,gitUserList.get(position).login)
                }
            }
        )
        rcv_git_user_list.layoutManager = LinearLayoutManager(this)
        rcv_git_user_list.adapter = gitUserListAdapter
        srf_refresh_layout.setOnLoadMoreListener {
            gitUserListViewModel.loadMoreGitUsers(currentTailGitUserId)
        }

    }


    private fun initViewModel() {
        pb_loading.visibility = View.VISIBLE
        gitUserListViewModel = ViewModelProviders.of(this).get(GitUserListViewModel::class.java)
        gitUserListViewModel.gitUserListData.observe(this, Observer {
            pb_loading.visibility = View.GONE
            srf_refresh_layout.finishLoadMore()
            if (it is ApiErrorResponse){
                val errorResponse = it as ApiErrorResponse
                Toast.makeText(this,errorResponse.errorMessage,Toast.LENGTH_LONG).show()
            }else if(it is ApiSuccessResponse){
                val successResponse = it as ApiSuccessResponse
                gitUserList.addAll(successResponse.body)
                gitUserListAdapter?.notifyDataSetChanged()
                if (successResponse.body.isNotEmpty()) {
                    currentTailGitUserId = gitUserList.get(gitUserList.size-1).id
                }
            }else if(it is ApiEmptyResponse){
                //TODO show there is no userItem
            }
        })
        //Load init data
        gitUserListViewModel.loadMoreGitUsers(currentTailGitUserId)
    }
}
