<script lang="ts">
    import axios, {AxiosRequestConfig} from 'axios';
    import {UserDto} from '@/security/UserDto';
    import {environment} from '@/env/DevEnv';
    import {Component} from 'vue-property-decorator';
    import Vue from 'vue';
    import {store} from '@/store/store';

    @Component
    export default class NewEntryForm extends Vue {

        protected logIn(username: string, password: string) {
            const token: string = window.btoa(username + ':' + password);
            sessionStorage.setItem('auth_token', token);
            this.registerInterceptor();
        }

        protected registerInterceptor() {
            axios.interceptors.request.use((config: AxiosRequestConfig): AxiosRequestConfig => {
                const token = sessionStorage.getItem('auth_token');
                if (token) {
                    config.headers = {
                        Authorization: 'Bearer ' + token,
                    };
                }
                return config;
            });
            console.log('interceptor registered');
            axios.get<UserDto>(environment.apiUrl + 'user/')
                .then((user) => store.commit('logIn'))
                .catch((error) => console.log('could not log in ' + error))
                .finally();
        }
    }
</script>

