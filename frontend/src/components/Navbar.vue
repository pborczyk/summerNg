<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand :to="{ path: 'newest' }">Summer</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item :to="{ path: '/entries/newest' }">Nowe</b-nav-item>
                    <b-nav-item :to="{ path: '/entries/hot' }">Gorące</b-nav-item>
                    <b-nav-item :to="{ path: '/entries/top' }">Nejlepsze</b-nav-item>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto">
                   <login-dropdown></login-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {logIn} from '@/security/BasicAuthInterceptor';
    import LoginDropdown from '@/components/LoginDropdown.vue';
    @Component({
        components: {
            LoginDropdown,
        },
    })
    export default class Navbar extends Vue {

        private form: any = {
            login: '',
            password: '',
        };

        get isUserLoggedIn(): boolean {
            return localStorage.getItem('auth_token') != null;
        }

        private onLoginSubmit() {
            logIn(this.form.login, this.form.password);
        }
    }
</script>

<style scoped>

</style>
