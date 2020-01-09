package id.haadii.submission.dicoding.footballmatchschedule.util

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.haadii.submission.dicoding.footballmatchschedule.detailleague.DetailViewModel
import id.haadii.submission.dicoding.footballmatchschedule.detailmatch.DetailMatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.detailteam.DetailTeamViewModel
import id.haadii.submission.dicoding.footballmatchschedule.di.Injection
import id.haadii.submission.dicoding.footballmatchschedule.match.MatchViewModel
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository

class ViewModelFactory(
    private val repository: MatchRepository,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(), application)
                    }
                }
            }
            return INSTANCE
        }
    }

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchViewModel::class.java)) {
            return MatchViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                repository
            ) as T
        } else if (modelClass.isAssignableFrom(DetailMatchViewModel::class.java)) {
            return DetailMatchViewModel(application) as T
        } else if (modelClass.isAssignableFrom(DetailTeamViewModel::class.java)) {
            return DetailTeamViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}