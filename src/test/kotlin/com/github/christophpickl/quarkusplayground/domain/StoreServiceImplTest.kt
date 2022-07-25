package com.github.christophpickl.quarkusplayground.domain

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class StoreServiceImplTest {

    private lateinit var repo: StoreRepository
    private lateinit var service: StoreServiceImpl
    private val givenStores = listOf(Store.any)
    private val store = Store.any

    @BeforeEach
    fun `init mocks`() {
        repo = mock()
        service = StoreServiceImpl(repo)
    }

    @Test
    fun `Given stores returned When fetch all Then stores returned`() {
        whenever(repo.selectAll()).thenReturn(givenStores)

        val actualStores = service.fetchAll()

        verify(repo, times(1)).selectAll()
        verifyNoMoreInteractions(repo)
        assertThat(actualStores, equalTo(givenStores))
    }

    @Test
    fun `Given store returned When fetch single Then store returned`() {
        whenever(repo.selectSingle(store.id)).thenReturn(store)

        val actualStore = service.find(store.id)

        verify(repo, times(1)).selectSingle(store.id)
        verifyNoMoreInteractions(repo)
        assertThat(actualStore, equalTo(store))
    }
}