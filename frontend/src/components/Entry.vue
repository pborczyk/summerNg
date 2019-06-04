<template>
    <div>
        <b-card>
            <b-media>
                <b-img slot="aside" blank blank-color="#ccc" width="32" alt="placeholder"></b-img>

                <div class="clearfix">
                    <h5 class="mt-0 float-left"><b-link :to="{ path: '/profile/' + entry.author, }">{{ entry.author }}</b-link></h5>
                    <div class="upvote-box">{{ entry.upvotes }}<b-button variant="light" class="upvote-button" :disabled="!isUserLoggedIn" @click="onUpvoteClicked"><span class="fas fa-plus icon-size"></span></b-button></div>
                </div>
                <p>
                    {{ entry.content }}
                </p>

                <youtube-embed v-if="entry.embedContent != ''" v-bind:videoId="entry.embedContent"></youtube-embed>

                <comment v-for="comment in entry.comments"
                         v-bind:comment="comment">
                </comment>

                <add-comment-form
                        @comment-added="onCommentAdded"
                        v-if="isUserLoggedIn"
                        v-bind:entry-id="entry.id">

                </add-comment-form>
            </b-media>
        </b-card>
        <hr/>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {CommentDto} from '@/data/CommentDto';
    import Comment from '@/components/Comment.vue';
    import AddCommentForm from '@/components/AddCommentForm.vue';
    import {store} from '@/store/store';
    import {EntryDto} from "@/data/EntryDto";
    import {api} from '@/api/Api';
    import {environment} from '@/env/DevEnv';
    import {AxiosPromise} from 'axios';
    import YoutubeEmbed from "@/components/embed/YoutubeEmbed.vue";
    @Component({
        components: {YoutubeEmbed, AddCommentForm, Comment},
    })
    export default class Entry extends Vue {
        @Prop() private entry!: EntryDto;

        private get isUserLoggedIn(): boolean {
            return store.state.isLoggedIn;
        }

        public onCommentAdded() {
            api.get<CommentDto[]>(environment.apiUrl + 'entries/' + this.entry.id + '/comments')
                .then((response) => this.entry.comments = response.data);
        }

        public onUpvoteClicked() {
            api.put<number>(environment.apiUrl + 'entries/' + this.entry.id + '/upvote')
                .then((currentUpvotes) => this.entry.upvotes = currentUpvotes.data);
        }

        private mounted() {
            if (this.entry === undefined) {
                this.getEntry(this.$route.params.entryId as unknown as number)
                    .then((response) => this.entry = response.data);
            }
        }

        private getEntry(entryId: number) : AxiosPromise<EntryDto> {
            return api.get<EntryDto>(environment.apiUrl + 'entries/' + entryId);
        }
    }

</script>

<style scoped>
    .upvote-box {
        float: right;
        text-align: center;
    }

    .upvote-button {
        font-size: 0.5rem;
    }

    .icon-size {
        font-size: 0.75rem;
    }
</style>
