package com.argeel.shifume.controller.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import com.argeel.shifume.R

/**
 * Created by gael.thiebaut on 15/06/2018.
 * Language : Kotlin
 **/

class SettingFragment: Fragment (), OnClickListener {

  val PREFS_FILENAME: String = "shifumePreferences"
  var prefs: SharedPreferences? = null

  private var  buttonEasy: Button? = null
  private var  buttonNormal: Button? = null
  private var  buttonHard: Button? = null

  companion object {
    fun newInstance () : SettingFragment {
      return SettingFragment()
    }
  }

  override
  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    prefs = context!!.getSharedPreferences(PREFS_FILENAME, 0)

    val view: View = inflater.inflate(
        R.layout.content_setting, container,
        false)

    buttonEasy = view.findViewById(R.id.buttonEasy)
    buttonEasy!!.setOnClickListener(this)

    buttonNormal = view.findViewById(R.id.buttonNormal)
    buttonNormal!!.setOnClickListener(this)

    buttonHard = view.findViewById(R.id.buttonHard)
    buttonHard!!.setOnClickListener(this)

    setupDifficulty(prefs!!.getInt("difficulty", 1))

    return view
  }

  override
  fun onClick(v: View?) {
    when (v!!.id) {
      R.id.buttonEasy -> {
        setupDifficulty(0)
      }
      R.id.buttonNormal -> {
        setupDifficulty(1)
      }
      R.id.buttonHard -> {
        setupDifficulty(2)
      }
    }
  }

  private fun setupDifficulty(difficulty: Int) {
    when (difficulty) {
      0 -> {
        buttonEasy!!.setBackgroundResource(R.color.darkGrey)
        buttonNormal!!.setBackgroundResource(R.color.grey)
        buttonHard!!.setBackgroundResource(R.color.grey)
        prefs!!.edit().putInt("difficulty", 0).apply()      }
      1 -> {
        buttonEasy!!.setBackgroundResource(R.color.grey)
        buttonNormal!!.setBackgroundResource(R.color.darkGrey)
        buttonHard!!.setBackgroundResource(R.color.grey)
        prefs!!.edit().putInt("difficulty", 1).apply()      }
      2 -> {
        buttonEasy!!.setBackgroundResource(R.color.grey)
        buttonNormal!!.setBackgroundResource(R.color.grey)
        buttonHard!!.setBackgroundResource(R.color.darkGrey)
        prefs!!.edit().putInt("difficulty", 2).apply()      }
    }
  }
}
