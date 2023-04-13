package com.example.searcheverywheredemo

import com.intellij.ide.actions.searcheverywhere.ContributorSearchResult
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator

class SampleSearchEverywhereContributor(anActionEvent: AnActionEvent) :
    ExternalCommandSearchEverywhereContributor(anActionEvent) {
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getGroupName(): String {
        return "Sample Search"
    }

    override fun getActions(onChanged: Runnable): MutableList<AnAction> {
        return listOf<AnAction>(OpenWebsiteAction()).toMutableList()
    }
    override fun doSearch(
        pattern: String,
        searchEverywhere: Boolean,
        progressIndicator: ProgressIndicator
    ): ContributorSearchResult<*> {
        val builder = ContributorSearchResult.builder<Any>()
        for (i in 1..25 ) {
            builder.addItem(SampleNavigationItem("Sample $pattern $i"))
        }
        return builder.build()
    }
}
