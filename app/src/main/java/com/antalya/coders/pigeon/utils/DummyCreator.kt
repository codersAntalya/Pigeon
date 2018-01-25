package com.antalya.coders.pigeon.utils

import com.antalya.coders.pigeon.models.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by kemalturk on 25.01.2018.
 */
class DummyCreator {

  private val database = FirebaseDatabase.getInstance()
  private val usersRef = database.getReference("users")
  private val conversationRef = database.getReference("conversations")

  fun create(){

    createUsers()

    createConversations()


  }

  private fun createUsers(){

    for (i in 0..9){

      val user1 = HashMap<String, String>()
      user1.put("user_id", "+90530904247" + i)
      user1.put("name", "Kemal Türk " + i)
      user1.put("photo_url", " ")
      user1.put("photo_location", " ")
      user1.put("signup_date", "25/01/2018-19:31")

      usersRef.child(user1["user_id"]).setValue(user1)

    }

  }

  private fun createConversations(){

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener{
      override fun onCancelled(p0: DatabaseError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onDataChange(snapshot: DataSnapshot?) {

        snapshot?.children?.forEach { c ->

          val user = c.getValue(UserModel::class.java)

          if (user?.user_id != "+905309042476"){

            val u1 = ConversationUserModel()
            u1.user_id = "+905309042476"
            val u2 = ConversationUserModel()
            u2.user_id = user?.user_id ?: ""

            val users = ArrayList<ConversationUserModel>()
            users.add(u1)
            users.add(u2)

            val conversationKey = conversationRef.push().key

            val conversation = ConversationModel()

            conversation.conversation_id = conversationKey
            conversation.start_date = "25/01/2018-20:25"
            conversation.users = users
            conversation.messages = makeMessages(users)


            val userConversation = UserConversationModel()
            userConversation.conversation_id = conversationKey
            userConversation.unread_count = 0

            usersRef.child(users[0].user_id + "/conversations/" + conversationKey).setValue(userConversation)
            usersRef.child(users[1].user_id + "/conversations/" + conversationKey).setValue(userConversation)
            conversationRef.child(conversationKey).setValue(conversation)


          }


        }




      }
    })

  }


  private fun makeMessages(users: List<ConversationUserModel>): List<MessageModel>{

    val list = ArrayList<MessageModel>()

    for (i in 0..9){

      val m1 = MessageModel()
      m1.from = users[0].user_id
      m1.date = "25/01/2018-20:51"
      m1.text = "sa"

      val m2 = MessageModel()
      m2.from = users[0].user_id
      m2.date = "25/01/2018-20:51"
      m2.text = "as knk"

      list.add(m1)
      list.add(m2)

    }

    return list

  }
}