import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      currentBackground: '../../dinerBackground.jpg',
      currentSearch: '',
      showRestaurants: false,
      showGroupForm: false,
      category: '',
      term: '',
      longitude: '',
      latitude: '',
      //from here down will need to be removed when end points are created
      groups: [],
      showVoteView: false
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
      SET_SEARCH_TERM(state, search) {
        state.currentSearch = search;
      },
      SET_NARROW_TERM(state, term) {
        state.term = term;
      },
      TOGGLE_RESTAURANTS(state, show) {
        state.showRestaurants = show;
      },
      TOGGLE_GROUP_FORM(state, show) {
        state.showGroupForm = show;
      },
      SET_CATEGORY(state, category) {
        state.category = category;
      },
      SET_LOCATION(state, location) {
        state.longitude = location.longitude;
        state.latitude = location.latitude;
      },
      TOGGLE_VOTE_VIEW(state, view) {
        state.showVoteView = view;
      },
      //from here down will need to be removed when end points are created
      ADD_GROUP(state, group) {
        state.groups.push(group);
      },
      ADD_EATERY_TO_VOTE(state, data) {
        console.log(data);
        if (state.groups[data.id].eateries === undefined) {
          state.groups[data.id].eateries = [data.eatery];
        } else {
          state.groups[data.id].eateries.push(data.eatery);
        }
      }
    },
  });
  return store;
}
