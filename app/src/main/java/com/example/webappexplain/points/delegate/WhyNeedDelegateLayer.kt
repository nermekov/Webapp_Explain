package com.example.webappexplain.points.delegate

//1. Когда нет DelegateLayer: Слишком много зависимостей/импортов
/*
import app.rahmet.appsettings.api.domain.AppRepository
import com.choco.analytics.api.AnalyticsFacade
import app.rahmet.promo.api.domain.repo.ReferralRepository
import kz.rahmet.domain.repositories.OrdersRepository
import kz.rahmet.domain.repositories.TakeAwayRepository
 */
//В данном примере только по импортам видно, что у класса очень широкая зона ответственности

//2. Когда нет DelegateLayer: Много полей и много функций которые их обрабатывают,
// связность этих полей низкая, разный контекст
/*
//region Vars
    private var transactionDetailsModel: TakeAwayTransactionDetails? = null
    private var isTakeawayTransaction = false
    private lateinit var payReferral: PayReferral
    //endregion

    //region DI
    private val analytics: AnalyticsFacade by inject()
    private val takeAwayRepository: TakeAwayRepository by inject()
    private val ordersRepository: OrdersRepository by inject()
    private val referralRepository: ReferralRepository by inject()
    private val appRepository: AppRepository by inject()
    //endregion
*/
//Эти ньюансы могут повлиять на удобство рефакторинга и тестинга