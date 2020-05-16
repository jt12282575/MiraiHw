package dada.com.miraihw.ui.git_all_user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import dada.com.miraihw.R
import dada.com.miraihw.data.GitUser
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.roundToInt

class GitUserListAdapter (
    val context: Context,
    var gitUserList:MutableList<GitUser>,
    var gitUserItemOnClickListener: GitUserItemOnClickListener
)
    :RecyclerView.Adapter<GitUserListAdapter.GitUserListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.git_user_item, parent, false)
        return GitUserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gitUserList.size
    }

    override fun onBindViewHolder(holder: GitUserListViewHolder, position: Int) {
        holder?.bind(gitUserList.get(position),gitUserItemOnClickListener,position)
    }

    inner class GitUserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvStaff: Chip =itemView?.findViewById(R.id.chip_staff)
        private val ciAvatar: CircleImageView =itemView?.findViewById(R.id.ci_avatar)
        private val tvLogin: TextView =itemView?.findViewById(R.id.tv_login)
        private val clUserContainer: ConstraintLayout = itemView?.findViewById(R.id.cl_user_container)
        private val tvIndex:TextView = itemView?.findViewById(R.id.tv_index)

        fun bind(gitUser: GitUser,gitUserItemOnClickListener: GitUserItemOnClickListener,position: Int){
            tvLogin.text = gitUser.login
            tvStaff.visibility = if (gitUser.siteAdmin) View.GONE else View.VISIBLE
            val imageSize:Int = context.resources.getDimension(R.dimen.list_avatar_image_size).roundToInt()
            Picasso.get().load(gitUser.avatarUrl).resize(imageSize,imageSize).into(ciAvatar)
            clUserContainer.setOnClickListener {
                gitUserItemOnClickListener.onClick(it,position)
            }
            tvIndex.text = (position+1).toString()
        }
    }

    interface GitUserItemOnClickListener{
        fun onClick(v:View,position:Int)
    }



}
