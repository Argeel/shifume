package com.argeel.shifume.controller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import com.argeel.shifume.R
import kotlin.system.exitProcess

/**
 * Created by gael.thiebaut on 15/06/2018.
 * Language : Kotlin
 **/

class MenuFragment: Fragment(), OnClickListener {
  companion object {

    fun newInstance(): MenuFragment {
      return MenuFragment()
    }
  }
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(
        R.layout.content_menu, container,
        false)

    val buttonPlay: Button = view.findViewById(R.id.buttonMenuPlay)
    buttonPlay.setOnClickListener(this)

    val buttonSetting: Button = view.findViewById(R.id.buttonMenuSetting)
    buttonSetting.setOnClickListener(this)

    val buttonQuit: Button = view.findViewById(R.id.buttonMenuQuit)
    buttonQuit.setOnClickListener(this)

    return view
  }

  override fun onClick(v: View?) {
    when (v!!.id) {
      R.id.buttonMenuPlay -> {
        replaceFragmentWith(BattleFragment.newInstance(), "battle_fragment")
      }
      R.id.buttonMenuSetting -> {
        replaceFragmentWith(SettingFragment.newInstance(), "setting_fragment")
      }
      R.id.buttonMenuQuit -> {
        exitProcess(0)
      }
    }
  }

  private fun replaceFragmentWith(fragment: Fragment, tag: String) {
    activity!!.supportFragmentManager?.beginTransaction()?.replace(
        R.id.linearLayoutFragments,
        fragment, tag
    )?.addToBackStack(null)?.commit()
  }
}
