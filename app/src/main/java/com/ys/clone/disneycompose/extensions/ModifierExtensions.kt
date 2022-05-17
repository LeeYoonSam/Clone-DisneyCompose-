package com.ys.clone.disneycompose.extensions

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Stable
fun Modifier.visible(visibility: Boolean): Modifier {

	/**
	 * then
	 *  - 이 수정자를 다른 수정자와 연결합니다.
	 *  - 이 수식어를 나타내는 수식어를 돌려줍니다.
	 */

	return if (visibility) {
		this.then(alpha(1f))
	} else {
		this.then(alpha(0f))
	}
}