<template>
    <div>
        <new-entry-form v-if="isNewEntryFormVisible"></new-entry-form>
        <entry v-for="entry in entries"
               v-bind:username="entry.author"
               v-bind:content="entry.content"
               v-bind:upvotes="entry.upvotes">
        </entry>
    </div>
</template>

<script lang="ts">
    import {Component} from 'vue-property-decorator';
    import {EntryDto} from '@/data/EntryDto';
    import Entry from '@/components/Entry.vue';
    import {api} from '@/Api';
    import NewEntryForm from '@/components/NewEntryForm.vue';
    import {environment} from '@/env/DevEnv';
    import Vue from 'vue';
    import {store} from '@/store/store';

    @Component({
        components: {
            NewEntryForm,
            Entry,
        },
    })
    export default class Entries extends Vue {
        private entries: EntryDto[] = [];


        private get isNewEntryFormVisible(): boolean {
            return store.state.isLoggedIn;
        }

        private mounted() {
            const mode = this.$route.params.mode != null ? this.$route.params.mode : 'newest';
            const url = environment.apiUrl + 'entries/' + mode;
            api.get<EntryDto[]>(url)
                .then((response) => {
                    console.log(response.data);
                    return this.entries = response.data;
                })
                .catch((error) => console.log(error));
        }
    }

</script>

<style scoped>

</style>
