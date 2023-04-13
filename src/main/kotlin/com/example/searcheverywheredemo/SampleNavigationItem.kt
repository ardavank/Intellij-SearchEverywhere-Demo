package com.example.searcheverywheredemo

import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import javax.swing.Icon

class SampleNavigationItem(private val error: String? = null) : NavigationItem {
        override fun navigate(b: Boolean) = Unit

        override fun canNavigate(): Boolean = false

        override fun canNavigateToSource(): Boolean = false

        override fun getName(): String =
            error ?: "Unknown error!!"

        override fun getPresentation(): ItemPresentation =
            object : ItemPresentation {
                override fun getPresentableText(): String = name

                override fun getLocationString(): String? = null

                override fun getIcon(unused: Boolean): Icon? = null
            }
    }

