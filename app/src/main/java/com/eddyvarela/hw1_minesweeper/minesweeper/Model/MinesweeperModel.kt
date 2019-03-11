package com.eddyvarela.hw1_minesweeper.minesweeper.Model

import android.util.Log
import java.util.*

//singleton object
object MinesweeperModel{

    var flagging = false

    var model = arrayOf(
        arrayOf(Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false)
    ),  arrayOf(Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false)
    ),  arrayOf(Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false)
    ),  arrayOf(Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false)
    ),  arrayOf(Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false),
                Field(0,0,false,false)
            )
    )

    private val BOUND = model.size-1
    var gameWon = false
    var gameLost = false

    fun modelToString() {
        for (i in 0..4) {
            for (j in 0..4) {
                var field = model[i][j]
                Log.d(
                    "print out mode",
                    "location: $i:$j, status: ${field.type}, bombs around:${field.minesAround}, flagged:${field.isFlagged} "
                )
            }
        }
    }

    fun isMovePossible(x:Int, y:Int, flagMode:Boolean): Boolean{
        var movePossible = false


        if (!flagMode){
            model[x][y].wasClicked = true
            if (model[x][y].type == EMPTY_SPACE){
                movePossible = true
            }
        }

        else if (flagMode){
            if(model[x][y].type == EMPTY_SPACE){
                activateBombs()
            }
            if (model[x][y].type == BOMB ){
                model[x][y].isFlagged = true
                movePossible = true
            }
        }
        return movePossible
    }


    fun activateBombs(){
        for (i in 0..BOUND){
            for (j in 0..BOUND){
                if (model[i][j].type == BOMB && (!model[i][j].isFlagged)){
                        model[i][j].wasClicked = true
                }
            }
        }
    }
    fun resetModel(){
        for(i in 0..BOUND){
            for (j in 0..BOUND){
                model[i][j].isFlagged = false
                model[i][j].wasClicked = false
            }
        }
        populateMines()
    }

     fun populateMines(){
        for (i in 0..BOUND){
            for (j in 0..BOUND){
                var bomb = Random().nextInt(100) +1
                if (bomb >=80){
                    model[i][j].type = BOMB
                }else{
                    model[i][j].type = EMPTY_SPACE
                }
            }
        }
    }

    fun calculateMinesAround(){
        checkCorners()
        checkVertRows()
        checkHorizRows()
        checkMiddleSquare()
    }
    private fun checkMiddleSquare(){
        for (i in 1..BOUND-1){
            for (j in 1..BOUND-1){
                if(model[i][j].type == EMPTY_SPACE){
                    var count = 0
                    if(model[i-1][j-1].type == BOMB) count++ //up left diag
                    if(model[i-1][j].type == BOMB) count++ // vertical up
                    if(model[i-1][j+1].type == BOMB) count++ // up right diag
                    if(model[i][j-1].type == BOMB) count++ // left
                    if(model[i][j+1].type == BOMB) count++ // right
                    if(model[i+1][j-1].type == BOMB) count++ //bot left diag
                    if(model[i+1][j].type == BOMB) count++ // bot
                    if(model[i+1][j+1].type == BOMB) count++ // bot right diag
                    model[i][j].minesAround = count
                }
            }
        }
    }

    private fun checkHorizRows(){
        for (i in 1..BOUND-1){ //exclude corners
            var count = 0
            //top row
            if (model[0][i].type == EMPTY_SPACE){
                if(model[0][i-1].type == BOMB) count+=1 // directly left
                if(model[1][i-1].type == BOMB) count+=1 // down left diag
                if(model[1][i].type == BOMB) count+=1 // right
                if(model[1][i+1].type == BOMB) count+=1 //down right diag
                if(model[0][i+1].type == BOMB) count+=1 //directly right
                model[0][i].minesAround = count
                count = 0
            }
            //bottom row
            if (model[BOUND][i].type == EMPTY_SPACE) {
                if (model[BOUND][i - 1].type == BOMB) count += 1 // directly left
                if (model[BOUND-1][i - 1].type == BOMB) count += 1 // down left diag
                if (model[BOUND-1][i].type == BOMB) count += 1 // right
                if (model[BOUND-1][i + 1].type == BOMB) count += 1 //down right diag
                if (model[BOUND][i + 1].type == BOMB) count += 1 //directly right
                model[BOUND][i].minesAround = count
            }
        }
    }
    private fun checkVertRows(){
        for (i in 1..BOUND-1){ //exclude corners
            var count = 0
            //left column
            if (model[i][0].type == EMPTY_SPACE){
                if(model[i-1][0].type == BOMB) count+=1 // directly above
                if(model[i-1][1].type == BOMB) count+=1 // up right diag
                if(model[i][1].type == BOMB) count+=1 // right
                if(model[i+1][1].type == BOMB) count+=1 //down right diag
                if(model[i+1][0].type == BOMB) count+=1 //directly below
            model[i][0].minesAround = count
            count = 0
            }
            //right col
            if (model[i][BOUND].type == EMPTY_SPACE){
                if(model[i-1][BOUND].type == BOMB) count+=1 // directly above
                if(model[i-1][BOUND-1].type == BOMB) count+=1 // left right diag
                if(model[i][BOUND-1].type == BOMB) count+=1 // right
                if(model[i+1][BOUND-1].type == BOMB) count+=1 //down left diag
                if(model[i+1][BOUND].type == BOMB) count+=1 //directly below
            model[i][BOUND].minesAround = count
            }
        }
    }
    private fun checkCorners(){
        if(model[0][0].type == EMPTY_SPACE) checkCornerNeighbor(0,0)
        if(model[0][BOUND].type == EMPTY_SPACE) checkCornerNeighbor(0,4)
        if(model[BOUND][0].type == EMPTY_SPACE) checkCornerNeighbor(4,0)
        if(model[BOUND][BOUND].type == EMPTY_SPACE) checkCornerNeighbor(4,4)
    }

    private fun checkCornerNeighbor(i:Int,j:Int){
        var count = 0
        if (i == 0 && j == 0){
            if (model[i][j+1].type == BOMB) count+=1
            if (model[i+1][j].type == BOMB) count+=1
            if (model[i+1][j+1].type == BOMB) count+=1
            model[i][j].minesAround = count
        }
        else if (i == 0 && j == BOUND){
            if (model[i][j-1].type == BOMB) count+=1
            if (model[i+1][j].type == BOMB) count+=1
            if (model[i+1][j-1].type == BOMB) count+=1
            model[i][j].minesAround = count
        }
        else if (i == BOUND && j == 0){
            if (model[i][j+1].type == BOMB) count+=1
            if (model[i-1][j].type == BOMB) count+=1
            if (model[i-1][j+1].type == BOMB) count+=1
            model[i][j].minesAround = count
        }
        else if (i == BOUND && j == BOUND){
            if (model[i][j-1].type == BOMB) count+=1
            if (model[i-1][j].type == BOMB) count+=1
            if (model[i-1][j-1].type == BOMB) count+=1
            model[i][j].minesAround = count
        }
    }

    public fun toggleFlag(){
        flagging = !flagging
    }

    fun setGameBoard(){
        populateMines()
        calculateMinesAround()
        modelToString()
    }
}