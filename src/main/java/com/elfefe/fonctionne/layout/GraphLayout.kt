package com.elfefe.fonctionne.layout

import com.elfefe.fonctionne.*
import com.elfefe.fonctionne.mathengine.Function
import javafx.geometry.Insets
import javafx.geometry.Point3D
import javafx.scene.canvas.Canvas
import javafx.scene.layout.*
import javafx.scene.paint.Color
import java.lang.IllegalArgumentException

class GraphLayout : HBox() {
    companion object {
        const val HORIZONTAL_PADDING = 3.0
        const val VERTICAL_PADDING = 3.0
    }

    private val main = Main.INSTANCE
    private val drawProperty: DrawProperty = DrawProperty()
    private var canvas: Canvas

    init {
        background = backgroundColor(Color.TRANSPARENT)

        padding = Insets(VERTICAL_PADDING, HORIZONTAL_PADDING, VERTICAL_PADDING, HORIZONTAL_PADDING)


        canvas = Canvas(width - (HORIZONTAL_PADDING * 2), height - (VERTICAL_PADDING * 2)).apply {
            background = backgroundColor(primary)
            style = "-fx-border-radius: 10 10 10 10;"
            border = Border(BorderStroke(borders, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))
            isManaged = false

            widthProperty().bind(this@GraphLayout.widthProperty())
            heightProperty().bind(this@GraphLayout.heightProperty())

            setOnMouseMoved {
                drawProperty.mouseX = it.x
                drawProperty.mouseY = it.y
                drawIndicators()
            }
            setOnMouseEntered {
                drawProperty.mouseHover = true
            }
            setOnMouseExited {
                drawProperty.mouseHover = false
            }
        }

        widthProperty().addListener { _, _, width ->
//            canvas.width = width as Double - (HORIZONTAL_PADDING * 2)
//            canvas.maxWidth(width - (HORIZONTAL_PADDING * 2))
            drawIndicators()
        }

        heightProperty().addListener { _, _, height ->
//            canvas.height = height as Double - (VERTICAL_PADDING * 2)
//            canvas.maxHeight(height - (VERTICAL_PADDING * 2))
            drawIndicators()
        }

        drawIndicators()

        main.variablesLayout.run {
            yPos.addListener { _, _, value ->
                drawProperty.verticalIndicatorPosition = value as Double
                drawIndicators()
            }
            xPos.addListener { _, _, value ->
                drawProperty.horizontalIndicatorPosition = value as Double
                drawIndicators()
            }
            xGap.addListener { _, _, value ->
                drawProperty.horizontalStepsGap = value as Double
                drawIndicators()
            }
            yGap.addListener { _, _, value ->
                drawProperty.verticalStepsGap = value as Double
                drawIndicators()
            }
        }

        main.functionLayout.currentFunction.addListener { _, _, value ->
            drawProperty.function = Function(value, "x")
            drawIndicators()
        }

        children.add(canvas)
    }

    private fun drawIndicators() {
        canvas.graphicsContext2D.apply {
            clearRect(0.0, 0.0, canvas.width, canvas.height)
            beginPath()
            stroke = white

            /*** Vertical indicator */
            moveTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition, 0.0 + drawProperty.horizontalIndicatorPosition)
            lineTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition, canvas.height + drawProperty.horizontalIndicatorPosition)

            /*** Horizontal indicator */
            moveTo(0.0 + drawProperty.verticalIndicatorPosition, canvas.height / 2 + drawProperty.horizontalIndicatorPosition)
            lineTo(canvas.width + drawProperty.verticalIndicatorPosition, canvas.height / 2 + drawProperty.horizontalIndicatorPosition)

            /*** Vertical indicator steps */
            val centerY = canvas.height / 2

            centerY.until(canvas.height, drawProperty.verticalStepsGap) {
                moveTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition - drawProperty.verticalStepsHalfLength, it)
                lineTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition + drawProperty.verticalStepsHalfLength, it)
            }

            centerY.until(-canvas.height, -drawProperty.verticalStepsGap) {
                moveTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition - drawProperty.verticalStepsHalfLength, it)
                lineTo(canvas.width / 2 + drawProperty.verticalIndicatorPosition + drawProperty.verticalStepsHalfLength, it)
            }

            /*** Horizontal indicator steps */
            val centerX = canvas.width / 2

            centerX.until(canvas.width, drawProperty.horizontalStepsGap) {
                moveTo(it, canvas.height / 2 + drawProperty.horizontalIndicatorPosition - drawProperty.horizontalStepsHalfLength)
                lineTo(it, canvas.height / 2 + drawProperty.horizontalIndicatorPosition + drawProperty.horizontalStepsHalfLength)
            }

            centerX.until(-canvas.width, -drawProperty.horizontalStepsGap) {
                moveTo(it, canvas.height / 2 + drawProperty.horizontalIndicatorPosition - drawProperty.horizontalStepsHalfLength)
                lineTo(it, canvas.height / 2 + drawProperty.horizontalIndicatorPosition + drawProperty.horizontalStepsHalfLength)
            }

            stroke()
            closePath()

            drawShape()
            if (drawProperty.mouseHover) drawMouseIntersection()
        }
    }

    private fun drawShape() {
        canvas.graphicsContext2D.apply {
            try {
                beginPath()
                stroke = Color.AQUAMARINE

                val baseX = - canvas.width / 2
                val baseY = (canvas.height / 2) - drawProperty.function.evaluateAt(baseX)
                val steps = 1.0
                moveTo(baseX, baseY)
                baseX.until(canvas.width / 2, steps) { x ->
                    val y = (canvas.height / 2) - drawProperty.function.evaluateAt(x) + drawProperty.verticalIndicatorPosition
                    lineTo((x + canvas.width / 2 + drawProperty.horizontalIndicatorPosition), y)
                }
                stroke()
                closePath()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: StringIndexOutOfBoundsException) {
                println(e.message)
            }
        }
    }

    private fun drawMouseIntersection() {
        canvas.graphicsContext2D.apply {
            fill = functionCursor
            fillOval(drawProperty.mouseX, drawProperty.mouseY, 5.0, 5.0)
        }
    }

    private data class DrawProperty(
            var verticalIndicatorPosition: Double = VariableLayout.DEFAULT_POS.toDouble(),
            var horizontalIndicatorPosition: Double = VariableLayout.DEFAULT_POS.toDouble(),
            var verticalStepsGap: Double = VariableLayout.DEFAULT_GAP.toDouble(),
            var horizontalStepsGap: Double = VariableLayout.DEFAULT_GAP.toDouble(),
            var verticalStepsHalfLength: Double = 2.0,
            var horizontalStepsHalfLength: Double = 2.0,
            var mouseHover: Boolean = false,
            var mouseX: Double = 0.0,
            var mouseY: Double = 0.0,
            var horizontalMouseIntersection: Double = 0.0,
            var verticalMouseIntersection: Double = 0.0,
            var function: Function = Function("x")
    )
}