package com.netology.statsview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.netology.statsview.ui.StatsView
import android.view.View
import android.view.animation.Animation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<StatsView>(R.id.statsView)

        view.data = listOf(
            0.25F, 0.25F, 0.25F, 0.25F
//            500F,
//            500F,
//            500F,
//            500F,
//            1F
        )

//        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.animation)
//            .apply {
//                setAnimationListener(object: Animation.AnimationListener{
//                    override fun onAnimationStart(animation: Animation?) {
////                        textView.text = "started"
//                    }
//
//                    override fun onAnimationEnd(animation: Animation?) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun onAnimationRepeat(animation: Animation?) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//            })
    }
}