package dada.com.miraihw.repositary

import androidx.lifecycle.LiveData
import com.pivincii.livedata_retrofit.network.ApiResponse
import dada.com.miraihw.api.GitApi
import dada.com.miraihw.const.Const
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.data.GitUserInfo

class Repositary{
    val api = GitApi.get()
    fun getGitAllUserPerPage(lastUserId:Int): LiveData<ApiResponse<List<GitUser>>> {
        return api.getGitAllUsersPerPage(lastUserId, Const.USER_PER_PAGE)
    }
    fun getGitUserInfo(login:String):LiveData<ApiResponse<GitUserInfo>>{
        return api.getGitUserInfo(login)
    }
}