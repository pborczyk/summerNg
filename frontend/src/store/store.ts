import Vue from 'vue';
import Vuex from 'vuex';
import {UserDto} from '@/data/UserDto';

Vue.use(Vuex);

interface State {
    isLoggedIn: boolean;
    loggedInUsername: string;
    loggedInUserPrivileges: string[];
}

const stateObj: State = {
    isLoggedIn: false,
    loggedInUserPrivileges: [],
    loggedInUsername: '',
};

export const store = new Vuex.Store({
    state: stateObj,
    mutations: {
        logIn(state, user: UserDto) {
            state.isLoggedIn = true;
            state.loggedInUsername = user.username;
            state.loggedInUserPrivileges = user.privileges;
        },

        logOut(state) {
            state.isLoggedIn = false;
        },
    },
});
