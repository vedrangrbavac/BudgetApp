package com.example.budgetapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.budgetapp.data.database.AppDb
import com.example.budgetapp.data.database.dao.TransactionsDao
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.repositories.TransactionsRepository
import com.example.budgetapp.viewmodels.TransactionsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get
import java.time.LocalDate

class TransactionsViewModelTest : TestCase() {

    private val transactionsViewModel =
        TransactionsViewModel(get())    // inicijaliziramo viewModel
    private val transactionsRepository get() = get<TransactionsRepository>() // inicijaliziramo repozitorij

    override val testCaseMocksModule: Module                            // lista potrebni modula
        get() = module {
            single<TransactionsRepository> { mockk(relaxed = true) }
            single<AppDb> { mockk(relaxed = true) }
            single<TransactionsDao> { mockk(relaxed = true) }
        }

    private val testDispatcher = TestCoroutineDispatcher()

    override fun before() {
        super.before()
        Dispatchers.setMain(testDispatcher)
    }

    override fun after() {
        super.after()
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    val dbTransactionTemp = DBTransaction("Groceries", LocalDate.now(), "Food", 15.68)      // statički podaci

    @Test
    fun getTransactionsLiveData() = runBlockingTest {
        every { transactionsRepository.transactions } returns MutableLiveData(      // svaki poziv na tu metodu će uvijek vratiti tu listu
            listOf(                                                                 // "every" je metoda biblioteke Mockk
                dbTransactionTemp
            )
        )
        val observer = mockk<Observer<List<DBTransaction>?>>(relaxed = true)        // kreiramo observer koji promatramo
        transactionsViewModel.transactionsLiveData.observeForever(observer)         // poziv metode iz viewModela
        verify(timeout = 5000) { observer.onChanged(any()) }                        // provjeravamo je li se promijenio u vremenu od 5 sekundi
    }
}