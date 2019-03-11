package com.eddyvarela.hw1_minesweeper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.MinesweeperModel
import com.eddyvarela.hw1_minesweeper.minesweeperview.MinesweeperView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    public fun MinesweeperView.resetGame(){
        MinesweeperModel.resetModel()
        invalidate()
    }

    fun setStatusText(status: String) {

    }
}
