import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        isLoggedIn: !!sessionStorage.getItem('auth_token'),
        loggedInUsername: '',
    },
    mutations: {
        logIn(state, username) {
            state.isLoggedIn = true;
            state.loggedInUsername = username;
        },

        logOut(state) {
            state.isLoggedIn = false;
        },
    },
});
