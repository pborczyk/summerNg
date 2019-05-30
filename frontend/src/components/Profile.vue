<template>
    <div>
        <b-container>
            <img v-bind:src="getAvatarUrl" alt="Profile avatar" class="img-thumbnail">

            <entries v-bind:username="this.username"></entries>
        </b-container>
    </div>
</template>

<script lang="ts">
    import {Component, Prop} from "vue-property-decorator";
    import {mixins} from 'vue-class-component';
    import ValidationMixin from '@/components/util/ValidationMixin.vue';
    import Entries from '@/components/Entries.vue';
    @Component({
        components: {Entries}
    })
    export default class Profile extends mixins(ValidationMixin) {
        @Prop() private username!: string;

        public getAvatarUrl() {

        }
    }
</script>

<style scoped>

</style>
