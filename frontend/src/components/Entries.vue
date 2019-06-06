<template>
    <div class="entry">
        <new-entry-form v-if="isNewEntryFormVisible"></new-entry-form>
        <entry v-for="entry in entries"
               v-bind:entry="entry"
                @entry-deleted="onEntryDeleted($event)">
        </entry>
        <div class="overflow-auto">
            <b-pagination-nav :link-gen="linkGen" :number-of-pages="maxPages" use-router></b-pagination-nav>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Watch} from "vue-property-decorator";
    import {EntryDto} from '@/data/EntryDto';
    import Entry from '@/components/Entry.vue';
    import {api} from '@/api/Api';
    import NewEntryForm from '@/components/NewEntryForm.vue';
    import {environment} from '@/env/DevEnv';
    import Vue from 'vue';
    import {store} from '@/store/store';
    import {GetEntriesresponseDto} from "@/data/GetEntriesresponseDto";

    @Component({
        components: {
            NewEntryForm,
            Entry,
        },
    })
    export default class Entries extends Vue {
        private entries: EntryDto[] = [];
        public maxPages: number = 1;
        @Prop() private username!: string;



        private get isNewEntryFormVisible(): boolean {
            return store.state.isLoggedIn;
        }

        private mounted() {
          this.initialize();
        }

        private initialize() {
            if (this.username != null) {
                this.userModeHandler();
            }
            let mode = this.$route.params.mode;
            if (this.$route.params.mode == null) {
                mode = 'newest';
            }
            switch (mode) {
                case 'newest':
                case 'top':
                    this.rankingModeHandler(mode);
                    break;
            }
        }

        private rankingModeHandler(mode: string) {
            debugger;
            let pageNum = (this.$route.params.pageNum != null ? this.$route.params.pageNum : "1") as unknown as number;
            const url = environment.apiUrl + 'entries/?mode=' + mode + '&page='
            + (pageNum - 1);
            api.get<GetEntriesresponseDto>(url)
                .then((response) => {
                    console.log(response.data);
                    this.entries = response.data.content;
                    this.maxPages = response.data.maxPages;
                })
                .catch((error) => console.log(error));
        }

        private userModeHandler() {
            const url = environment.apiUrl + 'users/' + this.username +'/entries';
            api.get<EntryDto[]>(url)
                .then((response) => {
                    console.log(response.data);
                    return this.entries = response.data;
                })
                .catch((error) => console.log(error));

        }

        public onEntryDeleted(entryId: number) {
            for( var i = 0; i < this.entries.length; i++){
                if (  this.entries[i].id === entryId) {
                    this.entries.splice(i, 1);
                }
            }
        }

        @Watch('$route')
        public routeWatch() {
            this.initialize();
        }

        public linkGen(pageNum: number) {
            let mode = this.$route.params.mode;
            if (this.$route.params.mode == null) {
                mode = 'newest';
            }
            return '/entries/' + mode + '/page/' + pageNum;
        }
    }

</script>

<style scoped>
    .entry {
        margin-top: 10px;
    }
</style>
