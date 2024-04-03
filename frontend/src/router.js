
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReservationReserveManager from "./components/listers/ReservationReserveCards"
import ReservationReserveDetail from "./components/listers/ReservationReserveDetail"

import TradeTradeManager from "./components/listers/TradeTradeCards"
import TradeTradeDetail from "./components/listers/TradeTradeDetail"

import StockInventoryManager from "./components/listers/StockInventoryCards"
import StockInventoryDetail from "./components/listers/StockInventoryDetail"

import NotificationNoticeManager from "./components/listers/NotificationNoticeCards"
import NotificationNoticeDetail from "./components/listers/NotificationNoticeDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reservations/reserves',
                name: 'ReservationReserveManager',
                component: ReservationReserveManager
            },
            {
                path: '/reservations/reserves/:id',
                name: 'ReservationReserveDetail',
                component: ReservationReserveDetail
            },

            {
                path: '/trades/trades',
                name: 'TradeTradeManager',
                component: TradeTradeManager
            },
            {
                path: '/trades/trades/:id',
                name: 'TradeTradeDetail',
                component: TradeTradeDetail
            },

            {
                path: '/stocks/inventories',
                name: 'StockInventoryManager',
                component: StockInventoryManager
            },
            {
                path: '/stocks/inventories/:id',
                name: 'StockInventoryDetail',
                component: StockInventoryDetail
            },

            {
                path: '/notifications/notices',
                name: 'NotificationNoticeManager',
                component: NotificationNoticeManager
            },
            {
                path: '/notifications/notices/:id',
                name: 'NotificationNoticeDetail',
                component: NotificationNoticeDetail
            },



    ]
})
