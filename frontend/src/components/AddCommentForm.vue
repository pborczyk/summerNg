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
                        data-vv-name="comment"
                        :state="validateState('comment')"
                        aria-describedby="comment-feedback"
                        v-validate="{ required: true, min: 2 }">
                </b-form-textarea>
                <b-form-invalid-feedback id="comment-feedback">
                    Komentarz nie może zostać pusty.
                </b-form-invalid-feedback>
                <b-button type="submit" variant="primary" class="submit-button">Wyślij</b-button>
            </b-form-group>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {api} from '@/api/Api';
    import {CreateCommentDto} from '@/data/CreateCommentDto';
    import {store} from '@/store/store';
    import {mixins} from 'vue-class-component';
    import ValidationMixin from '@/components/util/ValidationMixin.vue';
    import {environment} from '@/env/DevEnv';

    @Component
    export default class AddCommentForm extends mixins(ValidationMixin) {
        public content = '';

        @Prop() private entryId!: number;

        private textAreaRows: number = 2;

        public onTextAreaBlur() {
            this.textAreaRows = 2;
        }

        public onTextAreaFocus() {
            this.textAreaRows = 5;
        }

        public onSubmit() {
            const request: CreateCommentDto = {
                entryId: this.entryId,
                author: store.state.loggedInUsername,
                content: this.content,
            };
            api.post(environment.apiUrl + 'entry/comment/' , request)
                .then(() => this.$emit('comment-added'));
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
