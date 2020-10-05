package com.elfefe.fonctionne

import com.elfefe.fonctionne.layout.FunctionLayout
import com.elfefe.fonctionne.layout.GraphLayout
import com.elfefe.fonctionne.layout.VariableLayout
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.stage.Stage


class Main : Application() {
    private val rootLayout by lazy { initLayouts() }
    private val leftLayout = VBox()
    private val rightLayout = VBox()

    val graphLayout by lazy { GraphLayout() }
    val functionLayout by lazy { FunctionLayout() }
    val variablesLayout by lazy { VariableLayout() }

    init {
        INSTANCE = this
    }

    override fun start(primaryStage: Stage?) {
        primaryStage?.apply {
            scene = Scene(rootLayout)
            title = TITLE
        }?.show()
    }

    private fun initLayouts() = HBox().apply {
        background = backgroundColor(Color.BLACK)

        children.addAll(
                leftLayout,
                rightLayout
        )

        responsiveSize(leftLayout, 1.25)
        responsiveSize(rightLayout, 5.0)

        leftLayout.apply {
            children.addAll(
                    graphLayout,
                    functionLayout
            )

            responsiveSize(graphLayout, heightDivider = 1.1)
            responsiveSize(functionLayout, heightDivider = 10.0)
        }

        rightLayout.apply {
            children.addAll(
                    variablesLayout
            )

            responsiveSize(variablesLayout)
        }
    }

    companion object {
        const val TITLE = "Fonctionne"
        lateinit var INSTANCE: Application
    }
}