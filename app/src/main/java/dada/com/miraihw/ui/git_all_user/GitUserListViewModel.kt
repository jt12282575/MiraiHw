package dada.com.miraihw.ui.git_all_user



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dada.com.miraihw.const.Const.Companion.INIT_SINCE_USER_ID
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.repositary.Repositary


class GitUserListViewModel : ViewModel() {


     var currentTailGitUserId = INIT_SINCE_USER_ID
     val lastGitUserId = MutableLiveData<Int>()
     var gitUserList = mutableListOf<GitUser>()
     val repositary = Repositary()
     val gitUserListData = Transformations.switchMap(lastGitUserId) {
          repositary.getGitAllUserPerPage(it)
     }

     fun loadMoreGitUsers(){
          lastGitUserId.value = currentTailGitUserId
     }

     init {
          if (currentTailGitUserId == INIT_SINCE_USER_ID) {
               loadMoreGitUsers()
          }
     }



}