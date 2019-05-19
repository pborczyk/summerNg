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
                        @input="$v.form.content.$touch()"
                        :state="$v.form.content.$dirty ? !$v.form.content.$error : null">
                </b-textarea>
                <b-form-invalid-feedback id="entry-feedback">
                    Wpis nie może zostać pusty.
                </b-form-invalid-feedback>
            </b-form-group>
            <b-button variant="primary" type="submit" v-bind:disabled="$v.form.$invalid">Dodaj</b-button>
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
    import {required} from 'vuelidate/lib/validators';
    import {mixins} from 'vue-class-component';
    import {validationMixin} from "vuelidate";

    @Component({
        validations: {
            form: {
                content: {
                    required,
                }
            }
        }
    })
    export default class NewEntryForm extends mixins(validationMixin) {
        form: CreateEntryRequestDto = {
            content: '',
            author: '',
        };

        private textAreaRows: number = 2;

        onTextAreaBlur() {
            this.textAreaRows = 2;
        }

        onTextAreaFocus() {
            this.textAreaRows = 6;
        }


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
