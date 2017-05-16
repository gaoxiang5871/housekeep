import {
  SET_TELEPHONE,
  REMOVE_TELEPHONE
} from '../mutation-types'

// initial state
// userInfomation
const state = {
  telephone: ''
}

// getters
const getters = {
  telephone: state => state.telephone
}

// actions
const actions = {
  setTelephone ({ commit, state }, telephone) {
    commit(SET_TELEPHONE, telephone)
  },
  removeTelephone ({ commit, state }) {
    commit(REMOVE_TELEPHONE)
  }
}

// mutations
const mutations = {
  [SET_TELEPHONE] (state, telephone) {
    console.log(telephone)
    state.telephone = telephone
  },

  [REMOVE_TELEPHONE] (state) {
    // clear cart
    state.telephone = ''
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
