package com.example.odigitaltest.core.extensions

import com.example.odigitaltest.core.utils.Constants
import com.example.odigitaltest.core.utils.ImageSize

fun String.toUrl(imageSize: ImageSize = ImageSize.SMALL) =
    "${Constants.IMAGE_BASE_URL}${imageSize.sizePath}$this"