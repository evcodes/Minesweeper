package com.eddyvarela.hw1_minesweeper.minesweeperview


import android.content.Context
import android.graphics.*
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.eddyvarela.hw1_minesweeper.R
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.BOMB
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.EMPTY_SPACE
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.MinesweeperModel

class MinesweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()

    private var xCoord: Float = -1f
    private var yCoord: Float = -1f

    var gameBoard = MinesweeperModel

    private var untouched = BitmapFactory.decodeResource(resources,R.drawable.untouched)
    private var bombIcon = BitmapFactory.decodeResource(resources, R.drawable.bomb)
    private var flagIcon = BitmapFactory.decodeResource(resources, R.drawable.flag)
    private var empty = BitmapFactory.decodeResource(resources, R.drawable.empty)
    private var oneIcon = BitmapFactory.decodeResource(resources, R.drawable.one)
    private var twoIcon = BitmapFactory.decodeResource(resources, R.drawable.two)
    private var threeIcon = BitmapFactory.decodeResource(resources, R.drawable.three)
    private var fourIcon = BitmapFactory.decodeResource(resources, R.drawable.four)
    private var fiveIcon = BitmapFactory.decodeResource(resources, R.drawable.five)
    private var sixIcon = BitmapFactory.decodeResource(resources, R.drawable.six)
    private var sevenIcon = BitmapFactory.decodeResource(resources, R.drawable.seven)
    private var eightIcon = BitmapFactory.decodeResource(resources, R.drawable.eight)

    init {
        paintBackground.color = Color.GRAY
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 8f
        gameBoard.setGameBoard()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        untouched = Bitmap.createScaledBitmap(untouched,
            width/5, height/5, false)
        bombIcon = Bitmap.createScaledBitmap(bombIcon,
            width/5, height/5, false)
        oneIcon = Bitmap.createScaledBitmap(oneIcon,
            width/5, height/5, false)
        twoIcon = Bitmap.createScaledBitmap(twoIcon,
            width/5, height/5, false)
        threeIcon = Bitmap.createScaledBitmap(threeIcon,
            width/5, height/5, false)
        fourIcon = Bitmap.createScaledBitmap(fourIcon,
            width/5, height/5, false)
        fiveIcon = Bitmap.createScaledBitmap(fiveIcon,
            width/5, height/5, false)
        sixIcon = Bitmap.createScaledBitmap(sixIcon,
            width/5, height/5, false)
        sevenIcon = Bitmap.createScaledBitmap(sevenIcon,
            width/5, height/5, false)
        eightIcon = Bitmap.createScaledBitmap(eightIcon,
            width/5, height/5, false)
        empty = Bitmap.createScaledBitmap(empty,
            width/5, height/5, false)

        flagIcon = Bitmap.createScaledBitmap(flagIcon,
            height/5, width/5, false)
    }

    override fun onDraw(canvas: Canvas?) {
        drawGameBoard(canvas)
        drawIcons(canvas)
    }

    private fun drawGameBoard(canvas: Canvas?){
        // vertical lines
        val vertOrigin = (width / 5).toFloat()

        //horizontal lines
        val horizOrigin = (height/ 5).toFloat()

        val height = height.toFloat()
        val width = width.toFloat()

        canvas?.drawRect(0f, 0f, width, height, paintBackground)

        //vertical lines
        canvas?.drawLine(vertOrigin, 0f, vertOrigin, height, paintLine)
        canvas?.drawLine(vertOrigin*2, 0f, vertOrigin*2, height, paintLine)
        canvas?.drawLine(vertOrigin*3, 0f, vertOrigin*3, height, paintLine)
        canvas?.drawLine(vertOrigin*4, 0f, vertOrigin*4, height, paintLine)

        //horizontal lines
        canvas?.drawLine(0f, horizOrigin, width, horizOrigin, paintLine)
        canvas?.drawLine(0f, horizOrigin*2, width, horizOrigin*2, paintLine)
        canvas?.drawLine(0f, horizOrigin*3, width, horizOrigin*3, paintLine)
        canvas?.drawLine(0f, horizOrigin*4, width, horizOrigin*4, paintLine)
    }

    private fun drawIcons(canvas: Canvas?){

        var leftPos = width/5f
        var topPos = height/5f
        for (i in 0..4){
            for (j in 0..4){

                if (gameBoard.model[i][j].wasClicked){
                    if(gameBoard.model[i][j].type == BOMB){
                        canvas?.drawBitmap(bombIcon, leftPos*j, topPos*i, null)
                    }else if (gameBoard.model[i][j].type == EMPTY_SPACE){
                        var minesAround = gameBoard.model[i][j].minesAround
                        when (minesAround) {
                            1 -> canvas?.drawBitmap(oneIcon, leftPos*j, topPos*i, null)
                            2 -> canvas?.drawBitmap(twoIcon, leftPos*j, topPos*i, null)
                            3 -> canvas?.drawBitmap(threeIcon, leftPos * j, topPos * i, null)
                            4 -> canvas?.drawBitmap(fourIcon, leftPos * j, topPos * i, null)
                            5 -> canvas?.drawBitmap(fiveIcon, leftPos * j, topPos * i, null)
                            6 -> canvas?.drawBitmap(sixIcon, leftPos * j, topPos * i, null)
                            7 -> canvas?.drawBitmap(sevenIcon, leftPos * j, topPos * i, null)
                            8 -> canvas?.drawBitmap(eightIcon, leftPos * j, topPos * i, null)
                        }
                    }
                }
                else if (gameBoard.model[i][j].isFlagged){
                    canvas?.drawBitmap(flagIcon, leftPos*j, topPos*i, null)
                }
                else if(!gameBoard.model[i][j].wasClicked ){
                    canvas?.drawBitmap(untouched, leftPos*j , topPos*i, null)
                }
            }
        }
    }

    override fun onTouchEvent (event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)
            if ((tX <5 && tY<5) && !gameBoard.gameWon && !gameBoard.gameLost){
                if (!gameBoard.isMovePossible(tY, tX, gameBoard.flagging)){

                    Snackbar.make(this,"Sorry, you lost. Try again later noob", Snackbar.LENGTH_INDEFINITE).show()
                    gameBoard.gameLost = true
                }
            }
            invalidate()
        }
        return true
    }
}

