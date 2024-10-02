<template>
    <div class="eatery-finder-form" v-on:submit="setSearchTerm">
        <h3>Restaurant Finder</h3>
        <form v-if="showForm">
            <div class="form-input-group">
                <label for="search">Zipcode/City:</label>
                <input type="text" v-model="search">
            </div>
            <button type="submit">Find Eatery</button>
        </form>
        <div v-else>
            <button>Show Form</button>
        </div>
    </div>

</template>

<script>

export default {
    data() {
        return {
            search: "",
            showForm: true,
        };
    },
    methods: {
        toggleForm() {
            this.showForm = !this.showForm;
        },
        setSearchTerm() {
            this.$store.commit('SET_SEARCH_TERM', this.search);
        }
    },
};


</script>

<style>
.eatery-finder-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 50px;
    background-color: #f0f0f0;
    width: 50%;
}
</style>