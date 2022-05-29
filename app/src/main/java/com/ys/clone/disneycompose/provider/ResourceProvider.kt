package com.ys.clone.disneycompose.provider

import androidx.annotation.StringRes

interface ResourceProvider {
	fun getString(@StringRes id: Int): String
}