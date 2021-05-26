package pl.lodz.apzumi.utils.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_reposytory_list.view.*
import pl.lodz.apzumi.R
import pl.lodz.apzumi.model.Repo
import pl.lodz.apzumi.model.ReposytoryInformation

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var reposytoryList: List<ReposytoryInformation>? = null
    private var clickListener: (ReposytoryInformation) -> Unit = { }

    fun setReposytoryList(
        list: List<ReposytoryInformation>,
        clickListener: (ReposytoryInformation) -> Unit
    ) {
        reposytoryList = list
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.layout_reposytory_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        reposytoryList?.get(position)?.let {
            holder.setReposytory(it)
            holder.setOnClick {
                clickListener(it)
            }
        }
    }

    override fun getItemCount(): Int = reposytoryList?.size ?: 0

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun setReposytory(reposytory: ReposytoryInformation) {
            Glide.with(itemView.context).load(reposytory.avatarUrl).fitCenter()
                .placeholder(R.drawable.placeholder)
                .into(itemView.imageView)
            itemView.userNameInput.text = reposytory.userName
            itemView.reposytoryNameInput.text = reposytory.reposytoryName
            if (reposytory.repo == Repo.BITBUCKET) {
                itemView.setBackgroundColor(Color.BLUE)
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        fun setOnClick(function: () -> Unit) {
            itemView.setOnClickListener {
                function()
            }
        }
    }
}