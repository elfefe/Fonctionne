package com.elfefe.fonctionne.layout

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import processing.core.PApplet
import processing.javafx.PSurfaceFX

class DrawPapplet: PApplet() {
    var fxSurface: PSurfaceFX
    var canvas: Canvas
    var stackpane: StackPane
    var scene: Scene
    var stage: Stage

    init {
        launch()

        fxSurface = surface as PSurfaceFX
        canvas = fxSurface.native as Canvas
        stackpane = canvas.parent as StackPane
        stage = canvas.scene.window as Stage

        scene = Scene(Group(canvas))
        stage.scene = scene

        canvas.widthProperty().unbind()
        canvas.heightProperty().unbind()
    }

    private fun launch() {
        main(javaClass.simpleName)
    }

    override fun draw() {
        background(0)
        ellipse(mouseX.toFloat(), mouseY.toFloat(), 20f, 20f)
    }

    override fun settings() {
        size(800, 600, FX2D)
    }
}