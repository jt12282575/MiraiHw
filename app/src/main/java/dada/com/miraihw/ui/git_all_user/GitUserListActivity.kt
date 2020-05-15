package dada.com.miraihw.ui.git_all_user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pivincii.livedata_retrofit.network.ApiEmptyResponse
import com.pivincii.livedata_retrofit.network.ApiErrorResponse
import com.pivincii.livedata_retrofit.network.ApiSuccessResponse
import dada.com.miraihw.R
import dada.com.miraihw.data.GitUser
import kotlinx.android.synthetic.main.git_user_list_layout.*


class GitUserListActivity : AppCompatActivity() {
    lateinit var gitUserListViewModel: GitUserListViewModel
    lateinit var gitUserListAdapter: GitUserListAdapter
    var gitUserList = mutableListOf<GitUser>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_user_list_layout)
        initView()
        initViewModel()

    }

    private fun initView() {
        gitUserListAdapter = GitUserListAdapter(this,gitUserList)
        git_user_rcvlist.layoutManager = LinearLayoutManager(this)
        git_user_rcvlist.adapter = gitUserListAdapter
    }


    private fun initViewModel() {
        gitUserListViewModel = ViewModelProviders.of(this).get(GitUserListViewModel::class.java)
        gitUserListViewModel.gitUserListData.observe(this, Observer {
            if (it is ApiErrorResponse){
                val errorResponse = it as ApiErrorResponse
                Toast.makeText(this,errorResponse.errorMessage,Toast.LENGTH_LONG).show()
            }else if(it is ApiSuccessResponse){
                val successResponse = it as ApiSuccessResponse
                gitUserList.addAll(successResponse.body)
                gitUserListAdapter?.notifyDataSetChanged()
                //TODO load data success
                Toast.makeText(this,"Load data",Toast.LENGTH_LONG).show()
            }else if(it is ApiEmptyResponse){
                Toast.makeText(this,"Empty data",Toast.LENGTH_LONG).show()
            }
        })
    }
}
