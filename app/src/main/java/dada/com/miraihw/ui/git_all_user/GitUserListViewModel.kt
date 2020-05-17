package dada.com.miraihw.ui.git_all_user



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dada.com.miraihw.api.GitApi
import androidx.lifecycle.ViewModel
import com.pivincii.livedata_retrofit.network.ApiResponse
import dada.com.miraihw.const.Const.Companion.INIT_SINCE_USER_ID
import dada.com.miraihw.const.Const.Companion.USER_PER_PAGE
import dada.com.miraihw.data.GitUser


class GitUserListViewModel : ViewModel() {
     val api = GitApi.get()
     val gitUserListData = loadGitUsers()
     val lastGitUserId = MutableLiveData<Int>()
     fun loadGitUsers():LiveData<ApiResponse<List<GitUser>>>{
          return api.getGitAllUsersPerPage(INIT_SINCE_USER_ID,USER_PER_PAGE)
     }
     fun loadMoreGitUsers(id:Int){

     }

}