<template>
    <div>
        <b-form @submit="onSubmit" novalidate="true">
            <b-form-group
                    id="input-group-1"
                    label="Login"
                    label-for="login-input"
                    description="Nazwa pod którą będziesz widoczny/a">
                <b-form-input
                        id="login-input"
                        v-model="form.username"
                        required
                        placeholder="Wpisz login">
                </b-form-input>

                <b-form-invalid-feedback id="login-feedback">
                    Login jest wymagany oraz musi mieć 6-25 znaków.
                </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group id="password-group-1"
                          label="Hasło"
                          label-for="password-input-1"
                          descrpition="Twoje hasło">
                <b-form-input
                        id="password-input-1"
                        v-model="form.password"
                        required
                        type="password"
                        placeholder="Wpisz hasło"></b-form-input>

                <b-form-invalid-feedback id="password-feedback">
                    Hasło jest wymagane oraz musi mieć 6-25 znaków.
                </b-form-invalid-feedback>
            </b-form-group>

            <b-form-group id="password-group-2"
                          label="Hasło"
                          label-for="password-input-2"
                          descrpition="Twoje hasło">
                <b-form-input
                        id="password-input-2"
                        v-model="form.repeatPassword"
                        required
                        type="password"
                        placeholder="Powtórz hasło">
                </b-form-input>

                <b-form-invalid-feedback id="repeat-password-feedback">
                    Hasła muszą być zgodne.
                </b-form-invalid-feedback>
            </b-form-group>

            <b-button type="submit" variant="primary">Zarejestruj</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {RegisterUserDto} from '@/data/RegisterUserDto';
    import {api} from '@/api/Api';
    import {environment} from '@/env/DevEnv';
    import {SpringErrorDto} from '@/data/SpringErrorDto';


    @Component
    export default class Register extends Vue {

        form = {
            username: '',
            password: '',
            repeatPassword: '',
        };

        private onSubmit() {
            api.post(environment.apiUrl + 'user/', this.form)
                .then((response) => this.onSuccess())
                .catch((error) => this.onError(error.response.data));
        }

        private onSuccess() {
            this.$router.replace({path: '/' },
                () => {
                    console.log('error, emitting event');
                    this.$emit('alert-event', 'Zarejestrowano', '');
                });
        }

        private onError(error: SpringErrorDto) {
            console.log(error);
            this.$emit('alert-event', error.message, 'danger');
        }
    }
</script>

<style scoped>

</style>
