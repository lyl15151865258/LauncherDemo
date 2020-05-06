package com.example.launcherdemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_app.view.*

class AppAdapter(private val apps: List<ResolveInfo>) : RecyclerView.Adapter<AppAdapter.AppHolder>() {

    private lateinit var context: Context

    class AppHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        context = parent.context
        val view = View.inflate(context, R.layout.item_app, null)
        return AppHolder(view)
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    override fun onBindViewHolder(holder: AppHolder, position: Int) {
        val resolveInfo = apps[position]
        val activityInfo = resolveInfo.activityInfo
        holder.itemView.ivIcon.setImageDrawable(activityInfo.loadIcon(context.packageManager))
        holder.itemView.tvName.text = resolveInfo.loadLabel(context.packageManager)
        holder.itemView.setOnClickListener {
            val intent = Intent().apply {
                component = ComponentName(activityInfo.packageName, activityInfo.name)
            }
            context.startActivity(intent)
        }
    }
}
