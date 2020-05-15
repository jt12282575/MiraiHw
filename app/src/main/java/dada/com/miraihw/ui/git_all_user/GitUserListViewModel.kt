package dada.com.miraihw.ui.git_all_user


import dada.com.miraihw.api.GitApi
import androidx.lifecycle.ViewModel

class GitUserListViewModel : ViewModel() {
     val api = GitApi.get()

}