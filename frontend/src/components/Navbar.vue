<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand :to="{ path: 'newest' }">Summer</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item :to="{ path: '/entries/newest' }">Nowe</b-nav-item>
                    <b-nav-item :to="{ path: '/entries/hot' }">Gorące</b-nav-item>
                    <b-nav-item :to="{ path: '/entries/top' }">Najlepsze</b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto" v-if="isLoginDropdownVisible">
                   <login-dropdown></login-dropdown>
                </b-navbar-nav>
                <b-nav
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script lang="ts">
    import {Component} from 'vue-property-decorator';
    import {logIn} from '@/security/BasicAuthInterceptor';
    import LoginDropdown from '@/components/LoginDropdown.vue';
    import Vue from 'vue';
    import {isUserLoggedIn} from '@/security/SecurityUtils';

    @Component({
        components: {
            LoginDropdown,
        },
    })
    export default class Navbar extends Vue {

        get isLoginDropdownVisible() : boolean {
            return !isUserLoggedIn();
        }

        private form: any = {
            login: '',
            password: '',
        };

        private onLoginSubmit() {
            logIn(this.form.login, this.form.password);
        }
    }
</script>

<style scoped>

</style>
