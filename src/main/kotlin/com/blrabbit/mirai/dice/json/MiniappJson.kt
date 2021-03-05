package com.blrabbit.mirai.dice.json

import kotlinx.serialization.Serializable

// json的格式
@Serializable
data class MiniappJson(
    val app: String = "com.tencent.miniapp",
    val desc: String = "",
    val view: String = "notification",
    val ver: String = "0.0.0.1",
    val prompt: String,//
    val meta: Meta
    ) {
    @Serializable
    data class Meta(
        val notification: Notification
    )

    @Serializable
    data class Notification(
        val appInfo: AppInfo,
        val data: List<Data>,
        val title: String = "",
        val emphasis_keyword: String = "今日人品"
    )

    @Serializable
    data class AppInfo(
        val appName: String,
        val appType: Int = 4,
        val appid: Long = 1109659848,
        val iconUrl: String
    )

    @Serializable
    data class Data(
        val title: String,
        val value: String
    )
}
