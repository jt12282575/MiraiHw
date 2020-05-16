package dada.com.miraihw.ui.git_detail_page

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dada.com.miraihw.R
import dada.com.miraihw.const.Const.Companion.GIT_USER_LOGIN
import kotlin.math.log

class GitUserDetailActivity : AppCompatActivity() {
    lateinit var login:String

    companion object{
        fun launch(context: Context,login:String){
            val intent = Intent(context,GitUserDetailActivity::class.java)
            intent.putExtra(GIT_USER_LOGIN,login)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_user_detail)
        login = intent.getStringExtra(GIT_USER_LOGIN)
        Toast.makeText(this, login,Toast.LENGTH_LONG).show()
    }
}
