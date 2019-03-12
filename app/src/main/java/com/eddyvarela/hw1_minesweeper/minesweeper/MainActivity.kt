package com.eddyvarela.hw1_minesweeper.minesweeper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import com.eddyvarela.hw1_minesweeper.R
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.MinesweeperModel
import com.eddyvarela.hw1_minesweeper.minesweeperview.MinesweeperView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var threshold:Int

        val toggle: ToggleButton = findViewById(R.id.toggleFlag)
        toggle.setOnCheckedChangeListener { _, _ ->  MinesweeperModel.toggleFlag()
        }


        if (intent.extras!!.containsKey(SplashScreeen.THRESHOLD)){
            threshold = intent.getIntExtra(SplashScreeen.THRESHOLD, 80)
            MinesweeperModel.resetModel()
            MinesweeperModel.setGameBoard(threshold)
        }

        btnReset.setOnClickListener {
            minesweeperView.resetGame()
            MinesweeperModel.flagging=false
            toggle.isChecked = false
        }
    }

        fun MinesweeperView.resetGame(){
            MinesweeperModel.resetModel()
            invalidate()
        }
    }
