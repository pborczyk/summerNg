<template>
    <div>
        <b-form @submit="onSubmit">
            <b-textarea
                    id="textarea"
                    v-model="form.content"
                    placeholder="Wpisz coś..."
                    rows="3"
                    max-rows="6">
            </b-textarea>
            <b-button variant="primary" type="submit">Dodaj</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';
    import {api} from '@/Api';
    import {environment} from '@/env/DevEnv';
    import {CreateEntryRequestDto} from '@/data/CreateEntryRequestDto';
    import {store} from '@/store/store';


    @Component
    export default class NewEntryForm extends Vue {
        form: CreateEntryRequestDto = {
            content: '',
            author: '',
        };

        private onSubmit() {
            this.form.author = store.state.loggedInUsername;
            api.post<number>(environment.apiUrl + 'entry/', this.form)
                .then((respone) => console.log('New entry id: ' + respone))
                .catch((error) => console.log(error));
        }
    }

</script>

<style scoped>

</style>
