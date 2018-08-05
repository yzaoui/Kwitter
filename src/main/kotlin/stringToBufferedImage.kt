import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*

fun stringToBufferedImage(stringSeed: String, numCellsX: Int = 8, numCellsY: Int = 8, cellWidthPixels: Int = 8, cellHeightPixels: Int = 8, flatImage: Boolean = false): BufferedImage {
    val seed: Int = stringSeed.hashCode()
    val random = Random(seed.toLong())

    val hue = Math.abs(random.nextFloat()) % 1f
    val saturation = Math.abs(random.nextFloat()) % 0.5f + 0.5f
    val brightness = Math.abs(random.nextFloat()) % 0.3f + 0.7f

    val foregroundColor = Color(50, 50, 50).rgb

    // avatar is a bitmap of RGB integers
    val avatar = Array(numCellsX, { IntArray(numCellsY) })
    for (x in 0 until numCellsX / 2 + if (numCellsX % 2 == 0) 0 else 1) {
        val middleColumn = x == numCellsX - 1 - x
        for (y in 0 until numCellsY) {
            if (random.nextBoolean()) {
                val mod1 = Math.abs(random.nextFloat()) % 0.1f - 0.05f
                avatar[x][y] = when(flatImage) {
                    true -> Color.HSBtoRGB(hue, saturation, brightness)
                    false -> Color.HSBtoRGB(hue + mod1, saturation, Math.min(Math.max(brightness + mod1, 0.0f), 1.0f))
                }

                val mod2 = Math.abs(random.nextFloat()) % 0.1f - 0.05f
                if (!middleColumn) avatar[numCellsX - 1 - x][y] = when(flatImage) {
                    true -> avatar[x][y]
                    false -> Color.HSBtoRGB(hue + mod2, saturation, Math.min(Math.max(brightness + mod2, 0.0f), 1.0f))
                }
            } else {
                avatar[x][y] = foregroundColor
                if (!middleColumn) avatar[numCellsX - 1 - x][y] = foregroundColor
            }
        }
    }

    val image = BufferedImage(numCellsX * cellWidthPixels, numCellsY * cellHeightPixels, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until numCellsX) {
        for (y in 0 until numCellsY) {
            for (px in 0 until cellWidthPixels) {
                for (py in 0 until cellHeightPixels) {
                    image.setRGB(cellWidthPixels * x + px, cellHeightPixels * y + py, avatar[x][y])
                }
            }
        }
    }

    return image
}
