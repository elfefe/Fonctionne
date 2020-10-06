package com.elfefe.fonctionne.layout

import com.elfefe.fonctionne.*
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.control.TitledPane
import javafx.scene.layout.*
import javafx.scene.paint.Color

class VariableLayout : VBox() {
    companion object {
        const val X_GAP_LABEL = "   X gap"
        const val Y_GAP_LABEL = "   Y gap"
        const val X_POS_LABEL = "   X pos"
        const val Y_POS_LABEL = "   Y pos"
        const val EXAMPLES = "   Examples"
        const val DEFAULT_GAP = "5"
        const val DEFAULT_POS = "0"
    }
    private val main = Main.INSTANCE

    val xGap = SimpleDoubleProperty(DEFAULT_GAP.toDouble())
    val yGap = SimpleDoubleProperty(DEFAULT_GAP.toDouble())
    val xPos = SimpleDoubleProperty(DEFAULT_POS.toDouble())
    val yPos = SimpleDoubleProperty(DEFAULT_POS.toDouble())

    init {
        background = backgroundColor(Color.TRANSPARENT)

        padding = Insets(0.0, 0.0, 0.0, 5.0)

        val options = ScrollPane().apply {
            background = backgroundColor(primary)

            border = Border(BorderStroke(borders, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))

            style = "-fx-background: rgb(${primary.red*255},${primary.green*255},${primary.blue*255});" +
                    "-fx-border-color: rgb(${primary.red*255},${primary.green*255},${primary.blue*255});"

            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

            getChildren().addAll(
                    xPos(),
                    yPos(),
                    xGap(),
                    yGap(),
                    examples()
            )
        }

        children.add(options)

        responsiveSize(options)
    }

    private fun xPos() = Item(X_POS_LABEL).apply {
        entry.text = DEFAULT_POS
        entry.textProperty().addListener { _, _, value ->
            xPos.value = if (value.isEmpty()) 0.0 else value.toDouble()
        }
    }

    private fun yPos() = Item(Y_POS_LABEL).apply {
        entry.text = DEFAULT_POS
        entry.textProperty().addListener { _, _, value ->
            xPos.value = if (value.isEmpty()) 0.0 else value.toDouble()
        }
    }

    private fun xGap() = Item(X_GAP_LABEL).apply {
        entry.text = DEFAULT_GAP
        entry.textProperty().addListener { _, _, value ->
            if (value.isEmpty()) entry.text = "1"
            else if (value.toDouble() < 1) entry.text = "1"
            xGap.value = value.toDouble()
        }
    }

    private fun yGap() = Item(Y_GAP_LABEL).apply {
        entry.text = DEFAULT_GAP
        entry.textProperty().addListener { _, _, value ->
            if (value.isEmpty()) entry.text = "1"
            else if (value.toDouble() < 1) entry.text = "1"
            yGap.value = value.toDouble()
            yGap.value.until(0.0) {}
        }
    }

    private fun examples() = TitledPane(EXAMPLES, VBox().apply {
        val sinusoid = Button("Sinusoid")
        sinusoid.setOnMouseClicked {
            main.functionLayout.currentFunction.value = FunctionLayout.SINUSOID
        }
        children.addAll(sinusoid)
    }).apply {
        padding = Insets(10.0, 8.0, 10.0, 8.0)
    }

    class Item(title: String, formatter: Formatter = Formatter.DOUBLE): TitledPane() {
        val entry = TextField().apply {
            when(formatter) {
                Formatter.DOUBLE -> doubleFormater()
            }
        }

        init {
            text = title

            background = backgroundColor(primary)
            alignment = Pos.TOP_CENTER

            padding = Insets(10.0, 8.0, 10.0, 8.0)

            content = entry
        }
    }
}