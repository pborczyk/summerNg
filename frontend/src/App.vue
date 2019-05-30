<template>
    <div>
        <navbar @alert-event="alertEvent"></navbar>
        <b-alert
                :show="dismissCountDown"
                dismissible
                variant="warning"
                @dismissed="dismissCountDown=0"
                @dismiss-count-down="countDownChanged">
            <p>{{ alertMessage }}
            </p>
            <b-progress
                    :variant="alertVariant"
                    :max="dismissSecs"
                    :value="dismissCountDown"
                    height="4px">

            </b-progress>
        </b-alert>
        <b-container>
            <router-view @alert-event="alertEvent"></router-view>
        </b-container>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Vuex from 'vuex';

    import {Component, Prop} from 'vue-property-decorator';

    import BootstrapVue from 'bootstrap-vue';
    import 'bootstrap/dist/css/bootstrap.css';
    import 'bootstrap-vue/dist/bootstrap-vue.css';

    import VueRouter from 'vue-router';

    import NewEntryForm from './components/NewEntryForm.vue';
    import RegisterForm from './components/RegisterForm.vue';
    import Entry from './components/Entry.vue';
    import Comment from './components/Comment.vue';
    import Entries from './components/Entries.vue';
    import Navbar from './components/Navbar.vue';
    import LoginDropdown from './components/LoginDropdown.vue';
    import AddCommentForm from './components/AddCommentForm.vue';
    import VeeValidate from 'vee-validate';
    import Profile from '@/components/Profile.vue';

    Vue.use(VeeValidate, {
        inject: true,
    });
    Vue.use(BootstrapVue);
    Vue.use(VueRouter);
    Vue.use(Vuex);

    const routes = [
        {path: '/', component: Entries},
        {path: '/entries/:mode', component: Entries},
        {path: '/register', component: RegisterForm},
        {path: '/entry/:entryId', component: Entry, props: true},
        {path: '/profile/:username', component: Profile, props: true}
    ];

    const router = new VueRouter({
        routes,
    });

    @Component({
        components: {
            Entries,
            Navbar,
            NewEntryForm,
            RegisterForm,
            Entry,
            Comment,
            LoginDropdown,
            AddCommentForm,
            Profile,
        },
        router,

    })
    export default class App extends Vue {

        public alertMessage = '';
        public alertVariant = '';
        public dismissSecs = 10;
        public dismissCountDown = 0;

        private alertEvent(message: string, variant: string) {
            this.alertMessage = message;
            this.alertVariant = variant;
            this.dismissCountDown = this.dismissSecs;
        }

        private countDownChanged(dismissCountDown: number) {
            this.dismissCountDown = dismissCountDown;
        }
    }

</script>

<style>

</style>
