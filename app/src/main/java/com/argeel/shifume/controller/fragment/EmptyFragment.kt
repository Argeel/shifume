package com.argeel.shifume.controller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.argeel.shifume.R

/**
 * Created by gael.thiebaut on 15/06/2018.
 * Language : Kotlin
 **/

class EmptyFragment: Fragment() {
  companion object {
    fun newInstance () : EmptyFragment {
      return EmptyFragment()
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(
        R.layout.content_empty, container,
        false)

    return view
  }
}
