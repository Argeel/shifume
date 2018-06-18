package com.argeel.shifume.controller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.argeel.shifume.R
import com.argeel.shifume.R.drawable
import java.util.Random

/**
 * Created by gael.thiebaut on 15/06/2018.
 * Language : Kotlin
 **/

class BattleFragment: Fragment(), OnClickListener {

  private var imageViewAI: ImageView? = null
  private var imageViewUser: ImageView? = null
  private var imageViewValidate: ImageView? = null
  private var linearLayoutBattle: LinearLayout? = null

  private var imageViewBattle: ImageView? = null

  companion object {
    fun newInstance () : BattleFragment {
      return BattleFragment()
    }
  }

  fun ClosedRange<Int>.random() =
    Random().nextInt(endInclusive - start) +  start

  override
  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(
        R.layout.content_battle, container,
        false)

    val imageButtonRock: ImageButton = view.findViewById(R.id.imageButtonRock)
    imageButtonRock.setOnClickListener(this)

    val imageButtonPaper: ImageButton = view.findViewById(R.id.imageButtonPaper)
    imageButtonPaper.setOnClickListener(this)

    val imageButtonCisors: ImageButton = view.findViewById(R.id.imageButtonCisors)
    imageButtonCisors.setOnClickListener(this)

    imageViewBattle = view.findViewById(R.id.imageViewBattle)

    linearLayoutBattle = view.findViewById(R.id.linearLayoutBattle)

    imageViewAI = view.findViewById(R.id.imageViewAIResult)
    imageViewUser = view.findViewById(R.id.imageViewUserResult)
    imageViewValidate = view.findViewById(R.id.imageViewValide)

    return view
  }

  override
  fun onClick(v: View?) {
    if (imageViewBattle!!.visibility == VISIBLE) {
      imageViewBattle!!.setBackgroundResource(R.color.white)
      imageViewBattle!!.visibility = GONE
    }

    val resultAI: Int = (0..3).random()
    val resourceIdAI: Int = getResourceIdAiFromResult(resultAI)

    val resultsUser = getResourceIdUserFromResult(v)
    val resultUser: Int = resultsUser[0]
    val resourceIdUser: Int = resultsUser[1]

    val resourceIdValidate: Int = getValidationFromResult(resultAI, resultUser)

    setupImageFromResourceId(resourceIdUser, resourceIdAI, resourceIdValidate)
  }

  /* Normal AI : Play a random action from Rock, Paper, Scissors */
  fun getResourceIdAiFromResult(resultAI: Int): Int {
    when (resultAI) {
      0 -> {
        return drawable.rock_hand_ai
      }
      1 -> {
        return drawable.paper_hand_ai
      }
      2 -> {
        return drawable.scissors_hand_ai
      }
    }
    return 0
  }

  fun getResourceIdUserFromResult(v: View?): IntArray {
    when (v!!.id) {
      R.id.imageButtonRock -> {
        return intArrayOf(0, drawable.rock_hand)
      }
      R.id.imageButtonPaper -> {
        return intArrayOf(1, drawable.paper_hand)
      }
      R.id.imageButtonCisors -> {
        return intArrayOf(2, drawable.scissors_hand)
      }
    }
    return intArrayOf(0, 0)
  }
  fun getValidationFromResult(resultAI: Int, resultUser: Int) : Int {
    when (resultAI) {
      0 -> {
        if (resultUser == 0) {
          return 0
        } else if (resultUser == 1) {
          return R.drawable.green_check
        } else {
          return R.drawable.red_cross
        }
      }
      1 -> {
        if (resultUser == 0) {
          return R.drawable.red_cross
        } else if (resultUser == 1) {
          return 0
        } else {
          return R.drawable.green_check
        }
      }
      2 -> {
        if (resultUser == 0) {
          return R.drawable.green_check
        } else if (resultUser == 1) {
          return R.drawable.red_cross
        } else {
          return 0
        }
      }
    }

    return 1
  }
  /***************************************************************/

  // TODO : Make Easy and Hard AI (Starts with Easy : Full Win, Hard : Full lose)

  private fun setupImageFromResourceId(idUser: Int, idAI: Int, idValidate: Int) {
    if (linearLayoutBattle!!.visibility == GONE) {
      linearLayoutBattle!!.visibility = VISIBLE
    }

    imageViewUser!!.setImageResource(idUser)
    imageViewAI!!.setImageResource(idAI)
    imageViewValidate!!.setImageResource(idValidate)
  }
}
