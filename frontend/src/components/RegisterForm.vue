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
                        placeholder="Wpisz login"
                        @input="$v.form.username.$touch()"
                        :state="$v.form.username.$dirty ? !$v.form.username.$error : null">
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
                        placeholder="Wpisz hasło"
                        @input="$v.form.password.$touch()"
                        :state="$v.form.password.$dirty ? !$v.form.password.$error : null"
                ></b-form-input>

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
                        placeholder="Powtórz hasło"
                        @input="$v.form.repeatPassword.$touch()"
                        :state="$v.form.repeatPassword.$dirty ? !$v.form.repeatPassword.$error : null"
                ></b-form-input>

                <b-form-invalid-feedback id="repeat-password-feedback">
                    Hasła muszą być zgodne.
                </b-form-invalid-feedback>
            </b-form-group>

            <b-button type="submit" variant="primary" :disabled="$v.form.$invalid">Zarejestruj</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {RegisterUserDto} from '@/data/RegisterUserDto';
    import {api} from '@/Api';
    import {environment} from '@/env/DevEnv';
    import {SpringErrorDto} from '@/data/SpringErrorDto';
    import {mixins} from 'vue-class-component';
    import { validationMixin } from 'vuelidate';
    import {required, minLength, sameAs, maxLength} from "vuelidate/lib/validators";


    @Component(
        {
            validations: {
                form: {
                    username: {
                        required,
                        minLength: minLength(4),
                        maxLength: maxLength(25),
                    },
                    password: {
                        required,
                        minLength: minLength(6),
                        maxLength: maxLength(25),
                    },
                    repeatPassword: {
                        required,
                        sameAs: sameAs('password'),
                    },
                },
            },
        },
    )
    export default class Register extends mixins(validationMixin) {

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
