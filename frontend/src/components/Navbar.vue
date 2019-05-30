<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand :to="{ path: 'newest' }">Summer</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item :to="{ path: '/entries/newest' }">Nowe</b-nav-item>
                    <b-nav-item :to="{ path: '/entries/top' }">Najlepsze</b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto" v-if="isLoginDropdownVisible">
                    <login-dropdown></login-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script lang="ts">
    import {Component} from 'vue-property-decorator';
    import LoginDropdown from '@/components/LoginDropdown.vue';
    import Vue from 'vue';
    import {store} from '@/store/store';

    @Component({
        components: {
            LoginDropdown,
        },
    })
    export default class Navbar extends Vue {

        get isLoginDropdownVisible(): boolean {
            return !store.state.isLoggedIn;
        }

        private form: any = {
            login: '',
            password: '',
        };
    }
</script>

<style scoped>

</style>
