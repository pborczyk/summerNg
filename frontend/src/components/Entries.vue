<template>
    <div class="entry">
        <new-entry-form v-if="isNewEntryFormVisible"></new-entry-form>
        <entry v-for="entry in entries"
               v-bind:entry="entry">
        </entry>
    </div>
</template>

<script lang="ts">
    import {Component, Prop} from "vue-property-decorator";
    import {EntryDto} from '@/data/EntryDto';
    import Entry from '@/components/Entry.vue';
    import {api} from '@/api/Api';
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
        @Prop() private username!: string;



        private get isNewEntryFormVisible(): boolean {
            return store.state.isLoggedIn;
        }

        private mounted() {
            if (this.$route.params.mode != null) {
                const mode = this.$route.params.mode;
                switch (mode) {
                    case 'newest':
                    case 'top':
                        this.rankingModeHandler(mode);
                        break;
                }
            }
            if (this.username != null) {
                this.userModeHandler();
            }
        }

        private rankingModeHandler(mode: string) {
            const url = environment.apiUrl + "entries/" + mode;
            api.get<EntryDto[]>(url)
                .then((response) => {
                    console.log(response.data);
                    return this.entries = response.data;
                })
                .catch((error) => console.log(error));
        }

        private userModeHandler() {
            const url = environment.apiUrl + "entries/user?username=" + this.username;
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
    .entry {
        margin-top: 10px;
    }
</style>
