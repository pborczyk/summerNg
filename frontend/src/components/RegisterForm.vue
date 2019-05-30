<template>
    <div>
        <b-form @submit="onSubmit">
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
                        data-vv-name="username"
                        :state="validateState('username')"
                        aria-describedby="login-feedback"
                        v-validate="{ required: true, min: 6, max:30 }">
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
                        data-vv-name="password"
                        :state="validateState('password')"
                        aria-describedby="password-feedback"
                        v-validate="{ required: true, min: 6, max:30 }">
                </b-form-input>

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
                        data-vv-name="repeatPassword"
                        :state="validateState('repeatPassword')"
                        aria-describedby="repeat-password-feedback"
                        v-validate="{ is: form.password }">
                </b-form-input>

                <b-form-invalid-feedback id="repeat-password-feedback">
                    Hasła muszą być zgodne.
                </b-form-invalid-feedback>
            </b-form-group>

            <b-button type="submit" variant="primary" :disabled="errors.any()">Zarejestruj</b-button>
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
    import {mixins} from 'vue-class-component';
    import ValidationMixin from '@/components/util/ValidationMixin.vue';


    @Component
    export default class Register extends mixins(ValidationMixin) {

        public form = {
            username: '',
            password: '',
            repeatPassword: '',
        };

        private onSubmit() {
            api.post(environment.apiUrl + 'user/', {
                username: this.form.username,
                password: this.form.password,
            })
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
