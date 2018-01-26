package com.antalya.coders.pigeon.models

/**
 * Created by kemalturk on 25.01.2018.
 */
class ConversationModel {

  var conversation_id: String? = null
  var start_date: String? = null

  var users: HashMap<String, ConversationUserModel>? = null
  var messages: List<MessageModel>? = null

}