<template>
    <div>
        <navbar></navbar>
        <router-view></router-view>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';

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



    Vue.use(BootstrapVue);
    Vue.use(VueRouter);

    const routes = [
        { path: '/entries/:mode', component: Entries },
        { path: '/register', component: RegisterForm },
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
        },
        router,
    })
    export default class App extends Vue {
    }

</script>

<style>

</style>
