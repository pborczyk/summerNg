import axios, {AxiosRequestConfig} from 'axios';
import {environment} from '@/env/DevEnv';

export const api = axios.create({
    url: environment.apiUrl,
    params: {},
    timeout: 10000,
});
