package com.antalya.coders.pigeon.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.antalya.coders.pigeon.NumberContainer
import com.antalya.coders.pigeon.R
import com.antalya.coders.pigeon.callbacks.MyDbCallback
import com.antalya.coders.pigeon.management.ConversationListDbManager
import com.antalya.coders.pigeon.models.ConversationListModel
import com.google.firebase.database.DatabaseError

class ChatListFragment : Fragment(), MyDbCallback<ConversationListModel> {



  private var listView: ListView? = null
  private lateinit var dbManager: ConversationListDbManager


  private fun init(view: View){

    listView = view.findViewById(R.id.listViewChat)


  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_chat_list, container, false)
    init(rootView)

    dbManager = ConversationListDbManager(NumberContainer.number, this)
    dbManager.execute()




    return rootView
  }

  override fun onResponse(list: List<ConversationListModel>) {
    //Todo
  }

  override fun onError(e: DatabaseError?) {
    Toast.makeText(context, "Bir hata oluştu!", Toast.LENGTH_LONG).show()
    Log.e("Hata", e?.message)
  }


}
