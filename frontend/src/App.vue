<template>
    <div>
        <navbar></navbar>
        <entries v-bind:entries="testData"></entries>
    </div>
</template>

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

    Vue.use(BootstrapVue);


    @Component({
        components: {
            Entries,
            Navbar,
            HelloWorld,
        },
    })
    export default class App extends Vue {
        private testData: EntryDto[] = [
            {
                authorUsername: 'guy',
                entryContent: 'content',
                score: 12,
                comments: []
            },
            {
                authorUsername: 'guy2',
                entryContent: 'content2',
                score: 21,
                comments: []
            }];
    }
</script>

<style>

</style>
