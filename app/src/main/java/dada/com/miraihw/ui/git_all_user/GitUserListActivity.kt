package dada.com.miraihw.ui.git_all_user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pivincii.livedata_retrofit.network.ApiErrorResponse
import dada.com.miraihw.R


class GitUserListActivity : AppCompatActivity() {
     lateinit var gitUserListViewModel: GitUserListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_user_list)
        initViewModel()
        loadData()

    }

    private fun loadData() {
        val gitUserList = gitUserListViewModel.api.getGitAllUsers()
        gitUserList.observe(this, Observer {
            if (it is ApiErrorResponse){
                val errorResponse = it as ApiErrorResponse
                Toast.makeText(this,errorResponse.errorMessage,Toast.LENGTH_LONG).show()
            }else{
                //TODO load data success
                Toast.makeText(this,"Load data",Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initViewModel() {
        gitUserListViewModel = ViewModelProviders.of(this).get(GitUserListViewModel::class.java)

    }
}
