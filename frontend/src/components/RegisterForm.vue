<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset">
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
            </b-form-group>

            <b-form-group id="input-group-2"
                          label="Hasło"
                          label-for="password-input"
                          descrpition="Twoje hasło">
                <b-form-input
                        id="password-input"
                        v-model="form.password"
                        required
                        type="password"
                        placeholder="Wpisz hasło"
                ></b-form-input>
            </b-form-group>

            <b-button type="submit" variant="primary">Zarejestruj</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {RegisterUserDto} from '@/data/RegisterUserDto';
    import axios from 'axios';


    @Component
    export default class Register extends Vue {

        private form: RegisterUserDto = {
            username: '',
            password: '',
        };

        private onSubmit() {
            axios.post('http://localhost:9090/user/', this.form)
                .then((respone) => console.log(respone))
                .catch((error) => console.log(error));
        }
    }
</script>

<style scoped>

</style>
