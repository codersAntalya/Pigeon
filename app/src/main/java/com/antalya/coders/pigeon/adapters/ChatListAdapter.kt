package com.antalya.coders.pigeon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.antalya.coders.pigeon.R
import com.antalya.coders.pigeon.models.ConversationListModel
import kotlinx.android.synthetic.main.chat_list_item.view.*

/**
 * Created by kemalturk on 25.01.2018.
 */
class ChatListAdapter(val list: List<ConversationListModel>): BaseAdapter() {


  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view = LayoutInflater.from(parent?.context).inflate(R.layout.chat_list_item, parent, false)

    val conversation = list[position]
    view.tvName.text = conversation.name ?: ""
    view.tvDate.text = conversation.date ?: ""
    view.tvLastMessage.text = conversation.lastMessage ?: ""



    return view
  }

  override fun getItem(position: Int): Any {
    return list[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return list.size
  }


}