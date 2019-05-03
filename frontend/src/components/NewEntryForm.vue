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
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import axios from 'axios';


    @Component
    export default class NewEntryForm extends Vue {
        private form: any = {
            content: '',
        };

        private onSubmit() {
            axios.post<number>('http://localhost:9090/entry/', this.form)
                .then((respone) => console.log('New entry id: ' + respone))
                .catch((error) => console.log(error));
        }
    }

</script>

<style scoped>

</style>
