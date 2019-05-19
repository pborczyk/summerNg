<script lang="ts">
    import {api} from '@/api/Api';
    import {UserDto} from '@/data/UserDto';
    import {environment} from '@/env/DevEnv';
    import {Component} from 'vue-property-decorator';
    import Vue from 'vue';
    import {store} from '@/store/store';
    import {AxiosRequestConfig} from 'axios';

    @Component
    export default class BasicAuthInterceptor extends Vue {

        protected logIn(username: string, password: string) {
            const token: string = window.btoa(username + ':' + password);
            sessionStorage.setItem('auth_token', token);
            this.registerInterceptor();
        }

        protected registerInterceptor() {
            const interceptorId = api.interceptors.request.use((config: AxiosRequestConfig): AxiosRequestConfig => {
                const token = sessionStorage.getItem('auth_token');
                if (token) {
                    config.headers = {
                        Authorization: 'Basic ' + token,
                    };
                }
                return config;
            });
            console.log('interceptor registered');
            api.get<UserDto>(environment.apiUrl + 'user/')
                .then((user) => store.commit('logIn', user.data.username))
                .catch((error) => {
                    api.interceptors.request.eject(interceptorId);
                    this.$emit('alert-event', 'Błędne dane logowania', 'danger');
                    console.log('interceptor unregistered');
                })
                .finally();
        }
    }
</script>

