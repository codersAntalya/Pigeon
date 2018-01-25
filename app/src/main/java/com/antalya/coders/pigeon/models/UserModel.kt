package com.antalya.coders.pigeon.models

/**
 * Created by kemalturk on 25.01.2018.
 */
class UserModel {

  var user_id: String? = null
  var name: String? = null
  var photo_url: String? = null
  var photo_location: String? = null
  var signup_date: String? = null
  var conversations: List<UserConversationModel>? = null
  var blocked_users: List<BlockedUserModel>? = null

}