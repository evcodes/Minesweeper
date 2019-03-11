package com.eddyvarela.hw1_minesweeper.minesweeper.Model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*

//Type 0 => Empty space, 1 => Bomb
// minesAround int between 0 -> 8
// isFlagged either flagged or not
// wasClicked


val BOMB = 1;
val EMPTY_SPACE = 0;

data class Field(var type: Int, var minesAround: Int,
                 var isFlagged:Boolean,var wasClicked: Boolean){

}