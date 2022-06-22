package com.example.homework8hero.presentation.util

import com.example.homework8hero.presentation.ui.detail.DetailHeroFragment
import com.example.homework8hero.presentation.ui.list.ListHeroesFragment
import com.example.homework8hero.presentation.ui.info.InfoFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Heroes() = FragmentScreen {
        ListHeroesFragment.newInstance()
    }

    fun DetailHero(id: Int) = FragmentScreen {
        DetailHeroFragment.newInstance(id)
    }

    fun Info() = FragmentScreen {
        InfoFragment.newInstance()
    }

}