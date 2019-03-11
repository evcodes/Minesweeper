package com.eddyvarela.hw1_minesweeper.minesweeperview


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.eddyvarela.hw1_minesweeper.MainActivity
import com.eddyvarela.hw1_minesweeper.R
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.BOMB
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.EMPTY_SPACE
import com.eddyvarela.hw1_minesweeper.minesweeper.Model.MinesweeperModel
import kotlinx.android.synthetic.main.activity_main.view.*

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
    }

    override fun onDraw(canvas: Canvas?) {
        drawGameBoard(canvas)
        drawIcons(canvas)
    }

    private fun drawGameBoard(canvas: Canvas?){
        // vertical lines
        val line1VertOrigin = (width / 5).toFloat()
        val line2VertOrigin = line1VertOrigin * 2
        val line3VertOrigin = line1VertOrigin * 3
        val line4VertOrigin = line1VertOrigin * 4

        //horizontal lines
        val line1HorizOrigin = (height/ 5).toFloat()
        val line2HorizOrigin = line1HorizOrigin * 2
        val line3HorizOrigin = line1HorizOrigin * 3
        val line4HorizOrigin = line1HorizOrigin * 4

        val height = height.toFloat()
        val width = width.toFloat()

        canvas?.drawRect(0f, 0f, width, height, paintBackground)

        //vertical lines
        canvas?.drawLine(line1VertOrigin, 0f, line1VertOrigin, height, paintLine)
        canvas?.drawLine(line2VertOrigin, 0f, line2VertOrigin, height, paintLine)
        canvas?.drawLine(line3VertOrigin, 0f, line3VertOrigin, height, paintLine)
        canvas?.drawLine(line4VertOrigin, 0f, line4VertOrigin, height, paintLine)

        //horizontal lines
        canvas?.drawLine(0f, line1HorizOrigin, width, line1HorizOrigin, paintLine)
        canvas?.drawLine(0f, line2HorizOrigin, width, line2HorizOrigin, paintLine)
        canvas?.drawLine(0f, line3HorizOrigin, width, line3HorizOrigin, paintLine)
        canvas?.drawLine(0f, line4HorizOrigin, width, line4HorizOrigin, paintLine)
    }

    private fun drawIcons(canvas: Canvas?){

        var leftPos = width/5f
        var topPos = height/5f
        for (i in 0..4){
            for (j in 0..4){
                if(gameBoard.model[i][j].type == BOMB){
                    canvas?.drawBitmap(bombIcon, leftPos*j, topPos*i, null)
                }
                else if(gameBoard.model[i][j].type == EMPTY_SPACE){
                    var minesAround = gameBoard.model[i][j].minesAround
                    if(minesAround == 1){
                        canvas?.drawBitmap(oneIcon, leftPos*j, topPos*i, null)
                    }else if(minesAround == 2){
                        canvas?.drawBitmap(twoIcon, leftPos*j, topPos*i, null)
                    }else if(minesAround == 3) {
                        canvas?.drawBitmap(threeIcon, leftPos * j, topPos * i, null)
                    }else if(minesAround == 4) {
                        canvas?.drawBitmap(fourIcon, leftPos * j, topPos * i, null)
                    }else if(minesAround == 5) {
                        canvas?.drawBitmap(fiveIcon, leftPos * j, topPos * i, null)
                    }else if(minesAround == 6) {
                        canvas?.drawBitmap(sixIcon, leftPos * j, topPos * i, null)
                    }else if(minesAround == 7) {
                        canvas?.drawBitmap(sevenIcon, leftPos * j, topPos * i, null)
                    }else if(minesAround == 8) {
                        canvas?.drawBitmap(eightIcon, leftPos * j, topPos * i, null)
                    }
                }
                if (!gameBoard.model[i][j].wasClicked) {
                    canvas?.drawBitmap(untouched, leftPos * j, topPos * i, null)
                }
            }
        }
    }
}


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        if (event?.action == MotionEvent.ACTION_DOWN) {
//            val tX = event.x.toInt() / (width / 5)
//            val tY = event.y.toInt() / (height / 5)
//
//            if (tX < 5 && tY < 5 && MinesweeperModel.getFieldContent(tX, tY) ==
//                MinesweeperModel.EMPTY) {
//                MinesweeperModel.setFieldContent(tX, tY, Minesweeper.nextPlayer)
//                MinesweeperModel.switchPlayer()
//                invalidate()
//
//                (context as MainActivity).setStatusText(status)
//            }
//        }
//

//        return true
//    }

//


//    private fun drawMove(canvas: Canvas) {
//        for (i in 0..5) {
//            for (j in 0..5) {
//                if (MinesweeperModel.getFieldContent(i,j) == MinesweeperModel.CIRCLE) {
//                    val centerX = (i * width / 3 + width / 6).toFloat()
//                    val centerY = (j * height / 3 + height / 6).toFloat()
//                    val radius = height / 6 - 2
//
//                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
//                } else if (MinesweeperModel.getFieldContent(i, j) == MinesweeperModel.CROSS) {
//                    canvas.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
//                        ((i + 1) * width / 3).toFloat(),
//                        ((j + 1) * height / 3).toFloat(), paintLine)
//
//                    canvas.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
//                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine)
//                }
//            }
//        }
//    }


