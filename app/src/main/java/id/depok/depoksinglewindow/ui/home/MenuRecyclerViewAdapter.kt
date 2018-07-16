package id.depok.depoksinglewindow.ui.home

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.depok.depoksinglewindow.R
import id.depok.depoksinglewindow.data.AppMenu
import id.depok.depoksinglewindow.databinding.ItemHomeMenuBinding

/**
 * Created by PiNGUiN on 2017-12-17.
 */
class MenuRecyclerViewAdapter(val context: Context, private val appMenus: List<AppMenu>, val callback: MenuCallback):
        RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>() {

    override fun getItemCount(): Int {
        return appMenus.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_menu, parent,
                false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.binding?.apply {
            val menu = appMenus[position]
            imageviewItemmenuIcon.setImageResource(menu.iconResId)
            textviewItemmenuLabel.text = context.getString(menu.stringResId)
        }
    }

    inner class MenuViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val binding: ItemHomeMenuBinding? = DataBindingUtil.bind(itemView!!)

        init {
            itemView?.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            callback.onClick(appMenus[adapterPosition])
        }
    }

    interface MenuCallback {
        fun onClick(appMenu: AppMenu)
    }
}