<template>
    <div>
        <entry v-for="entry in entries" v-bind:username="entry.authorUsername"
               v-bind:content="entry.entryContent"></entry>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from 'vue-property-decorator';
    import {EntryDto} from '@/data/EntryDto';
    import Entry from '@/components/Entry.vue';

    @Component({
        components: {Entry,}
    })
    export default class Entries extends Vue {
        @Prop() private entries!: EntryDto[];
    }
</script>

<style scoped>

</style>