import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        shipLineCode: -1,
        balloonCode: "-111111",
    }, 
    mutations: {
        selectShipLine(state, selectCode) { //设置地图上shipline筛选条件
            state.shipLineCode = selectCode
        },
        choiceShipPoint(state, balloonCode) {
            state.balloonCode = balloonCode
        }
    }
})
