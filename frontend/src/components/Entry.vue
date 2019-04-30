<template>
    <div>
        <b-card>
            <b-media>
                <b-img slot="aside" blank blank-color="#ccc" width="64" alt="placeholder"></b-img>

                <div class="clearfix">
                    <h5 class="mt-0 float-left">{{ username }}</h5>
                    <div class="float-right">{{ score }}</div>
                </div>
                <p>
                    {{ content }}
                </p>
                
            </b-media>
        </b-card>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';

    @Component
    export default class Entry extends Vue {
        @Prop() private username!: string;
        @Prop() private content!: string;
    }

</script>

<style scoped>

</style>