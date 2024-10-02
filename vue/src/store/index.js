import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      currentBackground: '../../dinerBackground.jpg',
      currentSearch: '',
      showRestaurants: true,
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      UPDATE_BACKGROUND(state, string) {
        state.currentBackground = string;
      },
      SET_SEARCH_TERM(state, search){
        state.currentSearch = search;
      },
      TOGGLE_RESTAURANTS(state){
        state.showRestaurants = !state.showRestaurants;
      }
    },
  });
  return store;
}
