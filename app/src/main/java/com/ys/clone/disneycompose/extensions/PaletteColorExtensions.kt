package com.ys.clone.disneycompose.extensions

import androidx.palette.graphics.Palette

/**
 * Palette
 *  - 이미지에서 눈에 띄는 색상을 추출하는 도우미 클래스입니다.
 *  - 프로필이 다른 여러 색상이 이미지에서 추출됩니다.
 *  - Vibrant, Vibrant Dark, Vibrant Light, Muted, Muted Dark, Muted Light
 *  - 적절한 getter 메서드에서 검색할 수 있습니다.
 *  - 인스턴스는 생성된 팔레트를 조정할 수 있는 여러 옵션을 지원하는 Palette.Builder로 생성됩니다.
 *  - 생성은 항상 배경 스레드, 이상적으로는 이미지를 로드하는 스레드에서 완료되어야 합니다.
 *  - Palette.Builder는 동기 및 비동기 생성을 모두 지원합니다.
 */
fun Palette?.paletteColorList(): List<Int> {
	return listOf(
		this?.lightVibrantSwatch?.rgb,
		this?.lightMutedSwatch?.rgb,
		this?.vibrantSwatch?.rgb,
		this?.mutedSwatch?.rgb,
		this?.darkVibrantSwatch?.rgb,
		this?.darkMutedSwatch?.rgb,
		this?.dominantSwatch?.rgb
	).map {
		it ?: 0
	}
}