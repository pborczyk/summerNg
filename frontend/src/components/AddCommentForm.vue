<template>
    <div>
        <b-form @submit="onSubmit">
            <b-form-group>
                <b-form-textarea
                        id="comment-input"
                        v-model="content"
                        placeholder="Zacznij pisać..."
                        v-bind:rows="textAreaRows"
                        max-rows="5"
                        @blur="onTextAreaBlur"
                        @click="onTextAreaFocus"
                        @focus="onTextAreaFocus"
                        class="text-area"
                        @input="$v.content.$touch()"
                        :state="$v.content.$dirty ? !$v.content.$error : null"
                ></b-form-textarea>
                <b-form-invalid-feedback id="entry-feedback">
                    Komentarz nie może zostać pusty.
                </b-form-invalid-feedback>
                <b-button type="submit" variant="primary" class="submit-button" :disabled="$v.content.$invalid">Wyślij</b-button>
            </b-form-group>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {api} from '@/Api';
    import {CreateCommentDto} from '@/data/CreateCommentDto';
    import {store} from '@/store/store';
    import {minLength, required} from 'vuelidate/lib/validators';
    import {mixins} from 'vue-class-component';
    import {validationMixin} from "vuelidate";

    @Component({
        validations: {
            content: {
                required,
            },
        },
    })
    export default class AddCommentForm extends mixins(validationMixin) {
        @Prop() private username!: string;
        @Prop() private entryId!: number;
        content = '';

        private textAreaRows: number = 2;

        onTextAreaBlur() {
            this.textAreaRows = 2;
        }

        onTextAreaFocus() {
            this.textAreaRows = 5;
        }

        onSubmit() {
            const request: CreateCommentDto = {
                entryId: this.entryId,
                author: store.state.loggedInUsername,
                content: this.content,
            };
            //api.post(en , request);
        }
    }
</script>

<style scoped>
    .text-area {
        margin-top: 5px;
        margin-bottom: 5px;
    }

    .submit-button {
    }
</style>
