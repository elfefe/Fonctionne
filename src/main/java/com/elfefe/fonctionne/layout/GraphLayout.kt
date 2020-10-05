package com.elfefe.fonctionne.layout

import com.elfefe.fonctionne.backgroundColor
import javafx.scene.canvas.Canvas
import javafx.scene.layout.HBox
import javafx.scene.paint.Color

class GraphLayout: HBox() {
init {
    background = backgroundColor(Color.RED)

    val canvas = Canvas(width, height)

    widthProperty().addListener { _,_,width ->
        canvas.width = width as Double
        canvas.maxWidth(width)
        drawIndicators(canvas)
    }

    heightProperty().addListener { _,_,height ->
        canvas.height = height as Double
        canvas.maxHeight(height)
        drawIndicators(canvas)
    }

    drawIndicators(canvas)

    children.add(canvas)
}

    private fun drawIndicators(canvas: Canvas) {
        canvas.graphicsContext2D.apply {
            clearRect(0.0, 0.0, canvas.width, canvas.height)
            beginPath()
            moveTo(canvas.width / 2, 0.0)
            lineTo(canvas.width / 2, canvas.height)
            moveTo(0.0, canvas.height / 2)
            lineTo(canvas.width, canvas.height / 2)
            stroke()
            closePath()
        }
    }
}