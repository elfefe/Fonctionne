package com.elfefe.fonctionne

import com.elfefe.fonctionne.layout.DrawPapplet
import com.elfefe.fonctionne.layout.FunctionLayout
import com.elfefe.fonctionne.layout.GraphLayout
import com.elfefe.fonctionne.layout.VariableLayout
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.*
import javafx.stage.Stage
import processing.core.PApplet


class Main {
    private var rootLayout: HBox
    private val leftLayout = VBox()
    private val rightLayout = VBox()

    var graphLayout: GraphLayout
    var functionLayout: FunctionLayout
    var variablesLayout: VariableLayout

    init {
        INSTANCE = this

        variablesLayout = VariableLayout()
        functionLayout = FunctionLayout()
        graphLayout = GraphLayout()

        rootLayout = initLayouts()
    }

    private fun initLayouts() = HBox().apply {
        background = backgroundColor(wall)

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
        lateinit var INSTANCE: Main

        @JvmStatic
        fun main(args: Array<String>) {
            val app = DrawPapplet()
        }
    }
}

/*
class Main : Application() {
    private var rootLayout: HBox
    private val leftLayout = VBox()
    private val rightLayout = VBox()

    var graphLayout: GraphLayout
    var functionLayout: FunctionLayout
    var variablesLayout: VariableLayout

    init {
        INSTANCE = this

        variablesLayout = VariableLayout()
        functionLayout = FunctionLayout()
        graphLayout = GraphLayout()

        rootLayout = initLayouts()
    }

    override fun start(primaryStage: Stage?) {
        primaryStage?.apply {
            scene = Scene(rootLayout, 1600.0, 900.0)
            title = TITLE
        }?.show()
    }

    private fun initLayouts() = HBox().apply {
        background = backgroundColor(wall)

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
        lateinit var INSTANCE: Main
    }
}*/
