<template>
    <form class="eatery-finder-form" v-on:submit.prevent="setSearch">
        <div class="form-body">
            <div class="form-input-group">
                <label for="search">Zipcode/City:</label>
                <input type="text" v-model="search" />
            </div>
            <button type="submit">Find Eatery</button>
        </div>
    </form>
</template>

<script>
export default {
    data() {
        return {
            search: "",
        };
    },
    methods: {
        setSearch() {
            this.$store.commit('SET_SEARCH_TERM', this.search);
        },
    },
};

</script>

<style>

</style>
