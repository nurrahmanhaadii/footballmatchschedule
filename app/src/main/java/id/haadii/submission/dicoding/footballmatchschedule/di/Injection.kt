package id.haadii.submission.dicoding.footballmatchschedule.di

import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository

object Injection {
    fun provideRepository() : MatchRepository {
        return MatchRepository()
    }
}