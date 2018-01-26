package com.antalya.coders.pigeon.callbacks


/**
 * Created by kemalturk on 26.01.2018.
 */
interface MyDbCallback<in model> {

  fun onResponse(list: List<model>)
  fun onError(e: DatabaseError?)

}