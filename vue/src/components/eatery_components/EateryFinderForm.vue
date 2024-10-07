<template>
    <form class="eatery-finder-form" v-on:submit.prevent="setSearch">

            <label for="zipcode">Zipcode/City:</label>
            <input type="text" v-model="zipcode" />
            <label for="term">Narrow your search:</label>
            <input type="text" v-model="term" />
            <button type="submit">Find Eatery</button>
    </form>
</template>

<script>
export default {
    data() {
        return {
            zipcode: ``,
            term: ``,
        };
    },
    methods: {
        setSearch() {

            this.$store.commit('SET_SEARCH_TERM', this.zipcode);

            this.$store.commit('SET_NARROW_TERM', this.term);
            this.resetForm();
        },
        resetForm() {
            this.zipcode = "";
            this.term = "";
        },
    },
};

</script>

<style></style>
