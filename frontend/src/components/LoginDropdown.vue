<template>
        <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content"><em>Zaloguj</em></template>
            <b-dropdown-form>
                <b-form-group label="Login" label-for="dropdown-form-email">
                    <b-form-input
                            id="dropdown-form-email"
                            size="sm"
                            placeholder="Login"
                            v-model="form.login">
                    </b-form-input>
                </b-form-group>

                <b-form-group label="Password" label-for="dropdown-form-password">
                    <b-form-input
                            id="dropdown-form-password"
                            type="password"
                            size="sm"
                            placeholder="Hasło"
                            v-model="form.password">
                    </b-form-input>
                </b-form-group>

                <b-form-checkbox class="mb-3">Zapamiętaj</b-form-checkbox>
                <b-dropdown-item-button variant="primary" @click="onLoginSubmit">Zaloguj</b-dropdown-item-button>
                <b-dropdown-divider></b-dropdown-divider>
                <b-link :to="{ path: '/register' }">Zarejestruj</b-link>

            </b-dropdown-form>
        </b-nav-item-dropdown>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {logIn} from '@/security/BasicAuthInterceptor';
    @Component
    export default class LoginDropdown extends Vue {
        private form: any = {
            login: '',
            password: '',
        };

        onLoginSubmit() {
            console.log('logging in');
            logIn(this.form.login, this.form.password);
        }
    }

</script>

<style scoped>

</style>
