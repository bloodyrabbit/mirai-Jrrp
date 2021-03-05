package com.blrabbit.mirai.dice

import kotlinx.serialization.Serializable

// 用户存储的数据格式
@Serializable
data class Userstorge (
    val Jrrp:Int,
    val suitable:String
    )