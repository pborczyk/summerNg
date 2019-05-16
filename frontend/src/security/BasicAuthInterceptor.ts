import axios, {AxiosRequestConfig} from 'axios';

export function logIn(username: string, password: string) {
    const token: string = window.btoa(username + ':' + password);
    sessionStorage.setItem('auth_token', token);
    registerInterceptor();
}

function registerInterceptor() {
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
}
