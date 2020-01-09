package id.haadii.submission.dicoding.footballmatchschedule.detailLeague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import id.haadii.submission.dicoding.footballmatchschedule.detailleague.DetailViewModel
import id.haadii.submission.dicoding.footballmatchschedule.model.DetailLeague
import id.haadii.submission.dicoding.footballmatchschedule.model.League
import id.haadii.submission.dicoding.footballmatchschedule.repository.MatchRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel
    private var repository = mock(MatchRepository::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel =
            DetailViewModel(
                repository
            )
    }

    @Test
    fun getDetailLeague() {
        val idLeague = "4328"
        val leagues = ArrayList<League>()
        leagues.add(
            League(
                idLeague = "4328",
                strLeague = "English Premier League",
                strLeagueAlternate = "Premier League",
                intFormedYear = "1992",
                dateFirstEvent = "2000-08-18",
                strCountry = "England",
                strWebsite = "www.premierleague.com",
                strBadge = "https://www.thesportsdb.com/images/media/league/badge/i6o0kh1549879062.png",
                strBanner = "https://www.thesportsdb.com/images/media/league/banner/4m3g4s1520767740.jpg",
                strDescriptionEN =
                "The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL).\r\n\r\nThe Premier League is a corporation in which the member clubs act as shareholders. Seasons run from August to May with each team playing 38 matches (playing each other home and away). Most games are played on Saturday and Sunday afternoons. The Premier League has featured 47 English and two Welsh clubs since its inception, making it a cross-border league.\r\n\r\nThe competition was formed as the FA Premier League on 20 February 1992 following the decision of clubs in the Football League First Division to break away from the Football League, founded in 1888, and take advantage of a lucrative television rights deal. The deal was worth £1 billion a year domestically as of 2013–14, with BSkyB and BT Group securing the domestic rights to broadcast 116 and 38 games respectively. The league generates €2.2 billion per year in domestic and international television rights. In 2014–15, teams were apportioned revenues of £1.6 billion, rising sharply to £2.4 billion in 2016–17.\r\n\r\nThe Premier League is the most-watched sports league in the world, broadcast in 212 territories to 643 million homes and a potential TV audience of 4.7 billion people. In the 2014–15 season, the average Premier League match attendance exceeded 36,000, second highest of any professional football league behind the Bundesliga's 43,500. Most stadium occupancies are near capacity. The Premier League ranks second in the UEFA coefficients of leagues based on performances in European competitions over the past five seasons, as of 2018.\r\n\r\nForty-nine clubs have competed since the inception of the Premier League in 1992. Six of them have won the title: Manchester United (13), Chelsea (5), Arsenal (3), Manchester City (3), Blackburn Rovers (1), and Leicester City (1). Following the 2003–04 season, Arsenal acquired the nickname \"The Invincibles\" as they became, and still remain, the only club to complete a Premier League campaign without losing a single game. The record of most points in a season is 100 by Manchester City in 2017–18."
            )
        )
        val dummyLeague = DetailLeague(leagues)
        val liveDataLeague = MutableLiveData<DetailLeague>()
        liveDataLeague.value = dummyLeague

        `when`(
            repository.getDetailLeague(
                com.nhaarman.mockitokotlin2.eq(idLeague),
                any()
            )
        ).thenAnswer {
            (it.arguments[1] as (DetailLeague?) -> Unit).invoke(dummyLeague)
        }

        val observer = mock(Observer::class.java) as Observer<DetailLeague>
        viewModel.setDetailLeague().value = dummyLeague
        viewModel.setDetailLeague().observeForever(observer)
        verify(observer, times(1)).onChanged(dummyLeague)
        assertNotNull(viewModel.setDetailLeague().value)
        assertEquals(
            leagues[0].strLeague,
            viewModel.setDetailLeague().value?.leagues?.get(0)?.strLeague
        )
    }

}