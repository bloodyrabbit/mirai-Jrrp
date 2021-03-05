package com.blrabbit.mirai.dice

import com.blrabbit.mirai.dice.json.MiniappJson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.contact.User

// json信息生成
class Miniclass(qq: User, sendername:String) {

    val appName:String = "${sendername}的今日人品"
    val iconUrl:String = qq.avatarUrl
    val data:MutableList<MiniappJson.Data> = mutableListOf()
    val format = Json { encodeDefaults = true }

    fun adddata(id:Int,title:String,value:String){
        data.add(id, MiniappJson.Data(title,value))
    }

    fun toLightapp(): String {
        val json = MiniappJson(
            prompt = appName,
            meta = MiniappJson.Meta(
                MiniappJson.Notification(
                    appInfo = MiniappJson.AppInfo(
                        appName = appName,
                        iconUrl = iconUrl
                    ),
                    data = data,
                )
            )
        )
        return format.encodeToString(json)
    }
}