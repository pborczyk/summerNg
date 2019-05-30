<template>
    <div>
        <b-container>
            <b-container>
                <b-row>
                    <b-col cols="1">
                        <b-img blank blank-color="#ccc" width="128" v-bind:src="getAvatarUrl" alt="Profile avatar"
                               class="img-thumbnail"></b-img>
                    </b-col>
                    <b-col>
                        {{this.username}}
                    </b-col>
                </b-row>
            </b-container>

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
