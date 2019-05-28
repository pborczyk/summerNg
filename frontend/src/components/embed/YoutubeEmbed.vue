<template>
    <div>
        <iframe id="ytplayer" type="text/html" width="640" height="360"
                v-bind:src="embedSrc"
                frameborder="0">
        </iframe>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {environment} from '@/env/DevEnv';
    @Component
    export default class YoutubeEmbed extends Vue {
        @Prop() private videoId!: string;
        @Prop() private autoplay!: boolean;

        get embedSrc(): string {
            return 'http://www.youtube.com/embed/' + this.videoId + '?autoplay=' + this.autoplay ? '1' : '0'
                + '&origin=' + environment.appUrl;
        }
    }

</script>

<style scoped>
    .yt-player {
        width: 640px;
        height: 360px;
    }
</style>
