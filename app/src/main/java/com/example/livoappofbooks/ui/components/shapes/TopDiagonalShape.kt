package com.example.livoappofbooks.ui.components.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TopDiagonalShape(
    private val diagonalHeight: Float = 80f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, diagonalHeight)       // canto superior esquerdo (mais baixo)
            lineTo(size.width, 0f)          // canto superior direito (mais alto)
            lineTo(size.width, size.height) // canto inferior direito
            lineTo(0f, size.height)         // canto inferior esquerdo
            close()
        }
        return Outline.Generic(path)
    }
}
