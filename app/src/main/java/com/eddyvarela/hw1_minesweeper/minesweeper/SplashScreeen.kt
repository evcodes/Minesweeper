package com.eddyvarela.hw1_minesweeper.minesweeper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.eddyvarela.hw1_minesweeper.R
import kotlinx.android.synthetic.main.activity_splash_screeen.*

class SplashScreeen : AppCompatActivity() {


    companion object {
        public val THRESHOLD = "THRESHOLD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screeen)


        btnEasy.setOnClickListener {
            var intentDetails = Intent()
            intentDetails.setClass(this@SplashScreeen,
                MainActivity::class.java)
            intentDetails.putExtra(THRESHOLD, 90)
            startActivity(intentDetails)

        }

        btnMedium.setOnClickListener {
            var intentDetails = Intent()
            intentDetails.setClass(this@SplashScreeen,
                MainActivity::class.java)

            intentDetails.putExtra(THRESHOLD, 80)
            startActivity(intentDetails)
        }

        btnHard.setOnClickListener {
            var intentDetails = Intent()
            intentDetails.setClass(this@SplashScreeen,
                MainActivity::class.java)
            intentDetails.putExtra(THRESHOLD, 75)
            startActivity(intentDetails)
        }
    }
}
