package com.example.blogpost.domain.settings.models

enum class SettingType(
    val settingName: String,
    val key: String,
    val description: String
) {
    NOTIFICATION_POST( // TODO: move name + desc to repository!!
        settingName = "Посты",
        key = "blogpost_notification_post",
        description = "Вы будете получать уведомления\nо новых постах"
    ),
    NOTIFICATION_LIKE(
        settingName = "Лайки",
        key = "blogpost_notification_like",
        description = "Вам придет уведомление,\nкогда ваш пост лайкнут"
    ),
    NOTIFICATION_COMMENT(
        settingName = "Комментарии",
        key = "blogpost_notification_comment",
        description = "Вы узнаете, если кто-то\nпрокомментирует ваш пост"
    )
}