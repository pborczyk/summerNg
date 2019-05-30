<template>
    <div>
        <b-card>
            <b-media>
                <b-img slot="aside" blank blank-color="#ccc" width="64" alt="placeholder"></b-img>

                <div class="clearfix">
                    <h5 class="mt-0 float-left">{{ entry.author }}</h5>
                    <div class="float-right">{{ entry.upvotes }}</div>
                </div>
                <p>
                    {{ entry.content }}
                </p>

                <comment v-for="comment in entry.comments"
                         v-bind:comment="comment">
                </comment>

                <add-comment-form
                        @comment-added="onCommentAdded"
                        v-if="isNewCommentFormVisible"
                        v-bind:entry-id="entry.id">

                </add-comment-form>
            </b-media>
        </b-card>
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
    @Component({
        components: {AddCommentForm, Comment},
    })
    export default class Entry extends Vue {
        @Prop() private entry!: EntryDto;

        private get isNewCommentFormVisible(): boolean {
            return store.state.isLoggedIn;
        }

        public onCommentAdded() {
            api.get<CommentDto[]>(environment.apiUrl + 'entry/comment?entryId='+ this.entry.id)
                .then((response) => this.entry.comments = response.data);
        }
    }

</script>

<style scoped>

</style>
