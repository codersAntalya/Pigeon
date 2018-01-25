package com.antalya.coders.pigeon.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.antalya.coders.pigeon.R

class ChatListFragment : Fragment() {

  private var listView: ListView? = null


  private fun init(view: View){

    listView = view.findViewById(R.id.listViewChat)


  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_chat_list, container, false)
    init(rootView)







    return rootView
  }

}
