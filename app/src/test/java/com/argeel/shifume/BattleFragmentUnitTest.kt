package com.argeel.shifume

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.argeel.shifume.R.drawable
import com.argeel.shifume.controller.activity.MainActivity
import com.argeel.shifume.controller.fragment.BattleFragment
import org.junit.Assert
import org.junit.Test

/**
 * Created by gael.thiebaut on 18/06/2018.
 * Language : Kotlin
 **/

class BattleFragmentUnitTest {
  val fragment: BattleFragment = BattleFragment.newInstance()

  @Test
  fun getTheGoodResourceIdForAI() {
    Assert.assertEquals(drawable.rock_hand_ai, fragment.getResourceIdAiFromResult(0))
    Assert.assertEquals(drawable.paper_hand_ai, fragment.getResourceIdAiFromResult(1))
    Assert.assertEquals(drawable.scissors_hand_ai, fragment.getResourceIdAiFromResult(2))
  }

  @Test
  fun getTheGoodResourceIdForValidation() {
    Assert.assertEquals(drawable.green_check, fragment.getValidationFromResult(0, 1))
    Assert.assertEquals(0, fragment.getValidationFromResult(1, 1))
    Assert.assertEquals(drawable.red_cross, fragment.getValidationFromResult(2, 1))
  }

  @Test
  fun getTest() {
    val mainActivity: MainActivity = MainActivity()
    mainActivity!!.supportFragmentManager!!.beginTransaction().replace(R.id.linearLayoutFragments, fragment, "test_tag").commit()
    //fragment.layoutInflater.inflate(R.layout.content_battle, null)
    val imageButtonRock: View = fragment.view!!.findViewById(R.id.imageButtonRock)

    Assert.assertEquals(intArrayOf(0, drawable.rock_hand), fragment.getResourceIdUserFromResult(imageButtonRock))
  }
}
