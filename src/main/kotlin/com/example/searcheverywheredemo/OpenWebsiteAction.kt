package com.example.searcheverywheredemo

import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.actions.searcheverywhere.SearchEverywhereToggleAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class OpenWebsiteAction : AnAction("What is this?", "Open website", AllIcons.Actions.Help), SearchEverywhereToggleAction {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.open("https://www.example.com/");
    }

    override fun isEverywhere(): Boolean = false

    override fun setEverywhere(everywhere: Boolean) = Unit

    override fun canToggleEverywhere(): Boolean = false

}
