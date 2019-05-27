<template>
    
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";

    @Component({

    })
    export default class ValidationMixin extends Vue {
        protected validateState(ref: string) {
            let field = this.$validator.fields.find({name: ref});
            if (field && (field.flags.dirty || field.flags.validated)) {
                return !this.$validator.errors.has(ref)
            }
            return null;
        }
    }
</script>

<style scoped>

</style>
