<template>
    <div class="comment">
        <b-media>
            <b-img slot="aside" blank blank-color="#ccc" width="64" alt="placeholder"></b-img>

            <h5 class="mt-0">{{ comment.author }}</h5>
            <p class="mb-0">
                {{ comment.content }}
            </p>
        </b-media>
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
    .comment {
        margin-top: 10px;
    }
</style>
