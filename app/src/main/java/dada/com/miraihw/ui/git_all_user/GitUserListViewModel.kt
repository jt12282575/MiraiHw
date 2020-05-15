package dada.com.miraihw.ui.git_all_user



import androidx.lifecycle.LiveData
import dada.com.miraihw.api.GitApi
import androidx.lifecycle.ViewModel
import com.pivincii.livedata_retrofit.network.ApiResponse
import dada.com.miraihw.data.GitUser


class GitUserListViewModel : ViewModel() {
     val api = GitApi.get()
     val gitUserListData = loadGitUsers()
     fun loadGitUsers():LiveData<ApiResponse<List<GitUser>>>{
          return api.getGitAllUsers()
     }

}