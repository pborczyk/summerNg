<template>
    <div>
        <b-card>
            <b-media>
                <b-img slot="aside" blank blank-color="#ccc" width="64" alt="placeholder"></b-img>

                <div class="clearfix">
                    <h5 class="mt-0 float-left">{{ username }}</h5>
                    <div class="float-right">{{ upvotes }}</div>
                </div>
                <p>
                    {{ content }}
                </p>

                <comment v-for="comment in comments"
                         v-bind:username="comment.author"
                         v-bind:content="comment.content">
                </comment>

                <add-comment-form></add-comment-form>
            </b-media>
        </b-card>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {CommentDto} from '@/data/CommentDto';
    import Comment from '@/components/Comment.vue';
    import AddCommentForm from '@/components/AddCommentForm.vue';
    @Component({
        components: {AddCommentForm, Comment}
    })
    export default class Entry extends Vue {
        @Prop() private username!: string;
        @Prop() private content!: string;
        @Prop() private upvotes!: string;
        @Prop() private comments!: CommentDto[];
    }

</script>

<style scoped>

</style>
