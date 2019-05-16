<template>
    <div>
        <b-media>
            <b-img slot="aside" blank blank-color="#ccc" width="64" alt="placeholder"></b-img>

            <h5 class="mt-0">{{ username }}</h5>
            <p class="mb-0">
                {{ content }}
            </p>
        </b-media>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import {Component, Prop} from 'vue-property-decorator';

    @Component
    export default class Comment extends Vue {
        @Prop() private username!: string;
        @Prop() private content!: string;
    }

</script>

<style scoped>

</style>
