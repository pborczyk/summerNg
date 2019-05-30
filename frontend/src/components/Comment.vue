<template>
    <div class="comment-box">
        <b-media>
            <b-img slot="aside" blank blank-color="#ccc" width="32" alt="placeholder"></b-img>

            <h5 class="mt-0"><b-link :to="{ path: '/profile/' + comment.author, }">{{ comment.author }}</b-link></h5>
            <p class="mb-0 comment-content">
                {{ comment.content }}
            </p>
        </b-media>
        <hr/>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';
    import {CommentDto} from "@/data/CommentDto";

    @Component
    export default class Comment extends Vue {
        @Prop() private comment!: CommentDto;
    }

</script>

<style scoped>
    .comment-box {
        margin-top: 10px;
    }

    .comment-content {
        font-size: 1rem;
    }
</style>
