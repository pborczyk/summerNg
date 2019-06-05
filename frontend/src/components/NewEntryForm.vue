<template>
    <div class="entry-form">
        <b-form @submit="onSubmit">
            <b-form-group>
                <b-textarea
                        id="textarea"
                        v-model="form.content"
                        placeholder="Wpis musi mieć minimum 2 znaki."
                        v-bind:rows="textAreaRows"
                        max-rows="6"
                        @click="onTextAreaFocus"
                        @focus="onTextAreaFocus"
                        data-vv-name="entry"
                        v-validate.initial="{ required: true, min: 2 }">
                </b-textarea>
                <b-form-invalid-feedback id="entry-feedback">
                    Wpis nie może zostać pusty.
                </b-form-invalid-feedback>
            </b-form-group>
            <b-row>
                <b-col cols="1">
                <b-button variant="primary" type="submit" :disabled="errors.any()">Dodaj</b-button>
                </b-col>
                <b-col>
                <b-input v-model="form.embedContent"></b-input>
                </b-col>
            </b-row>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component} from 'vue-property-decorator';
    import {api} from '@/api/Api';
    import {environment} from '@/env/DevEnv';
    import {CreateEntryRequestDto} from '@/data/CreateEntryRequestDto';
    import {store} from '@/store/store';
    import {mixins} from 'vue-class-component';
    import ValidationMixin from '@/components/util/ValidationMixin.vue';
    import {EntryDto} from "@/data/EntryDto";
    import {AxiosPromise} from 'axios';

    @Component
    export default class NewEntryForm extends mixins(ValidationMixin) {
        public form: CreateEntryRequestDto = {
            embedContent: '',
            embedContentType: 'YOUTUBE',
            content: '',
            author: ''
        };

        private textAreaRows: number = 2;

        public onTextAreaBlur() {
            this.textAreaRows = 2;
        }

        public onTextAreaFocus() {
            this.textAreaRows = 6;
        }


        public onSubmit() {
            this.form.author = store.state.loggedInUsername;
            this.form.embedContent = this.form.embedContent.replace('https://www.youtube.com/watch?v=', '');
            api.post<number>(environment.apiUrl + 'entries/', this.form)
                .then((respone) => {
                    console.log('New entry id: ' + respone.data);
                    this.onSuccess(respone.data);
                })
                .catch((error) => console.log(error));
        }

        private onSuccess(entryId: number) {
            this.$router.replace({
                path: '/entry/' + entryId,
            });
            this.form.content = '';
        }
    }

</script>

<style scoped>
    .entry-form {
        margin-top: 10px;
        margin-bottom: 10px;
    }
</style>
