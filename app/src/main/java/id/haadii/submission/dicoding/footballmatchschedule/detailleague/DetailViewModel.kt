package id.haadii.submission.dicoding.footballmatchschedule.detailleague

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.haadii.submission.dicoding.footballmatchschedule.model.DetailLeague
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository

class DetailViewModel(private val repository: MatchRepository) : ViewModel() {

    private var dataDetailLeague = MutableLiveData<DetailLeague>()

    fun getDetailLeague(id: String) {
        repository.getDetailLeague(id) {
            dataDetailLeague.value = it
        }
    }

    fun setDetailLeague(): MutableLiveData<DetailLeague> {
        return dataDetailLeague
    }
}