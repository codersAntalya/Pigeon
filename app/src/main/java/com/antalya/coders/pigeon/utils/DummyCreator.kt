package com.antalya.coders.pigeon.utils

import com.antalya.coders.pigeon.models.*
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by kemalturk on 25.01.2018.
 */
class DummyCreator {

  private val db = FirebaseFirestore.getInstance()
  private val usersCollection = db.collection("users")
  private val conversationCollection = db.collection("conversations")

  fun create(){

    createUsers()

    createConversations()


  }

  private fun createUsers(){

    for (i in 0..9){

      val user1 = HashMap<String, String>()
      user1["user_id"] = "+90530904247" + i
      user1["name"] = "Kemal Türk " + i
      user1["photo_url"] = " "
      user1["photo_location"] = " "
      user1["signup_date"] = "25/01/2018-19:31"

      usersCollection
          .document(user1["user_id"].toString())
          .set(user1 as Map<String, String>)

    }

  }

  private fun createConversations(){

    usersCollection
        .get()
        .addOnSuccessListener { querySnapshot ->

          querySnapshot.documents.forEach { s ->

            val user = s.toObject(UserModel::class.java)

            if (user.user_id != "+905309042476"){

              val u1 = ConversationUserModel()
              u1.user_id = "+905309042476"
              val u2 = ConversationUserModel()
              u2.user_id = user.user_id ?: ""



              val users = HashMap<String, ConversationUserModel>()
              users.put(u1.user_id.toString(), u1)
              users.put(u2.user_id.toString(), u2)


              val conversationKey = conversationCollection.document().id

              val conversation = ConversationModel()

              conversation.conversation_id = conversationKey
              conversation.start_date = "25/01/2018-20:25"
              conversation.users = users
              conversation.messages = makeMessages(u1, u2)


              val userConversation = UserConversationModel()
              userConversation.conversation_id = conversationKey
              userConversation.unread_count = 0

              usersCollection.document(u1.user_id + "/conversations/" + conversationKey).set(userConversation)
              usersCollection.document(u2.user_id + "/conversations/" + conversationKey).set(userConversation)
              conversationCollection.document(conversationKey).set(conversation)

            }

          }

        }

  }


  private fun makeMessages(u1: ConversationUserModel, u2: ConversationUserModel): List<MessageModel>{

    val list = ArrayList<MessageModel>()

    for (i in 0..9){

      val m1 = MessageModel()
      m1.from = u1.user_id
      m1.date = "25/01/2018-20:51"
      m1.text = "sa"

      val m2 = MessageModel()
      m2.from = u2.user_id
      m2.date = "25/01/2018-20:51"
      m2.text = "as knk"

      list.add(m1)
      list.add(m2)

    }

    return list

  }
}