package com.antalya.coders.pigeon.management

import com.antalya.coders.pigeon.callbacks.MyDbCallback
import com.antalya.coders.pigeon.models.ConversationListModel
import com.antalya.coders.pigeon.models.ConversationModel
import com.antalya.coders.pigeon.models.UserConversationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by kemalturk on 26.01.2018.
 */
class ConversationListDbManager(val number: String, val callback: MyDbCallback<ConversationListModel>) {

  private val database = FirebaseDatabase.getInstance()
  private val mUserDbRef = database.getReference("users")
  private val mConversationDbRef = database.getReference("conversations")

  fun execute() {

    getUserConversationList()

  }

  private fun getUserConversationList() {


    val query =
        mConversationDbRef
            .orderByChild("users/$number")

    query.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError(p0)
      }

      override fun onDataChange(ds: DataSnapshot?) {

        val cList = ds?.children

        val returnList = ArrayList<ConversationListModel>()

        cList?.forEach { c ->
          val conversation = c.getValue(ConversationModel::class.java)

          val clm = ConversationListModel()
          clm.id = conversation?.conversation_id
          clm.date = conversation?.messages?.last()?.date
          clm.lastMessage = conversation?.messages?.last()?.text



        }


      }
    })


  }

  private fun getMainConversationList(conversationList: MutableIterable<DataSnapshot>?) {


  }

}