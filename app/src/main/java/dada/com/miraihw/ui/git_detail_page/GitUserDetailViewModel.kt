package dada.com.miraihw.ui.git_detail_page


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dada.com.miraihw.repositary.Repositary

class GitUserDetailViewModel: ViewModel() {
    val repositary = Repositary()
    val login = MutableLiveData<String>()
    val gitUserInfo = Transformations.switchMap(login){
        repositary.getGitUserInfo(it)
    }

}