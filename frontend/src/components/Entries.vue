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
        private entries: EntryDto[]= [];


        private mounted() {
            const mode = this.$route.params.mode != null ? this.$route.params.mode : 'newest';
            const url = 'http://localhost:9090/entry/' + mode;
            axios.get<EntryDto[]>(url)
                .then((response) => this.entries = response.data)
                .catch((error) => console.log(error));
        }
    }

</script>

<style scoped>

</style>
