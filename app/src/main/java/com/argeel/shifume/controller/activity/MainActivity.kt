package com.argeel.shifume.controller.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.argeel.shifume.R
import com.argeel.shifume.R.id
import com.argeel.shifume.R.string
import com.argeel.shifume.controller.fragment.BattleFragment
import com.argeel.shifume.controller.fragment.MenuFragment
import com.argeel.shifume.controller.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.app_bar_main.toolbar

/**
 * Created by gael.thiebaut on 15/06/2018.
 * Language : Kotlin
 **/

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  public enum class Page {
    MENU,
    BATTLE,
    SETTINGS,
    CLEAR
  };

  private var page: Page = Page.MENU

  override
  fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar,
        string.navigation_drawer_open,
        string.navigation_drawer_close
    )
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)

    // Load Menu Fragment
    replaceFragmentWith(MenuFragment.newInstance(), "menu_fragment")
  }

  override
  fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override
  fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_menu -> {
        page = Page.MENU
        replaceFragmentWith(MenuFragment.newInstance(), "menu_fragment")
      }
      R.id.nav_battle -> {
        page = Page.BATTLE
        replaceFragmentWith(BattleFragment.newInstance(), "battle_fragment")
      }
      R.id.nav_manage -> {
        page = Page.SETTINGS
        replaceFragmentWith(SettingFragment.newInstance(), "setting_fragment")
      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }

  private fun replaceFragmentWith(fragment: Fragment, tag: String) {
    supportFragmentManager
        .beginTransaction()
        .replace(
            id.linearLayoutFragments,
            fragment,
            tag
        )
        .addToBackStack(null)
        .commit()
  }
}
