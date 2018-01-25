package com.antalya.coders.pigeon


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BlankFragment : Fragment() {

  private fun init(view: View){

  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val rootView = inflater!!.inflate(R.layout.fragment_blank, container, false)
    init(rootView)



    return rootView
  }

}
