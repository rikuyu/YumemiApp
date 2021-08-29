package com.example.yumemiapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.yumemiapp.model.data.Profile
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class YumemiDaoTest {

    private lateinit var database: YumemiDatabase
    private lateinit var dao: YumemiDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YumemiDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.yumemiDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertContributer() = runBlockingTest {
        val contributer = Profile(
            id = 1,
            avatar_url = "dummy_url",
            followers_url = "dummy_url",
            following_url = "dummy_url",
            name = "dummy_name",
            html_url = "dummy_url",
        )

        dao.insertContributer(contributer)
        val allContributers = dao.getContributers()

        assertThat(allContributers).contains(contributer)
    }

    @Test
    fun deleteContributer() = runBlockingTest {
        val contributer = Profile(
            id = 1,
            avatar_url = "dummy_url",
            followers_url = "dummy_url",
            following_url = "dummy_url",
            name = "dummy_name",
            html_url = "dummy_url",
        )

        dao.insertContributer(contributer)
        dao.deleteContributer(contributer)
        val allContributers = dao.getContributers()

        assertThat(allContributers).doesNotContain(contributer)
    }

}