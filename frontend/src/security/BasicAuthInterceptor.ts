import axios, {AxiosRequestConfig} from 'axios';

export function logIn(username: string, password: string) {
    const token: string = window.btoa(username + ':' + password);
    localStorage.setItem('auth_token', token);
    registerInterceptor();
}

function registerInterceptor() {
    axios.interceptors.request.use((config: AxiosRequestConfig): AxiosRequestConfig => {
        const token = localStorage.getItem('auth_token');
        if (token) {
            config.headers = {
                Authorization: 'Bearer ' + token,
            };
        }
        return config;
    });
}
