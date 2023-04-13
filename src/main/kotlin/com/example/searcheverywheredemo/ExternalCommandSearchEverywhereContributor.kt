package com.example.searcheverywheredemo

import com.intellij.ide.actions.SearchEverywherePsiRenderer
import com.intellij.ide.actions.searcheverywhere.ContributorSearchResult
import com.intellij.ide.actions.searcheverywhere.FileSearchEverywhereContributor
import com.intellij.ide.actions.searcheverywhere.FoundItemDescriptor
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.util.Processor
import java.awt.Component
import javax.swing.JList
import javax.swing.ListCellRenderer

abstract class ExternalCommandSearchEverywhereContributor(anActionEvent: AnActionEvent) :
FileSearchEverywhereContributor(anActionEvent) {

    protected abstract fun isEnabled(): Boolean

    override fun isShownInSeparateTab(): Boolean = isEnabled()

    override fun showInFindResults(): Boolean = isEnabled()

    override fun fetchWeightedElements(
        pattern: String,
        progressIndicator: ProgressIndicator,
        consumer: Processor<in FoundItemDescriptor<Any>>
    ) {
        if (myProject != null && isEnabled()) {
            val results: List<Any> = doSearch(pattern, true, progressIndicator).items
            results.takeWhile { result ->
                processWeightedElement(
                    progressIndicator, consumer, result, getElementPriority(result, pattern))
            }
        }
    }

    private fun processWeightedElement(
        progressIndicator: ProgressIndicator,
        consumer: Processor<in FoundItemDescriptor<Any>>,
        element: Any,
        priority: Int
    ): Boolean {
        if (progressIndicator.isCanceled) {
            return false
        }
        return consumer.process(FoundItemDescriptor(element, priority))
    }

    protected abstract fun doSearch(
        pattern: String,
        searchEverywhere: Boolean,
        progressIndicator: ProgressIndicator
    ): ContributorSearchResult<*>

    override fun getElementsRenderer(): ListCellRenderer<Any> {
        return ExternalCommandSearchEverywhereRender(this)
    }

    private inner class ExternalCommandSearchEverywhereRender(parent: Disposable) :
        SearchEverywherePsiRenderer(parent) {

        override fun getListCellRendererComponent(
            list: JList<*>?,
            value: Any?,
            index: Int,
            isSelected: Boolean,
            cellHasFocus: Boolean
        ): Component {
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
        }
    }
}
