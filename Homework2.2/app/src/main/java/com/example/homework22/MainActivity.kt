package com.example.homework22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 *  1) Отличия жц фрагмента от активити:
 *  - два новых колбека(onAttach, onDetach) для связывания/отвязывания активити
 *  - три новых колбека(onCreateView, onViewCreated, onDestroyView) для работы с View
 *  2) Новые колбеки необходимы для уменьшения использования памяти. Поэтому фрагменты легче по памяти
 *  чем активити, и разработчики стремятся использовать подход SingleActivity
 *  3) Фрагмент зависит от активити, он не может существовать независимо.
 *
 *  Жизненный цикл фрагмента:
 *  onAttach() - фрагмент связывается с активностью.
 *  onCreate() - происходит создание фрагмента, объектов, и здесь можем получить раннее сохранненое состояние через Bundle
 *  onCreateView() - создание view
 *  onViewCreated() - view создано, обычно здесб прописывается логика работы(подписываются на источники, вешают обработчики событий)
 *  onStart() - фрагмент виден на экране, но находится не в фокусе пользователя
 *  onResume() - фрагмент виден на экране и пользователь может взаимедействовать
 *  onPause() - фрамгент виден, но нахожится не в фокусе пользователя
 *  onStop() - фрагмент не виден на экране
 *  onDestroyView() - уничтожается view фрашмента, на этом этапе все ссылки на представление фрагмента должны быть удалены
 *  onDestroy() - уничтожается фрагмент
 *  onDetach() - фрагмент отвязывается от активити и удаляется из FragmentManager
 *
 *  Жизненный цикл необходим для правильного использования памяти, когда ресурсы, которые не используются, занимают память
 *
 *  Примеры: -
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, MainFragment.newInstance())
                .commit()
        }
    }
}