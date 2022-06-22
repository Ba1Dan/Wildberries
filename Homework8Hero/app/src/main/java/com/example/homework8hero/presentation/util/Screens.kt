package com.example.homework8hero.presentation.util

import com.example.homework8hero.presentation.detail.DetailHeroFragment
import com.example.homework8hero.presentation.list.ListHeroesFragment
import com.example.homework8hero.presentation.info.InfoFragment
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