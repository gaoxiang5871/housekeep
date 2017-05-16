import {
  HIDE_SIDERBAR,
  SHOW_SIDERBAR
} from '../mutation-types'

// initial state
// userInfomation
const state = {
  isHasSiderBar: true
}
const getters = {
  isHasSiderBar: state => state.isHasSiderBar
}
// actions
const actions = {
  hideSiderBar ({ commit, state }) {
    commit(HIDE_SIDERBAR)
  },
  showSideBar ({ commit, state }) {
    commit(SHOW_SIDERBAR)
  }
}

// mutations
const mutations = {
  [HIDE_SIDERBAR] (state) {
    state.isHasSiderBar = false
  },
  [SHOW_SIDERBAR] (state) {
    state.isHasSiderBar = true
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
