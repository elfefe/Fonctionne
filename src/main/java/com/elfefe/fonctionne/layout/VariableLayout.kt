package com.elfefe.fonctionne.layout

import com.elfefe.fonctionne.backgroundColor
import javafx.beans.Observable
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.value.ObservableDoubleValue
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import java.util.function.UnaryOperator

class VariableLayout : VBox() {
    private val xGap = SimpleDoubleProperty(DEFAULT_X_GAP.toDouble())

    init {
        background = backgroundColor(Color.GREEN)
        val options = ScrollPane().apply {
            vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
            hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

            getChildren().add(xGap())
        }

        children.add(options)
    }

    fun xGap() = VBox().apply {
        background = backgroundColor(Color.ORANGE)
        alignment = Pos.TOP_CENTER

        val label = Label(X_GAP_LABEL).apply {
            style = "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;"
        }
        val entry = TextField(DEFAULT_X_GAP).apply {
            textFormatter = TextFormatter<String>(
                    UnaryOperator<TextFormatter.Change?> { change ->
                        return@UnaryOperator change?.run {
                            if (change.text.matches("/^[0-9]+.[0-9]+\$".toRegex())) return@run change
                            return@run null
                        }
                    }
            )

            textProperty().addListener { _, _, text ->
                xGap.value = text.toDouble()
            }
        }

        children.addAll(label, entry)
    }

    companion object {
        const val X_GAP_LABEL = "x gap"
        const val DEFAULT_X_GAP = "1"
    }
}