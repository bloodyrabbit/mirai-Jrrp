package com.blrabbit.mirai.dice

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.message.data.LightApp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object DiceMain : KotlinPlugin(
    JvmPluginDescription(
        id = "com.blrabbit.mirai.Jrrp",
        name = "Jrrp",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        DiceData.reload()
        GlobalEventChannel.subscribeMessages {
            always {
                if (message.contentToString() == ".jrrp" || message.contentToString() == "。jrrp") {
                    val miniapp = Miniclass(sender,senderName)
                    miniapp.adddata(0,"今日人品",JrrpRandom(sender.id).Jrrp.toString())
                    miniapp.adddata(1,"今日适宜",JrrpRandom(sender.id).suitable)
                    subject.sendMessage(LightApp(miniapp.toLightapp()))
                }
            }


        }
    }
}
// 生成今日人品并返回
fun JrrpRandom(id: Long): Userstorge {
    //获取时间
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.BASIC_ISO_DATE
    val formatted = current.format(formatter)

    //日期变更清空数据
    if (DiceData.Time != formatted){
        DiceData.Time = formatted
        DiceData.Dmap.clear()
    }
    //返回已经生成的今日人品
    DiceData.Dmap[id]?.let {
        return it
    }

    val suitable:List<String> = listOf("女装","打游戏","逛街","抽卡","氪金","学习","睡觉","修仙","看番","强化")
    val jrrp = Userstorge((1..100).random(),suitable.random())
    DiceData.Dmap.put(id, jrrp)
    return jrrp
}

// 存储数据到本地
object DiceData : AutoSavePluginData("DiceData") {
    var Time:String by value("")
    var Dmap: MutableMap<Long, Userstorge> by value(mutableMapOf())
}


