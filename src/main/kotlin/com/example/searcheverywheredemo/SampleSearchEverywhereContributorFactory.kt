package com.example.searcheverywheredemo

import com.intellij.ide.actions.searcheverywhere.SearchEverywhereContributor
import com.intellij.ide.actions.searcheverywhere.SearchEverywhereContributorFactory
import com.intellij.openapi.actionSystem.AnActionEvent

class SampleSearchEverywhereContributorFactory : SearchEverywhereContributorFactory<Any?> {
    override fun createContributor(anActionEvent: AnActionEvent): SearchEverywhereContributor<Any?> {
        return SampleSearchEverywhereContributor(anActionEvent)
    }
}
