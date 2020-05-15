package dada.com.miraihw.ui.git_all_user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import dada.com.miraihw.R
import dada.com.miraihw.data.GitUser
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.roundToInt

class GitUserListAdapter (val context: Context,var gitUserList:MutableList<GitUser>)
    :RecyclerView.Adapter<GitUserListAdapter.GitUserListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.git_user_item, parent, false)
        return GitUserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gitUserList.size
    }

    override fun onBindViewHolder(holder: GitUserListViewHolder, position: Int) {
        holder?.bind(gitUserList.get(position))
    }

    inner class GitUserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val staff: Chip =itemView?.findViewById(R.id.staff)
        private val avatar: CircleImageView =itemView?.findViewById(R.id.avatar)
        private val login: TextView =itemView?.findViewById(R.id.login)

        fun bind(gitUser: GitUser){
            login.text = gitUser.login
            staff.visibility = if (gitUser.siteAdmin) View.GONE else View.VISIBLE
            val imageSize:Int = context.resources.getDimension(R.dimen.image_size).roundToInt()
            Picasso.get().load(gitUser.avatarUrl).resize(imageSize,imageSize).into(avatar)
        }
    }

}