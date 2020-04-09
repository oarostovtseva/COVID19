package com.orost.covid19.utils


fun String.addSpacesToNumberText(): String {
    val chars = this.toCharArray()
    val sb = StringBuilder()
    val firstSpacePosition = this.length - (this.length / 3) * 3
    chars.forEachIndexed { i, ch ->
        val groupIndex = i - firstSpacePosition
        val groupPosition = groupIndex - groupIndex / 3 * 3
        if (i == firstSpacePosition && firstSpacePosition != 0) {
            sb.append(" ")
        } else if (groupPosition == 0 && i > 0) {
            sb.append(" ")
        }
        sb.append(ch)
    }
    return sb.toString()
}
