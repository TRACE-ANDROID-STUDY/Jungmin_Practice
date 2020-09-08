package com.trace.trace_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.trace.trace_study.AutoLogin.MySharedPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_viewPager.adapter=MainPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit=2
        main_viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked=true
            }

        })

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_home->main_viewPager.currentItem=0
                R.id.menu_book->main_viewPager.currentItem=1
                R.id.menu_person->main_viewPager.currentItem=2
            }
        }

    }
}