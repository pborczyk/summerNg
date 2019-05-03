<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset">
            <b-textarea
                    id="textarea"
                    v-model="content"
                    placeholder="Wpisz coś..."
                    rows="3"
                    max-rows="6">
            </b-textarea>
            <b-button variant="primary" type="submit">Dodaj</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from "vue-property-decorator";
    import axios, { AxiosRequestConfig, AxiosPromise } from 'axios';
    import {CreateEntryRequestDto} from '@/data/CreateEntryRequestDto';


    @Component
    export default class NewEntryForm extends Vue {
        form: any = {
            content: ""
        };

        private onSubmit() {
            axios.post<CreateEntryRequestDto[]>('http://localhost:9090/entry/' + this.$route.params.mode)
                .then((respone) => console.log('New entry id: ' + respone))
                .catch((error) => console.log(error));
        }

        private onReset() {

        }
    }
</script>

<style scoped>

</style>
