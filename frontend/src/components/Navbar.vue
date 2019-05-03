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
                    <b-nav-item :to="{ path: '/add' }">Dodaj</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                        <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                        <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                    </b-nav-form>

                    <b-nav-item-dropdown text="Lang" right>
                        <b-dropdown-item href="#">EN</b-dropdown-item>
                        <b-dropdown-item href="#">ES</b-dropdown-item>
                        <b-dropdown-item href="#">RU</b-dropdown-item>
                        <b-dropdown-item href="#">FA</b-dropdown-item>
                    </b-nav-item-dropdown>

                    <b-nav-item-dropdown right v-if="!isUserLoggedIn">
                        <!-- Using 'button-content' slot -->
                        <template slot="button-content"><em>Zaloguj</em></template>
                        <b-dropdown-form @submit="onLoginSubmit">
                            <b-form-group label="Login" label-for="dropdown-form-email" @submit.stop.prevent>
                                <b-form-input
                                        id="dropdown-form-email"
                                        size="sm"
                                        placeholder="Login"
                                        v-model="form.login"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-group label="Password" label-for="dropdown-form-password">
                                <b-form-input
                                        id="dropdown-form-password"
                                        type="password"
                                        size="sm"
                                        placeholder="Hasło"
                                        v-model="form.password"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-checkbox class="mb-3">Zapamiętaj</b-form-checkbox>
                            <b-dropdown-item-button variant="primary" @click="onClick">Zaloguj</b-dropdown-item-button>
                            <b-dropdown-divider></b-dropdown-divider>
                            <b-dropdown-item-button>Zarejestruj</b-dropdown-item-button>

                        </b-dropdown-form>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from "vue-property-decorator";
    import {logIn} from '@/security/BasicAuthInterceptor';

    @Component
    export default class Navbar extends Vue {

        form: any = {
            login: "",
            password: "",
        };

        get isUserLoggedIn(): boolean {
            return localStorage.getItem("auth_token") != null;
        }

        private onLoginSubmit() {
            logIn(this.form.login, this.form.password);
        }
    }
</script>

<style scoped>

</style>
