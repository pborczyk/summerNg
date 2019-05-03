<template>
    <div>
        <new-entry-form></new-entry-form>
        <entry v-for="entry in entries" v-bind:username="entry.authorUsername"
               v-bind:content="entry.entryContent"></entry>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {EntryDto} from '@/data/EntryDto';
    import Entry from '@/components/Entry.vue';
    import axios, { AxiosRequestConfig, AxiosPromise } from 'axios';
    import NewEntryForm from '@/components/NewEntryForm.vue';

    @Component({
        components: {
            NewEntryForm,
            Entry,
        },
    })
    export default class Entries extends Vue {
        @Prop() private apiAddress!: string;
        private entriesHolder! : EntryDto[];

        get entries() {
            return this.entriesHolder;
        }

        private mounted() {
            axios.get<EntryDto[]>('http://localhost:9090/entry/' + this.$route.params.mode)
                .then((response) => this.entriesHolder = response.data)
                .catch((error) => console.log(error));
        }
    }
    //http://localhost:9090/entry/newest
</script>

<style scoped>

</style>
