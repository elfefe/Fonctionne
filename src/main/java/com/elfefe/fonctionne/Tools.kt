package com.elfefe.fonctionne

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.Region
import javafx.scene.paint.Paint


fun backgroundColor(color: Paint) = Background(BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))
fun Region.responsiveSize(child: Region, widthDivider: Double = 1.0, heightDivider: Double = 1.0) {
    widthProperty().addListener { _,_,width ->
        val w = (width as Double) / widthDivider
        child.prefWidth = w
        child.maxWidth = w
    }
    heightProperty().addListener { _,_,height ->
        val h = (height as Double) / heightDivider
        child.prefHeight =  h
        child.maxHeight = h
    }
}