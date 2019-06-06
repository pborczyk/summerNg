<template>
    <div>
        <b-form @submit="onSubmit">
            <b-textarea v-model="form.content"/>
            <b-button variant="primary" type="submit">Zapisz</b-button>
        </b-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {EntryDto} from '@/data/EntryDto';
    import {EditEntryRequestDto} from "@/data/EditEntryRequestDto";
    import {api} from '@/api/Api';
    import {environment} from '@/env/DevEnv';

    @Component
    export default class EditEntry extends Vue {
        @Prop() private entry!: EntryDto;

        private form: EditEntryRequestDto = {
            content: '',
            embedContent: ''

        };

        private mounted() {
            this.form.content = this.entry.content;
            this.form.embedContent = this.entry.embedContent;
        }

        public onSubmit() {
            api.put<number>(environment.apiUrl + 'entries/' + this.entry.id, this.form)
                .then(() => {
                    this.$emit('entry-edited');
                });
        }

    }
</script>

<style scoped>

</style>
