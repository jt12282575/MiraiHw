package dada.com.miraihw.ui.git_detail_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pivincii.livedata_retrofit.network.ApiResponse
import dada.com.miraihw.api.GitApi
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.data.GitUserInfo

class GitUserDetailViewModel: ViewModel() {
    val api = GitApi.get()
    fun loadGitUser(login:String): LiveData<ApiResponse<GitUserInfo>> {
        return api.getGitUser(login)
    }
}