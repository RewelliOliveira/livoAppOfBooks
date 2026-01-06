package com.example.livoappofbooks.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.PathBuilder

val Arrow_forward_ios_new: ImageVector
    get() {
        if (_Arrow_forward_ios_new != null) return _Arrow_forward_ios_new!!
        _Arrow_forward_ios_new = ImageVector.Builder(
            name = "Arrow_forward_ios_new",
            defaultWidth = 30.dp,
            defaultHeight = 30.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(320f, 80f)
                lineTo(720f, 480f)
                lineTo(320f, 880f)
                lineToRelative(-71f, -71f)
                lineToRelative(329f, -329f)
                lineToRelative(-329f, -329f)
                close()
            }
        }.build()
        return _Arrow_forward_ios_new!!
    }

val Arrow_back_ios_new: ImageVector
    get() {
        if (_Arrow_back_ios_new != null) return _Arrow_back_ios_new!!

        _Arrow_back_ios_new = ImageVector.Builder(
            name = "Arrow_back_ios_new",
            defaultWidth = 30.dp,
            defaultHeight = 30.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(640f, 880f)
                lineTo(240f, 480f)
                lineToRelative(400f, -400f)
                lineToRelative(71f, 71f)
                lineToRelative(-329f, 329f)
                lineToRelative(329f, 329f)
                close()
            }
        }.build()

        return _Arrow_back_ios_new!!
    }
val Send: ImageVector
    get() {
        if (_Send != null) return _Send!!

        _Send = ImageVector.Builder(
            name = "Send",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.White)
            ) {
                moveTo(1f, 1.91f)
                lineTo(1.78f, 1.5f)
                lineTo(15f, 7.44899f)
                verticalLineTo(8.3999f)
                lineTo(1.78f, 14.33f)
                lineTo(1f, 13.91f)
                lineTo(2.58311f, 8f)
                lineTo(1f, 1.91f)
                close()
                moveTo(3.6118f, 8.5f)
                lineTo(2.33037f, 13.1295f)
                lineTo(13.5f, 7.8999f)
                lineTo(2.33037f, 2.83859f)
                lineTo(3.6118f, 7.43874f)
                lineTo(9f, 7.5f)
                verticalLineTo(8.5f)
                horizontalLineTo(3.6118f)
                close()
            }
        }.build()

        return _Send!!
    }

private var _Send: ImageVector? = null


val BuildingLibrary: ImageVector
    get() {
        if (_BuildingLibrary != null) return _BuildingLibrary!!

        _BuildingLibrary = ImageVector.Builder(
            name = "BuildingLibrary",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 21f)
                verticalLineTo(12.75f)
                moveTo(15.75f, 21f)
                verticalLineTo(12.75f)
                moveTo(8.25f, 21f)
                verticalLineTo(12.75f)
                moveTo(3f, 9f)
                lineTo(12f, 3f)
                lineTo(21f, 9f)
                moveTo(19.5f, 21f)
                verticalLineTo(10.3325f)
                curveTo(17.0563f, 9.94906f, 14.5514f, 9.75f, 12f, 9.75f)
                curveTo(9.44861f, 9.75f, 6.94372f, 9.94906f, 4.5f, 10.3325f)
                verticalLineTo(21f)
                moveTo(3f, 21f)
                horizontalLineTo(21f)
                moveTo(12f, 6.75f)
                horizontalLineTo(12.0075f)
                verticalLineTo(6.7575f)
                horizontalLineTo(12f)
                verticalLineTo(6.75f)
                close()
            }
        }.build()

        return _BuildingLibrary!!
    }

val Close_small: ImageVector
    get() {
        if (_closeSmall != null) return _closeSmall!!

        _closeSmall = ImageVector.Builder(
            name = "Close_small",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(19f, 6.41f)
                lineTo(17.59f, 5f)
                lineTo(12f, 10.59f)
                lineTo(6.41f, 5f)
                lineTo(5f, 6.41f)
                lineTo(10.59f, 12f)
                lineTo(5f, 17.59f)
                lineTo(6.41f, 19f)
                lineTo(12f, 13.41f)
                lineTo(17.59f, 19f)
                lineTo(19f, 17.59f)
                lineTo(13.41f, 12f)
                close()
            }
        }.build()

        return _closeSmall!!
    }

private var _closeSmall: ImageVector? = null

val BookOpen: ImageVector
    get() {
        if (_BookOpen != null) return _BookOpen!!

        _BookOpen = ImageVector.Builder(
            name = "BookOpen",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 6.04168f)
                curveTo(10.4077f, 4.61656f, 8.30506f, 3.75f, 6f, 3.75f)
                curveTo(4.94809f, 3.75f, 3.93834f, 3.93046f, 3f, 4.26212f)
                verticalLineTo(18.5121f)
                curveTo(3.93834f, 18.1805f, 4.94809f, 18f, 6f, 18f)
                curveTo(8.30506f, 18f, 10.4077f, 18.8666f, 12f, 20.2917f)
                moveTo(12f, 6.04168f)
                curveTo(13.5923f, 4.61656f, 15.6949f, 3.75f, 18f, 3.75f)
                curveTo(19.0519f, 3.75f, 20.0617f, 3.93046f, 21f, 4.26212f)
                verticalLineTo(18.5121f)
                curveTo(20.0617f, 18.1805f, 19.0519f, 18f, 18f, 18f)
                curveTo(15.6949f, 18f, 13.5923f, 18.8666f, 12f, 20.2917f)
                moveTo(12f, 6.04168f)
                verticalLineTo(20.2917f)
            }
        }.build()

        return _BookOpen!!
    }

val CalendarDays: ImageVector
    get() {
        if (_CalendarDays != null) return _CalendarDays!!

        _CalendarDays = ImageVector.Builder(
            name = "CalendarDays",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6.75f, 3f)
                verticalLineTo(5.25f)
                moveTo(17.25f, 3f)
                verticalLineTo(5.25f)
                moveTo(3f, 18.75f)
                verticalLineTo(7.5f)
                curveTo(3f, 6.25736f, 4.00736f, 5.25f, 5.25f, 5.25f)
                horizontalLineTo(18.75f)
                curveTo(19.9926f, 5.25f, 21f, 6.25736f, 21f, 7.5f)
                verticalLineTo(18.75f)
                moveTo(3f, 18.75f)
                curveTo(3f, 19.9926f, 4.00736f, 21f, 5.25f, 21f)
                horizontalLineTo(18.75f)
                curveTo(19.9926f, 21f, 21f, 19.9926f, 21f, 18.75f)
                moveTo(3f, 18.75f)
                verticalLineTo(11.25f)
                curveTo(3f, 10.0074f, 4.00736f, 9f, 5.25f, 9f)
                horizontalLineTo(18.75f)
                curveTo(19.9926f, 9f, 21f, 10.0074f, 21f, 11.25f)
                verticalLineTo(18.75f)
                moveTo(12f, 12.75f)
                horizontalLineTo(12.0075f)
                verticalLineTo(12.7575f)
                horizontalLineTo(12f)
                verticalLineTo(12.75f)
                close()
                moveTo(12f, 15f)
                horizontalLineTo(12.0075f)
                verticalLineTo(15.0075f)
                horizontalLineTo(12f)
                verticalLineTo(15f)
                close()
                moveTo(12f, 17.25f)
                horizontalLineTo(12.0075f)
                verticalLineTo(17.2575f)
                horizontalLineTo(12f)
                verticalLineTo(17.25f)
                close()
                moveTo(9.75f, 15f)
                horizontalLineTo(9.7575f)
                verticalLineTo(15.0075f)
                horizontalLineTo(9.75f)
                verticalLineTo(15f)
                close()
                moveTo(9.75f, 17.25f)
                horizontalLineTo(9.7575f)
                verticalLineTo(17.2575f)
                horizontalLineTo(9.75f)
                verticalLineTo(17.25f)
                close()
                moveTo(7.5f, 15f)
                horizontalLineTo(7.5075f)
                verticalLineTo(15.0075f)
                horizontalLineTo(7.5f)
                verticalLineTo(15f)
                close()
                moveTo(7.5f, 17.25f)
                horizontalLineTo(7.5075f)
                verticalLineTo(17.2575f)
                horizontalLineTo(7.5f)
                verticalLineTo(17.25f)
                close()
                moveTo(14.25f, 12.75f)
                horizontalLineTo(14.2575f)
                verticalLineTo(12.7575f)
                horizontalLineTo(14.25f)
                verticalLineTo(12.75f)
                close()
                moveTo(14.25f, 15f)
                horizontalLineTo(14.2575f)
                verticalLineTo(15.0075f)
                horizontalLineTo(14.25f)
                verticalLineTo(15f)
                close()
                moveTo(14.25f, 17.25f)
                horizontalLineTo(14.2575f)
                verticalLineTo(17.2575f)
                horizontalLineTo(14.25f)
                verticalLineTo(17.25f)
                close()
                moveTo(16.5f, 12.75f)
                horizontalLineTo(16.5075f)
                verticalLineTo(12.7575f)
                horizontalLineTo(16.5f)
                verticalLineTo(12.75f)
                close()
                moveTo(16.5f, 15f)
                horizontalLineTo(16.5075f)
                verticalLineTo(15.0075f)
                horizontalLineTo(16.5f)
                verticalLineTo(15f)
                close()
            }
        }.build()

        return _CalendarDays!!
    }

val Language: ImageVector
    get() {
        if (_Language != null) return _Language!!

        _Language = ImageVector.Builder(
            name = "Language",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(10.5f, 21f)
                lineTo(15.75f, 9.75f)
                lineTo(21f, 21f)
                moveTo(12f, 18f)
                horizontalLineTo(19.5f)
                moveTo(3f, 5.62136f)
                curveTo(4.96557f, 5.37626f, 6.96804f, 5.25f, 9f, 5.25f)
                moveTo(9f, 5.25f)
                curveTo(10.1208f, 5.25f, 11.2326f, 5.28841f, 12.3343f, 5.364f)
                moveTo(9f, 5.25f)
                verticalLineTo(3f)
                moveTo(12.3343f, 5.364f)
                curveTo(11.1763f, 10.6578f, 7.68868f, 15.0801f, 3f, 17.5023f)
                moveTo(12.3343f, 5.364f)
                curveTo(13.2298f, 5.42545f, 14.1186f, 5.51146f, 15f, 5.62136f)
                moveTo(10.4113f, 14.1162f)
                curveTo(8.78554f, 12.4619f, 7.47704f, 10.4949f, 6.58432f, 8.31366f)
            }
        }.build()

        return _Language!!
    }

val BookmarkFilled: ImageVector
    get() {
        if (_BookmarkFilled != null) return _BookmarkFilled!!
        _BookmarkFilled = ImageVector.Builder(
            name = "Bookmark",
            defaultWidth = 45.dp,
            defaultHeight = 78.dp,
            viewportWidth = 45f,
            viewportHeight = 78f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFDFBED))
            ) {
                moveTo(0f, 22.4896f)
                verticalLineTo(70.5443f)
                curveTo(0f, 74.3074f, 1.54875f, 76.2148f, 3.52174f, 76.7998f)
                curveTo(10.6555f, 78.9148f, 14.5593f, 65.1601f, 22f, 65.1601f)
                curveTo(29.5712f, 65.1601f, 34.0831f, 78.4818f, 41.4052f, 76.5557f)
                curveTo(43.4305f, 76.023f, 45f, 74.2222f, 45f, 70.5443f)
                verticalLineTo(22.4896f)
                curveTo(45f, 10.0632f, 34.9264f, 0f, 22.5f, 0f)
                curveTo(10.0736f, 0f, 0f, 10.0632f, 0f, 22.4896f)
                close()
            }
        }.build()
        return _BookmarkFilled!!
    }
val BookmarkOutlined: ImageVector
    get() {
        if (_BookmarkOutlined != null) return _BookmarkOutlined!!
        _BookmarkOutlined = ImageVector.Builder(
            name = "BookmarkOutlined",
            defaultWidth = 21.dp,
            defaultHeight = 24.dp,
            viewportWidth = 21f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color(0xFF003D3A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.116f, 1.154f)
                curveTo(18.491f, 1.314f, 19.5f, 2.501f, 19.5f, 3.886f)
                lineTo(19.5f, 23.252f)
                lineTo(10.125f, 18.564f)
                lineTo(0.75f, 23.252f)
                lineTo(0.75f, 3.886f)
                curveTo(0.75f, 2.501f, 1.758f, 1.314f, 3.134f, 1.154f)
                curveTo(7.779f, 0.615f, 12.471f, 0.615f, 17.116f, 1.154f)
                close()
            }
        }.build()
        return _BookmarkOutlined!!
    }
val Bookshelf: ImageVector
    get() {
        if (_Bookshelf != null) return _Bookshelf!!

        _Bookshelf = ImageVector.Builder(
            name = "Bookshelf",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(2.5f, 0f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineTo(2f)
                horizontalLineToRelative(10f)
                verticalLineTo(0.5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 1f, 0f)
                verticalLineToRelative(15f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(15f)
                horizontalLineTo(3f)
                verticalLineToRelative(0.5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineTo(0.5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveTo(3f, 14f)
                horizontalLineToRelative(10f)
                verticalLineToRelative(-3f)
                horizontalLineTo(3f)
                close()
                moveToRelative(0f, -4f)
                horizontalLineToRelative(10f)
                verticalLineTo(7f)
                horizontalLineTo(3f)
                close()
                moveToRelative(0f, -4f)
                horizontalLineToRelative(10f)
                verticalLineTo(3f)
                horizontalLineTo(3f)
                close()
            }
        }.build()

        return _Bookshelf!!
    }

val Star: ImageVector
    get() {
        if (_Star != null) return _Star!!

        _Star = ImageVector.Builder(
            name = "Star",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.4806f, 3.4987f)
                curveTo(11.6728f, 3.03673f, 12.3272f, 3.03673f, 12.5193f, 3.4987f)
                lineTo(14.6453f, 8.61016f)
                curveTo(14.7263f, 8.80492f, 14.9095f, 8.93799f, 15.1197f, 8.95485f)
                lineTo(20.638f, 9.39724f)
                curveTo(21.1367f, 9.43722f, 21.339f, 10.0596f, 20.959f, 10.3851f)
                lineTo(16.7546f, 13.9866f)
                curveTo(16.5945f, 14.1238f, 16.5245f, 14.3391f, 16.5734f, 14.5443f)
                lineTo(17.8579f, 19.9292f)
                curveTo(17.974f, 20.4159f, 17.4446f, 20.8005f, 17.0176f, 20.5397f)
                lineTo(12.2932f, 17.6541f)
                curveTo(12.1132f, 17.5441f, 11.8868f, 17.5441f, 11.7068f, 17.6541f)
                lineTo(6.98238f, 20.5397f)
                curveTo(6.55539f, 20.8005f, 6.02594f, 20.4159f, 6.14203f, 19.9292f)
                lineTo(7.42652f, 14.5443f)
                curveTo(7.47546f, 14.3391f, 7.4055f, 14.1238f, 7.24531f, 13.9866f)
                lineTo(3.04099f, 10.3851f)
                curveTo(2.661f, 10.0596f, 2.86323f, 9.43722f, 3.36197f, 9.39724f)
                lineTo(8.88022f, 8.95485f)
                curveTo(9.09048f, 8.93799f, 9.27363f, 8.80492f, 9.35464f, 8.61016f)
                lineTo(11.4806f, 3.4987f)
                close()
            }
        }.build()

        return _Star!!
    }

val StarHalf: ImageVector
    get() {
        if (_StarHalf != null) return _StarHalf!!

        _StarHalf = ImageVector.Builder(
            name = "StarHalf",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {

            // --- Metade esquerda preenchida ---
            path(
                fill = SolidColor(Color(0xFF0F172A)),
                stroke = null
            ) {
                moveTo(11.4806f, 3.4987f)
                curveTo(11.6728f, 3.03673f, 12.3272f, 3.03673f, 12.5193f, 3.4987f)
                lineTo(12.5193f, 17.6541f)
                lineTo(11.7068f, 17.6541f)
                lineTo(6.98238f, 20.5397f)
                lineTo(7.42652f, 14.5443f)
                lineTo(3.04099f, 10.3851f)
                lineTo(8.88022f, 8.95485f)
                lineTo(11.4806f, 3.4987f)
                close()
            }

            // --- Contorno completo igual ao Star original ---
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11.4806f, 3.4987f)
                curveTo(11.6728f, 3.03673f, 12.3272f, 3.03673f, 12.5193f, 3.4987f)
                lineTo(14.6453f, 8.61016f)
                curveTo(14.7263f, 8.80492f, 14.9095f, 8.93799f, 15.1197f, 8.95485f)
                lineTo(20.638f, 9.39724f)
                curveTo(21.1367f, 9.43722f, 21.339f, 10.0596f, 20.959f, 10.3851f)
                lineTo(16.7546f, 13.9866f)
                curveTo(16.5945f, 14.1238f, 16.5245f, 14.3391f, 16.5734f, 14.5443f)
                lineTo(17.8579f, 19.9292f)
                curveTo(17.974f, 20.4159f, 17.4446f, 20.8005f, 17.0176f, 20.5397f)
                lineTo(12.2932f, 17.6541f)
                curveTo(12.1132f, 17.5441f, 11.8868f, 17.5441f, 11.7068f, 17.6541f)
                lineTo(6.98238f, 20.5397f)
                curveTo(6.55539f, 20.8005f, 6.02594f, 20.4159f, 6.14203f, 19.9292f)
                lineTo(7.42652f, 14.5443f)
                curveTo(7.47546f, 14.3391f, 7.4055f, 14.1238f, 7.24531f, 13.9866f)
                lineTo(3.04099f, 10.3851f)
                curveTo(2.661f, 10.0596f, 2.86323f, 9.43722f, 3.36197f, 9.39724f)
                lineTo(8.88022f, 8.95485f)
                curveTo(9.09048f, 8.93799f, 9.27363f, 8.80492f, 9.35464f, 8.61016f)
                lineTo(11.4806f, 3.4987f)
                close()
            }

        }.build()

        return _StarHalf!!
    }

val StarFull: ImageVector
    get() {
        if (_StarFull != null) return _StarFull!!

        _StarFull = ImageVector.Builder(
            name = "StarFull",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {

            path(
                fill = SolidColor(Color(0xFF0F172A)),
                stroke = null
            ) {
                moveTo(11.4806f, 3.4987f)
                curveTo(11.6728f, 3.03673f, 12.3272f, 3.03673f, 12.5193f, 3.4987f)
                lineTo(14.6453f, 8.61016f)
                curveTo(14.7263f, 8.80492f, 14.9095f, 8.93799f, 15.1197f, 8.95485f)
                lineTo(20.638f, 9.39724f)
                curveTo(21.1367f, 9.43722f, 21.339f, 10.0596f, 20.959f, 10.3851f)
                lineTo(16.7546f, 13.9866f)
                curveTo(16.5945f, 14.1238f, 16.5245f, 14.3391f, 16.5734f, 14.5443f)
                lineTo(17.8579f, 19.9292f)
                curveTo(17.974f, 20.4159f, 17.4446f, 20.8005f, 17.0176f, 20.5397f)
                lineTo(12.2932f, 17.6541f)
                curveTo(12.1132f, 17.5441f, 11.8868f, 17.5441f, 11.7068f, 17.6541f)
                lineTo(6.98238f, 20.5397f)
                curveTo(6.55539f, 20.8005f, 6.02594f, 20.4159f, 6.14203f, 19.9292f)
                lineTo(7.42652f, 14.5443f)
                curveTo(7.47546f, 14.3391f, 7.4055f, 14.1238f, 7.24531f, 13.9866f)
                lineTo(3.04099f, 10.3851f)
                curveTo(2.661f, 10.0596f, 2.86323f, 9.43722f, 3.36197f, 9.39724f)
                lineTo(8.88022f, 8.95485f)
                curveTo(9.09048f, 8.93799f, 9.27363f, 8.80492f, 9.35464f, 8.61016f)
                lineTo(11.4806f, 3.4987f)
                close()
            }

            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                fill = null
            ) {
                moveTo(11.4806f, 3.4987f)
                curveTo(11.6728f, 3.03673f, 12.3272f, 3.03673f, 12.5193f, 3.4987f)
                lineTo(14.6453f, 8.61016f)
                curveTo(14.7263f, 8.80492f, 14.9095f, 8.93799f, 15.1197f, 8.95485f)
                lineTo(20.638f, 9.39724f)
                curveTo(21.1367f, 9.43722f, 21.339f, 10.0596f, 20.959f, 10.3851f)
                lineTo(16.7546f, 13.9866f)
                curveTo(16.5945f, 14.1238f, 16.5245f, 14.3391f, 16.5734f, 14.5443f)
                lineTo(17.8579f, 19.9292f)
                curveTo(17.974f, 20.4159f, 17.4446f, 20.8005f, 17.0176f, 20.5397f)
                lineTo(12.2932f, 17.6541f)
                curveTo(12.1132f, 17.5441f, 11.8868f, 17.5441f, 11.7068f, 17.6541f)
                lineTo(6.98238f, 20.5397f)
                curveTo(6.55539f, 20.8005f, 6.02594f, 20.4159f, 6.14203f, 19.9292f)
                lineTo(7.42652f, 14.5443f)
                curveTo(7.47546f, 14.3391f, 7.4055f, 14.1238f, 7.24531f, 13.9866f)
                lineTo(3.04099f, 10.3851f)
                curveTo(2.661f, 10.0596f, 2.86323f, 9.43722f, 3.36197f, 9.39724f)
                lineTo(8.88022f, 8.95485f)
                curveTo(9.09048f, 8.93799f, 9.27363f, 8.80492f, 9.35464f, 8.61016f)
                lineTo(11.4806f, 3.4987f)
                close()
            }

        }.build()

        return _StarFull!!
    }

val Pencil: ImageVector
    get() {
        if (_Pencil != null) return _Pencil!!

        _Pencil = ImageVector.Builder(
            name = "Pencil",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16.8617f, 4.48667f)
                lineTo(18.5492f, 2.79917f)
                curveTo(19.2814f, 2.06694f, 20.4686f, 2.06694f, 21.2008f, 2.79917f)
                curveTo(21.9331f, 3.53141f, 21.9331f, 4.71859f, 21.2008f, 5.45083f)
                lineTo(6.83218f, 19.8195f)
                curveTo(6.30351f, 20.3481f, 5.65144f, 20.7368f, 4.93489f, 20.9502f)
                lineTo(2.25f, 21.75f)
                lineTo(3.04978f, 19.0651f)
                curveTo(3.26323f, 18.3486f, 3.65185f, 17.6965f, 4.18052f, 17.1678f)
                lineTo(16.8617f, 4.48667f)
                close()
                moveTo(16.8617f, 4.48667f)
                lineTo(19.5f, 7.12499f)
            }
        }.build()

        return _Pencil!!
    }

val User: ImageVector
    get() {
        if (_User != null) return _User!!

        _User = ImageVector.Builder(
            name = "User",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15.75f, 6f)
                curveTo(15.75f, 8.07107f, 14.071f, 9.75f, 12f, 9.75f)
                curveTo(9.9289f, 9.75f, 8.24996f, 8.07107f, 8.24996f, 6f)
                curveTo(8.24996f, 3.92893f, 9.9289f, 2.25f, 12f, 2.25f)
                curveTo(14.071f, 2.25f, 15.75f, 3.92893f, 15.75f, 6f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4.5011f, 20.1182f)
                curveTo(4.5714f, 16.0369f, 7.90184f, 12.75f, 12f, 12.75f)
                curveTo(16.0982f, 12.75f, 19.4287f, 16.0371f, 19.4988f, 20.1185f)
                curveTo(17.216f, 21.166f, 14.6764f, 21.75f, 12.0003f, 21.75f)
                curveTo(9.32396f, 21.75f, 6.78406f, 21.1659f, 4.5011f, 20.1182f)
                close()
            }
        }.build()

        return _User!!
    }


val CheckCircle: ImageVector
    get() {
        if (_CheckCircle != null) return _CheckCircle!!

        _CheckCircle = ImageVector.Builder(
            name = "CheckCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 12.75f)
                lineTo(11.25f, 15f)
                lineTo(15f, 9.75f)
                moveTo(21f, 12f)
                curveTo(21f, 16.9706f, 16.9706f, 21f, 12f, 21f)
                curveTo(7.02944f, 21f, 3f, 16.9706f, 3f, 12f)
                curveTo(3f, 7.02944f, 7.02944f, 3f, 12f, 3f)
                curveTo(16.9706f, 3f, 21f, 7.02944f, 21f, 12f)
                close()
            }
        }.build()

        return _CheckCircle!!
    }


val PlusCircle: ImageVector
    get() {
        if (_PlusCircle != null) return _PlusCircle!!

        _PlusCircle = ImageVector.Builder(
            name = "PlusCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 9f)
                verticalLineTo(15f)
                moveTo(15f, 12f)
                horizontalLineTo(9f)
                moveTo(21f, 12f)
                curveTo(21f, 16.9706f, 16.9706f, 21f, 12f, 21f)
                curveTo(7.02944f, 21f, 3f, 16.9706f, 3f, 12f)
                curveTo(3f, 7.02944f, 7.02944f, 3f, 12f, 3f)
                curveTo(16.9706f, 3f, 21f, 7.02944f, 21f, 12f)
                close()
            }
        }.build()

        return _PlusCircle!!
    }

val MagnifyingGlass: ImageVector
    get() {
        if (_MagnifyingGlass != null) return _MagnifyingGlass!!

        _MagnifyingGlass = ImageVector.Builder(
            name = "MagnifyingGlass",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21f, 21f)
                lineTo(15.8033f, 15.8033f)
                moveTo(15.8033f, 15.8033f)
                curveTo(17.1605f, 14.4461f, 18f, 12.5711f, 18f, 10.5f)
                curveTo(18f, 6.35786f, 14.6421f, 3f, 10.5f, 3f)
                curveTo(6.35786f, 3f, 3f, 6.35786f, 3f, 10.5f)
                curveTo(3f, 14.6421f, 6.35786f, 18f, 10.5f, 18f)
                curveTo(12.5711f, 18f, 14.4461f, 17.1605f, 15.8033f, 15.8033f)
                close()
            }
        }.build()

        return _MagnifyingGlass!!
    }


private var _MagnifyingGlass: ImageVector? = null
private var _PlusCircle: ImageVector? = null
private var _CheckCircle: ImageVector? = null
private var _User: ImageVector? = null
private var _Pencil: ImageVector? = null
private var _StarFull: ImageVector? = null
private var _StarHalf: ImageVector? = null
private var _Star: ImageVector? = null
private var _Bookshelf: ImageVector? = null
private var _BookmarkFilled: ImageVector? = null
private var _BookmarkOutlined: ImageVector? = null
private var _Language: ImageVector? = null
private var _CalendarDays: ImageVector? = null
private var _BookOpen: ImageVector? = null
private var _BuildingLibrary: ImageVector? = null
private var _Arrow_back_ios_new: ImageVector? = null
private var _Arrow_forward_ios_new: ImageVector? = null

