<template>
    <div>
        <b-form @submit="onSubmit">
            <b-form-group>
                <b-textarea
                        id="textarea"
                        v-model="form.content"
                        placeholder="Wpisz coś..."
                        v-bind:rows="textAreaRows"
                        max-rows="6"
                        @blur="onTextAreaBlur"
                        @click="onTextAreaFocus"
                        @focus="onTextAreaFocus"
                        @input="onContentInput"
                        name="entry"
                        :state="validateState('entry')"
                        aria-describedby="entry-feedback"
                        v-validate="{ required: true, alpha: true, min: 2 }">
                </b-textarea>
                <b-form-invalid-feedback id="entry-feedback">
                    Wpis nie może zostać pusty.
                </b-form-invalid-feedback>
            </b-form-group>
            <b-button variant="primary" type="submit">Dodaj</b-button>
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

    @Component
    export default class NewEntryForm extends mixins(ValidationMixin) {
        public form: CreateEntryRequestDto = {
            content: '',
            author: '',
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
            api.post<number>(environment.apiUrl + 'entry/', this.form)
                .then((respone) => console.log('New entry id: ' + respone))
                .catch((error) => console.log(error));
        }
    }

</script>

<style scoped>

</style>
