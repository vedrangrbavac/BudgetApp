package com.example.budgetapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.budgetapp.data.database.AppDb
import com.example.budgetapp.data.database.dao.TransactionsDao
import com.example.budgetapp.data.models.persistance.DBTransaction
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.Assert.*
import java.time.LocalDate

class TransactionDatabaseTest {
    @JvmField
    @Rule
    val instantTaskExRule = InstantTaskExecutorRule()

    private lateinit var transactionsDao: TransactionsDao
    private lateinit var db: AppDb
    private lateinit var transaction: DBTransaction

    @Before
    fun createDb() {
        runBlocking {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).allowMainThreadQueries().build()
            transactionsDao = db.transactionsDao
            transaction = DBTransaction("Coffee with friends", LocalDate.now(), "Social life", 25.19)
            transactionsDao.insertModel(transaction)
        }
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun getTransactions() = runBlockingTest {
        assertEquals(transactionsDao.getTransactionsAsync().size, 1)
        assertEquals(transactionsDao.getTransactionsAsync()[0].category, "Social life")
        assertNotEquals(transactionsDao.getTransactionsAsync()[0].totalPrice, 25)
        assertTrue("It's false!", transactionsDao.getTransactionsAsync()[0].contents == transaction.contents)
    }
}