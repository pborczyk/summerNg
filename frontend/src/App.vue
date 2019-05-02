
        el: '#app',
    })
    export default cla<template>
    <div>
        <navbar></navbar>
        <router-view></router-view>
    </div>
</template>
<router-view></router-view>
<script lang="ts">
    import HelloWorld from './components/HelloWorld.vue';
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';
    import BootstrapVue from 'bootstrap-vue';
    import 'bootstrap/dist/css/bootstrap.css';
    import 'bootstrap-vue/dist/bootstrap-vue.css';
    import Navbar from './components/Navbar.vue';
    import Entries from './components/Entries.vue';
    import {EntryDto} from '@/data/EntryDto';
    import VueRouter from 'vue-router';

    Vue.use(BootstrapVue);
    Vue.use(VueRouter);

    const routes = [
        { path: '/entries/:mode', component: Entries },
    ];

    const router = new VueRouter({
        routes,
    });


    @Component({
        components: {
            Entries,
            Navbar,
            HelloWorld,
        },
        router,
        el: '#app',
    })
    export default class App extends Vue {
    }

</script>

<style>

</style>
